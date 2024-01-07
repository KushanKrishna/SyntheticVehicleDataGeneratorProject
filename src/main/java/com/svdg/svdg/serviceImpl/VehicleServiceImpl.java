package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.dto.AddVehicleDataRequestDto;
import com.svdg.svdg.dto.UpdateVehicleDataRequestDto;
import com.svdg.svdg.exception.EngineNoAlreadyPresentException;
import com.svdg.svdg.exception.RegNoAlreadyExistsException;
import com.svdg.svdg.exception.VinAlreayExistsException;
import com.svdg.svdg.exception.VinNotValidException;
import com.svdg.svdg.model.*;
import com.svdg.svdg.repository.*;
import com.svdg.svdg.service.*;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private SvdgRepository svdgRepository;
    @Autowired
    private VehicleServiceUtil vehicleServiceUtil;


    @Override
    public VehicleModel saveVehicle(VehicleModel vehicleModel) throws VinAlreayExistsException {
        if (!this.vehicleServiceUtil.isValidVin(vehicleModel.getVIN())) {
            throw new VinNotValidException(vehicleModel.getVIN());
        }
        if (vehicleServiceUtil.isVinPresent(vehicleModel.getVIN()) || vehicleServiceUtil.isRegNoPresent(vehicleModel.getVehicle_Reg_Num())) {
            if (vehicleServiceUtil.isVinPresent(vehicleModel.getVIN())) {
                throw new VinAlreayExistsException(vehicleModel.getVIN() + " already exists");
            } else {
                throw new RegNoAlreadyExistsException(vehicleModel.getVehicle_Reg_Num());
            }
        }
        if (vehicleServiceUtil.isEngineNoPresent(vehicleModel.getVehicle_Engine_Num())) {
            throw new EngineNoAlreadyPresentException(vehicleModel.getVehicle_Engine_Num());
        }
        VehicleOwnershipRecord vehicleOwnershipRecord = vehicleModel.getVehicleOwnershipRecord();
        List<VehicleServiceRecord> getVehicleServiceRecordList = vehicleModel.getVehicleServiceRecordlist();
        VehicleWarrantyInformation vehicleWarrantyInformation = vehicleModel.getVehicleWarrantyInformation();
        List<Dealer> dealerList = vehicleModel.getDealerList();
        if (Objects.nonNull(dealerList) && Objects.nonNull(getVehicleServiceRecordList) && Objects.nonNull(vehicleOwnershipRecord) && Objects.nonNull(vehicleWarrantyInformation)) {
            for (Dealer dealer : dealerList) {
                dealer.setVehicleModel(vehicleModel);
            }
            for (VehicleServiceRecord vehicleServiceRecord : getVehicleServiceRecordList) {
                vehicleServiceRecord.setVehicleModel(vehicleModel);
            }
            vehicleOwnershipRecord.setVehicleModel(vehicleModel);
            vehicleWarrantyInformation.setVehicleModel(vehicleModel);
            return this.svdgRepository.save(vehicleModel);
        } else {
            return null;
        }
    }

    @Override
    @Cacheable("allData")
    public List<VehicleModel> getVehicleData(Integer limit) {

        List<VehicleModel> list = this.svdgRepository.findAll();
        ArrayList<VehicleModel> arrayList = new ArrayList<>();
        Collections.shuffle(list);
        if (limit <= list.size()) {
            for (int i = 0; i < limit; i++) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;

    }


    @Override
    @Cacheable("allVehicleData")
    public List<VehicleModel> getAllVehicleData(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return this.svdgRepository.findAll(pageable).stream().toList();
    }


    @Override
    public boolean deleteData(String vin) {
        List<String> vins = this.svdgRepository.findAllVins();
        if (vins.contains(vin)) {
            VehicleModel vehicleModel = this.svdgRepository.findByVin(vin);
            if (Objects.nonNull(vehicleModel)) {
                this.svdgRepository.delete(vehicleModel);
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    @Override
    public boolean updateData(String vin, VehicleModel vehicleModel) {
        VehicleModel vehicleModel2 = this.svdgRepository.findByVin(vin);
        if (this.svdgRepository.existsById(vehicleModel2.getVehicle_PK())) {
            if (!this.vehicleServiceUtil.isValidVin(vehicleModel.getVIN())) {
                throw new VinNotValidException();
            }
            vehicleModel.setVehicle_PK(vehicleModel2.getVehicle_PK());
            VehicleOwnershipRecord vehicleOwnershipRecord = vehicleModel.getVehicleOwnershipRecord();
            List<VehicleServiceRecord> getVehicleServiceRecordList = vehicleModel.getVehicleServiceRecordlist();
            VehicleWarrantyInformation vehicleWarrantyInformation = vehicleModel.getVehicleWarrantyInformation();
            List<Dealer> dealerList = vehicleModel.getDealerList();

            if (Objects.nonNull(dealerList) && Objects.nonNull(getVehicleServiceRecordList) && Objects.nonNull(vehicleOwnershipRecord) && Objects.nonNull(vehicleWarrantyInformation)) {
                dealerList.forEach(dealer -> dealer.setVehicleModel(vehicleModel2));
                Iterator<Dealer> dealeritr = vehicleModel2.getDealerList().listIterator();
                Iterator<Dealer> dealeritr2 = dealerList.listIterator();
                while (dealeritr2.hasNext() && dealeritr.hasNext()) {
                    dealeritr2.next().setDealerId(dealeritr.next().getDealerId());
                }
                getVehicleServiceRecordList.forEach(vehicleServiceRecord -> vehicleServiceRecord.setVehicleModel(vehicleModel2));
                Iterator<VehicleServiceRecord> itr = getVehicleServiceRecordList.listIterator();
                Iterator<VehicleServiceRecord> itr2 = vehicleModel2.getVehicleServiceRecordlist().listIterator();
                while (itr.hasNext() && itr2.hasNext()) {
                    itr.next().setServiceRecordKey(itr2.next().getServiceRecordKey());
                }
                vehicleOwnershipRecord.setVehicleModel(vehicleModel2);
                vehicleOwnershipRecord.setOwnershipRecordKey(vehicleModel2.getVehicleOwnershipRecord().getOwnershipRecordKey());
                vehicleWarrantyInformation.setVehicleModel(vehicleModel2);
                vehicleWarrantyInformation.setVehicleWarrantyPk(vehicleModel2.getVehicleWarrantyInformation().getVehicleWarrantyPk());
                VehicleModel vehicleModel1 = this.svdgRepository.save(vehicleModel);
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public VehicleModel buildAddVehicleRequest(AddVehicleDataRequestDto addVehicleDataRequestDto) {
        return VehicleModel.builder()
                .VIN(addVehicleDataRequestDto.getVIN())
                .Vehicle_Reg_Num(addVehicleDataRequestDto.getVehicle_Reg_Num())
                .Vehicle_Reg_Date(addVehicleDataRequestDto.getVehicle_Reg_Date())
                .Vehicle_Engine_Num(addVehicleDataRequestDto.getVehicle_Engine_Num())
                .Vehicle_Brand(addVehicleDataRequestDto.getVehicle_Brand())
                .Vehicle_Model(addVehicleDataRequestDto.getVehicle_Model())
                .Vehicle_Exterior_Color(addVehicleDataRequestDto.getVehicle_Exterior_Color())
                .Vehicle_Interior_Color(addVehicleDataRequestDto.getVehicle_Interior_Color())
                .Vehicle_Type_ID(addVehicleDataRequestDto.getVehicle_Type_ID())
                .Vehicle_Emission_Class(addVehicleDataRequestDto.getVehicle_Emission_Class())
                .Vehicle_Mileage(addVehicleDataRequestDto.getVehicle_Mileage())
                .Vehicle_Engine_Type(addVehicleDataRequestDto.getVehicle_Engine_Type())
                .Vehicle_Transmission_Type(addVehicleDataRequestDto.getVehicle_Transmission_Type())
                .Vehicle_Price(addVehicleDataRequestDto.getVehicle_Price())
                .Vehicle_Feature_ID(addVehicleDataRequestDto.getVehicle_Feature_ID())
                .Vehicle_Year(addVehicleDataRequestDto.getVehicle_Year())
                .Vehicle_Condition_ID(addVehicleDataRequestDto.getVehicle_Condition_ID())
                .Vehicle_Location_ID(addVehicleDataRequestDto.getVehicle_Location_ID())
                .Vehicle_Status_ID(addVehicleDataRequestDto.getVehicle_Status_ID())
                .dealerList(addVehicleDataRequestDto.getDealerList())
                .vehicleServiceRecordlist(addVehicleDataRequestDto.getVehicleServiceRecordlist())
                .vehicleOwnershipRecord(addVehicleDataRequestDto.getVehicleOwnershipRecord())
                .vehicleWarrantyInformation(addVehicleDataRequestDto.getVehicleWarrantyInformation())
                .build();
    }

    @Override
    public VehicleModel buildVehicleUpdateRequest(UpdateVehicleDataRequestDto updateVehicleDataRequestDto) {
        return VehicleModel.builder()
                .VIN(updateVehicleDataRequestDto.getVIN())
                .Vehicle_Reg_Num(updateVehicleDataRequestDto.getVehicle_Reg_Num())
                .Vehicle_Reg_Date(updateVehicleDataRequestDto.getVehicle_Reg_Date())
                .Vehicle_Engine_Num(updateVehicleDataRequestDto.getVehicle_Engine_Num())
                .Vehicle_Brand(updateVehicleDataRequestDto.getVehicle_Brand())
                .Vehicle_Model(updateVehicleDataRequestDto.getVehicle_Model())
                .Vehicle_Exterior_Color(updateVehicleDataRequestDto.getVehicle_Exterior_Color())
                .Vehicle_Interior_Color(updateVehicleDataRequestDto.getVehicle_Interior_Color())
                .Vehicle_Type_ID(updateVehicleDataRequestDto.getVehicle_Type_ID())
                .Vehicle_Emission_Class(updateVehicleDataRequestDto.getVehicle_Emission_Class())
                .Vehicle_Mileage(updateVehicleDataRequestDto.getVehicle_Mileage())
                .Vehicle_Engine_Type(updateVehicleDataRequestDto.getVehicle_Engine_Type())
                .Vehicle_Transmission_Type(updateVehicleDataRequestDto.getVehicle_Transmission_Type())
                .Vehicle_Price(updateVehicleDataRequestDto.getVehicle_Price())
                .Vehicle_Feature_ID(updateVehicleDataRequestDto.getVehicle_Feature_ID())
                .Vehicle_Year(updateVehicleDataRequestDto.getVehicle_Year())
                .Vehicle_Condition_ID(updateVehicleDataRequestDto.getVehicle_Condition_ID())
                .Vehicle_Location_ID(updateVehicleDataRequestDto.getVehicle_Location_ID())
                .Vehicle_Status_ID(updateVehicleDataRequestDto.getVehicle_Status_ID())
                .dealerList(updateVehicleDataRequestDto.getDealerList())
                .vehicleServiceRecordlist(updateVehicleDataRequestDto.getVehicleServiceRecordlist())
                .vehicleOwnershipRecord(updateVehicleDataRequestDto.getVehicleOwnershipRecord())
                .vehicleWarrantyInformation(updateVehicleDataRequestDto.getVehicleWarrantyInformation())
                .build();
    }

    @Override
    public Boolean isValidAddRequest(AddVehicleDataRequestDto addVehicleDataRequestDto) {
        List<VehicleServiceRecord> serviceRecords = addVehicleDataRequestDto.getVehicleServiceRecordlist();
        Boolean serviceDateCheck = false;
        Boolean registrationDateCheck = true;
        Boolean checkSalePurchaseDate = false;
        if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord())) {
            if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                    checkSalePurchaseDate = addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().after(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate()) || addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().equals(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate());
                    if (serviceRecords != null) {
                        serviceDateCheck = serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())).count() > 0
                                || serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.after(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())).count() > 0 ? false : true;
                    } else {
                        serviceDateCheck = false;
                    }
                } else {
                    if (serviceRecords != null) {
                        serviceDateCheck = serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())).count() > 0
                                || serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.after(new Date())).count() > 0 ? false : true;
                    } else {
                        serviceDateCheck = false;
                    }

                    checkSalePurchaseDate = true;
                }

            } else {
                checkSalePurchaseDate = false;
            }
        }


        boolean warrantyCheck = false;
        if (Objects.nonNull(addVehicleDataRequestDto.getVehicleWarrantyInformation())) {
            if (Objects.nonNull(addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate()) && Objects.nonNull(addVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate())) {
                warrantyCheck = addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().before(addVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate());
                if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord())) {
                    if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                        if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate().after(addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate())) {
                            warrantyCheck = false;

                        }
                    }
                }
            }
        } else {
            warrantyCheck = false;
        }
        if (serviceDateCheck && checkSalePurchaseDate && warrantyCheck) {
            if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord()) && Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                    if (Objects.nonNull(addVehicleDataRequestDto.getVehicle_Reg_Date())) {
                        if (addVehicleDataRequestDto.getVehicle_Reg_Date().before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate()) || addVehicleDataRequestDto.getVehicle_Reg_Date().after(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                            registrationDateCheck = false;
                        }
                    } else {
                        registrationDateCheck = true;
                    }
                } else {
                    if (Objects.nonNull(addVehicleDataRequestDto.getVehicle_Reg_Date())) {
                        if (addVehicleDataRequestDto.getVehicle_Reg_Date().before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                            registrationDateCheck = false;
                        }
                    } else {
                        registrationDateCheck = true;
                    }
                }
            }
        }
        return registrationDateCheck && vehicleServiceUtil.isValidVin(addVehicleDataRequestDto.getVIN()) && serviceDateCheck && checkSalePurchaseDate && addVehicleDataRequestDto.getDealerList() != null
                && addVehicleDataRequestDto.getVehicleServiceRecordlist() != null &&
                Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord()) && warrantyCheck
                && StringUtils.isNotEmpty(addVehicleDataRequestDto.getVehicle_Engine_Num()) &&
                StringUtils.isNotEmpty(addVehicleDataRequestDto.getVehicle_Reg_Num());
    }

    @Override
    public boolean isValidUpdateRequest(UpdateVehicleDataRequestDto updateVehicleDataRequestDto) {
        List<VehicleServiceRecord> serviceRecords = updateVehicleDataRequestDto.getVehicleServiceRecordlist();
        Boolean serviceDateCheck = false;
        Boolean warrantyCheck = false;
        Boolean registrationDateCheck = true;
        if (serviceRecords != null) {
            serviceDateCheck = serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())).count() > 0
                    || serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.after(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())).count() > 0 ? false : true;
        } else {
            return false;
        }
        boolean checkSalePurchaseDate = false;
        if (updateVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
            if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate() != null)) {
                    checkSalePurchaseDate = updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().after(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate()) || updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().equals(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate());
                }
            }
        } else {
            checkSalePurchaseDate = false;
        }

        boolean warrentyCheck = false;
        if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleWarrantyInformation())) {
            warrentyCheck = updateVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().before(updateVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate());
        }

        if (serviceDateCheck && checkSalePurchaseDate && warrantyCheck) {
            if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord()) && Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                    if (Objects.nonNull(updateVehicleDataRequestDto.getVehicle_Reg_Date())) {
                        if (updateVehicleDataRequestDto.getVehicle_Reg_Date().before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate()) || updateVehicleDataRequestDto.getVehicle_Reg_Date().after(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                            registrationDateCheck = false;
                        }
                    } else {
                        registrationDateCheck = true;
                    }
                } else {
                    if (Objects.nonNull(updateVehicleDataRequestDto.getVehicle_Reg_Date())) {
                        if (updateVehicleDataRequestDto.getVehicle_Reg_Date().before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                            registrationDateCheck = false;
                        }
                    } else {
                        registrationDateCheck = true;
                    }
                }
            }
        }

        return vehicleServiceUtil.isValidVin(updateVehicleDataRequestDto.getVIN()) && serviceDateCheck && checkSalePurchaseDate && updateVehicleDataRequestDto.getDealerList() != null
                && updateVehicleDataRequestDto.getVehicleServiceRecordlist() != null &&
                Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord()) && warrentyCheck
                && StringUtils.isNotEmpty(updateVehicleDataRequestDto.getVehicle_Engine_Num()) &&
                StringUtils.isNotEmpty(updateVehicleDataRequestDto.getVehicle_Reg_Num());
    }

    @Override
    public List<VehicleModel> uploadData() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Workbook workbook = WorkbookFactory.create(new File("D:\\NewWorkSpace\\SVDG\\src\\main\\resources\\templates\\SyntheticVehicleData.xlsx"));
        Sheet sheet = workbook.getSheet("VehicleModel");
        Row headRow = sheet.getRow(0);
        Iterator<Cell> cellIterator = headRow.cellIterator();
        List<String> columnHeaders = new ArrayList<>();
        List<VehicleModel> list = new ArrayList<>();
        while (cellIterator.hasNext()) {
            columnHeaders.add(cellIterator.next().getStringCellValue());
        }

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            VehicleModel object = VehicleModel.class.getDeclaredConstructor().newInstance();
            Row row = sheet.getRow(rowIndex);
            for (int cellIndex = 0; cellIndex < columnHeaders.size(); cellIndex++) {
                String columnHeader = columnHeaders.get(cellIndex);
                Field field = VehicleModel.class.getDeclaredField(columnHeader);
                field.setAccessible(true);
                Cell cell = row.getCell(cellIndex);
                if (cell != null) {
                    if (field.getType().getSimpleName().equals("String")) {
                        field.set(object, String.valueOf(cell.getStringCellValue()));
                    } else if (field.getType().getSimpleName().equals("Integer")) {
                        field.set(object, (int) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Double")) {
                        field.set(object, (double) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Long")) {
                        field.set(object, (long) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Date")) {
                        field.set(object, (Date) cell.getDateCellValue());
                    }
                }
            }
            Sheet sheet1 = workbook.getSheet("Dealer");
            Row headRow1 = sheet1.getRow(0);
            Iterator<Cell> cellIterator3 = headRow1.cellIterator();
            List<String> columnHeaders3 = new ArrayList<>();

            List<Dealer> dealerList = new ArrayList<>();
            while (cellIterator3.hasNext()) {
                columnHeaders3.add(cellIterator3.next().getStringCellValue());
            }
            for (int rowIndex2 = 1; rowIndex2 < sheet1.getPhysicalNumberOfRows(); rowIndex2++) {
                Row row2 = sheet1.getRow(rowIndex2);
                Dealer dealer = new Dealer();
                for (int cellIndex = 0; cellIndex < columnHeaders3.size(); cellIndex++) {
                    String dealercolumnHeader = columnHeaders3.get(cellIndex);
                    Field field = Dealer.class.getDeclaredField(dealercolumnHeader);
                    field.setAccessible(true);
                    Cell cell = row2.getCell(cellIndex);
                    if (cell != null) {
                        if (field.getType().getSimpleName().equals("String")) {
                            field.set(dealer, String.valueOf(cell.getStringCellValue()));
                        } else if (field.getType().getSimpleName().equals("Integer")) {
                            field.set(dealer, Integer.valueOf((int) cell.getNumericCellValue()));
                        } else if (field.getType().getSimpleName().equals("Double")) {
                            field.set(dealer, cell.getNumericCellValue());
                        } else if (field.getType().getSimpleName().equals("Long")) {
                            field.set(dealer, (long) cell.getNumericCellValue());
                        } else if (field.getType().getSimpleName().equals("Date")) {
                            field.set(dealer, cell.getDateCellValue());
                        }
                    }
                }
                dealerList.add(dealer);
            }
            Collections.shuffle(dealerList);
            ArrayList<Dealer> dealers = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                dealers.add(dealerList.get(i));
            }
            object.setDealerList(dealers);
            Sheet sheet4 = workbook.getSheet("ServiceRecord");
            Row serviceHeadRow = sheet4.getRow(0);
            Iterator<Cell> cellIterator4 = serviceHeadRow.cellIterator();
            List<String> serviceColumnHeaders = new ArrayList<>();

            List<VehicleServiceRecord> vehicleServiceRecords = new ArrayList<>();
            while (cellIterator4.hasNext()) {
                serviceColumnHeaders.add(cellIterator4.next().getStringCellValue());
            }
            for (int rowIndex2 = 1; rowIndex2 < sheet4.getPhysicalNumberOfRows(); rowIndex2++) {
                Row row2 = sheet4.getRow(rowIndex2);
                VehicleServiceRecord vehicleServiceRecord = new VehicleServiceRecord();
                for (int cellIndex = 0; cellIndex < serviceColumnHeaders.size(); cellIndex++) {
                    String serviceColumnHeader = serviceColumnHeaders.get(cellIndex);
                    Field field = VehicleServiceRecord.class.getDeclaredField(serviceColumnHeader);
                    field.setAccessible(true);
                    Cell cell = row2.getCell(cellIndex);
                    if (cell != null) {
                        if (field.getType().getSimpleName().equals("String")) {
                            field.set(vehicleServiceRecord, String.valueOf(cell.getStringCellValue()));
                        } else if (field.getType().getSimpleName().equals("Integer")) {
                            field.set(vehicleServiceRecord, Integer.valueOf((int) cell.getNumericCellValue()));
                        } else if (field.getType().getSimpleName().equals("Double")) {
                            field.set(vehicleServiceRecord, cell.getNumericCellValue());
                        } else if (field.getType().getSimpleName().equals("Long")) {
                            field.set(vehicleServiceRecord, (long) cell.getNumericCellValue());
                        } else if (field.getType().getSimpleName().equals("Date")) {
                            field.set(vehicleServiceRecord, cell.getDateCellValue());
                        }
                    }
                }
                vehicleServiceRecords.add(vehicleServiceRecord);

            }
            Collections.shuffle(vehicleServiceRecords);
            ArrayList<VehicleServiceRecord> vehicleServiceRecords2 = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                vehicleServiceRecords2.add(vehicleServiceRecords.get(i));
            }
            object.setVehicleServiceRecordlist(vehicleServiceRecords2);
            list.add(object);
        }
        Sheet sheet2 = workbook.getSheet("OwnershipRecord");
        Row headRow2 = sheet2.getRow(0);
        List<VehicleOwnershipRecord> vehicleOwnershipRecords = new ArrayList<>();
        Iterator<Cell> cellIterator2 = headRow2.cellIterator();
        List<String> columnHeaders2 = new ArrayList<>();
        while (cellIterator2.hasNext()) {
            columnHeaders2.add(cellIterator2.next().getStringCellValue());
        }
        for (int rowIndex1 = 1; rowIndex1 < sheet2.getPhysicalNumberOfRows(); rowIndex1++) {
            VehicleOwnershipRecord ownershipRecord = VehicleOwnershipRecord.class.getDeclaredConstructor().newInstance();
            Row row1 = sheet2.getRow(rowIndex1);
            for (int cellIndex = 0; cellIndex < columnHeaders2.size(); cellIndex++) {
                String columnHeader2 = columnHeaders2.get(cellIndex);
                Field field = VehicleOwnershipRecord.class.getDeclaredField(columnHeader2);
                field.setAccessible(true);
                Cell cell = row1.getCell(cellIndex);
                if (cell != null) {
                    if (field.getType().getSimpleName().equals("String")) {
                        field.set(ownershipRecord, String.valueOf(cell.getStringCellValue()));
                    } else if (field.getType().getSimpleName().equals("Integer")) {
                        field.set(ownershipRecord, (int) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Double")) {
                        field.set(ownershipRecord, cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Long")) {
                        field.set(ownershipRecord, (long) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Date")) {
                        field.set(ownershipRecord, cell.getDateCellValue());
                    }
                }
            }
            vehicleOwnershipRecords.add(ownershipRecord);
        }
        for (int i = 0; i < vehicleOwnershipRecords.size() && i < list.size(); i++) {
            list.get(i).setVehicleOwnershipRecord(vehicleOwnershipRecords.get(i));
        }
        Sheet sheet3 = workbook.getSheet("WarrantyInformation");
        Row headRow3 = sheet3.getRow(0);
        List<VehicleWarrantyInformation> warrantyInformations = new ArrayList<>();
        Iterator<Cell> cellIterator3 = headRow3.cellIterator();
        List<String> columnHeaders3 = new ArrayList<>();
        while (cellIterator3.hasNext()) {
            columnHeaders3.add(cellIterator3.next().getStringCellValue());
        }
        for (int rowIndex1 = 1; rowIndex1 < sheet3.getPhysicalNumberOfRows(); rowIndex1++) {
            VehicleWarrantyInformation warrantyInformation = VehicleWarrantyInformation.class.getDeclaredConstructor().newInstance();
            Row sheet3Row = sheet3.getRow(rowIndex1);
            for (int cellIndex = 0; cellIndex < columnHeaders3.size(); cellIndex++) {
                String columnHeader3 = columnHeaders3.get(cellIndex);
                Field field = VehicleWarrantyInformation.class.getDeclaredField(columnHeader3);
                field.setAccessible(true);
                Cell cell = sheet3Row.getCell(cellIndex);
                if (cell != null) {
                    if (field.getType().getSimpleName().equals("String")) {
                        field.set(warrantyInformation, String.valueOf(cell.getStringCellValue()));
                    } else if (field.getType().getSimpleName().equals("Integer")) {
                        field.set(warrantyInformation, (int) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Double")) {
                        field.set(warrantyInformation, cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Long")) {
                        field.set(warrantyInformation, (long) cell.getNumericCellValue());
                    } else if (field.getType().getSimpleName().equals("Date")) {
                        field.set(warrantyInformation, cell.getDateCellValue());
                    }
                }
            }
            warrantyInformations.add(warrantyInformation);
        }
        for (int i = 0; i < list.size() && i < warrantyInformations.size(); i++) {
            list.get(i).setVehicleWarrantyInformation(warrantyInformations.get(i));
        }
        return list;
    }


}
