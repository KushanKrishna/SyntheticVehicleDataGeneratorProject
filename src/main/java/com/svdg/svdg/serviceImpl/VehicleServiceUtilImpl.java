package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.dto.AddVehicleDataFailedResponseDto;
import com.svdg.svdg.dto.AddVehicleDataRequestDto;
import com.svdg.svdg.dto.UpdateVehicleDataFailedResponseDto;
import com.svdg.svdg.dto.UpdateVehicleDataRequestDto;
import com.svdg.svdg.model.VehicleModel;
import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.repository.SvdgRepository;
import com.svdg.svdg.service.VehicleServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class VehicleServiceUtilImpl implements VehicleServiceUtil {

    @Autowired
    SvdgRepository svdgRepository;

    @Override
    public boolean isRegNoPresent(String regNO) {
        List<String> allReg = svdgRepository.findAllRegNo();
        if (allReg.contains(regNO)) return true;
        else return false;

    }

    @Override
    public boolean isVinPresent(String vin) {
        List<String> allVins = svdgRepository.findAllVins();
        if (allVins.contains(vin)) return true;
        else return false;
    }

    @Override
    public boolean isValidVin(String vin) {
        if (Objects.nonNull(vin)) {
            if (vin.length() == 17) {
                Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
                Matcher matcher = pattern.matcher(vin);
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public boolean isEngineNoPresent(String engineNo) {
        List<String> allEngineNo = this.svdgRepository.findAllEngineNo();
        if (allEngineNo.contains(engineNo)) return true;
        else return false;
    }

    @Override
    public VehicleModel getByVin(String vin) {
        if (Objects.nonNull(this.svdgRepository.findByVin(vin))) {
            VehicleModel vehicleModel = this.svdgRepository.findByVin(vin);
            return vehicleModel;
        } else return null;
    }

    @Override
    public AddVehicleDataFailedResponseDto buildFailedResponse(AddVehicleDataRequestDto addVehicleDataRequestDto) {
        AddVehicleDataFailedResponseDto dto = new AddVehicleDataFailedResponseDto();
        Set<String> messageSet = new HashSet<>();

        if (addVehicleDataRequestDto.getVehicle_Reg_Num() == null || addVehicleDataRequestDto.getVehicle_Reg_Num().isEmpty()) {
            messageSet.add("Registration number should not be empty");
        }
        if (addVehicleDataRequestDto.getVehicleWarrantyInformation() != null) {
            if (addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate() != null && addVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate() != null) {
                if (addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().after(addVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate()) || addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().equals(addVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate())) {
                    messageSet.add("Vehicle warranty start date should be less than end date");
                }
                if (addVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
                    if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null) {
                        if (addVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                            messageSet.add("Warranty start date should be greater than or equal to purchase date");
                        }
                    }
                }
            } else {
                messageSet.add("Vehicle warranty start date or end date should not be empty");
            }
        }
        if (addVehicleDataRequestDto.getVehicleServiceRecordlist() == null) {
            messageSet.add("Vehicle service records should not be empty");
        }
        if (!isValidVin(addVehicleDataRequestDto.getVIN())) {
            messageSet.add("Vin is not valid");
        }
        if (isVinPresent(addVehicleDataRequestDto.getVIN())) {
            messageSet.add("VIN already exists in the database");
        }
        if (isEngineNoPresent(addVehicleDataRequestDto.getVehicle_Engine_Num())) {
            messageSet.add("Engine number already present in the database");
        }
        if (isRegNoPresent(addVehicleDataRequestDto.getVehicle_Reg_Num())) {
            messageSet.add("Registration number already present in the database");
        }

        if (addVehicleDataRequestDto.getDealerList() == null || addVehicleDataRequestDto.getDealerList().isEmpty()) {
            messageSet.add("Dealer list should not be empty");
        }
        if (Objects.isNull(addVehicleDataRequestDto.getVehicle_Reg_Date())) {
            messageSet.add("Registration date should not be empty");
        }
        if (Objects.isNull(addVehicleDataRequestDto.getVehicleWarrantyInformation())) {
            messageSet.add("Warranty information should not be empty");
        }
        if (StringUtils.isEmpty(addVehicleDataRequestDto.getVehicle_Reg_Num())) {
            messageSet.add("Registration number should not be empty");
        }
        if (StringUtils.isEmpty(addVehicleDataRequestDto.getVehicle_Engine_Num())) {
            messageSet.add("Engine number should not be empty");
        }
        List<VehicleServiceRecord> serviceRecords = addVehicleDataRequestDto.getVehicleServiceRecordlist();
        if (addVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
            if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null && addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate() != null) {
                if (serviceRecords != null) {
                    if (serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.after(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())).count() > 0 ||
                            serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())).count() > 0) {
                        messageSet.add("Vehicle service record date should be greater than purchase date and lesser than sale date");
                    }
                    if (serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date == null).collect(Collectors.toList()).size() > 0) {
                        messageSet.add("Vehicle service record date should not be empty");
                    }
                }
            }
            if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                if (Objects.nonNull(addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                    if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                        messageSet.add("Vehicle purchase date should be less than sale date");
                    }
                }
            } else {
                messageSet.add("Vehicle purchase date should not be null");
            }
            if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getOwnedBy() == null || addVehicleDataRequestDto.getVehicleOwnershipRecord().getOwnedBy().isEmpty()) {
                messageSet.add("Owner's name should not be blank");
            }
        } else {
            messageSet.add("Owner's record should not be empty");
        }

        if (addVehicleDataRequestDto.getVehicle_Reg_Date() != null) {
            if (addVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
                if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null) {
                    if (addVehicleDataRequestDto.getVehicle_Reg_Date().before(addVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                        messageSet.add("Vehicle registration date should be grater than or equal to purchase date");
                    }
                    if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate() != null) {
                        if (addVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().before(addVehicleDataRequestDto.getVehicle_Reg_Date())) {
                            messageSet.add("Vehicle registration date should be lesser than sale date");
                        }
                    }
                }
            }
        }
        dto.setMessage(messageSet);
        dto.setTimestamp(new Timestamp(new Date().getTime()));
        dto.setStatus(HttpStatus.BAD_REQUEST);
        dto.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return dto;
    }

    @Override
    public UpdateVehicleDataFailedResponseDto buildFailedUpdateResponse(UpdateVehicleDataRequestDto updateVehicleDataRequestDto) {
        UpdateVehicleDataFailedResponseDto dto = new UpdateVehicleDataFailedResponseDto();
        Set<String> messageSet = new HashSet<>();
        if (updateVehicleDataRequestDto.getVehicle_Reg_Num() == null || updateVehicleDataRequestDto.getVehicle_Reg_Num().isEmpty()) {
            messageSet.add("Registration number should not be empty");
        }
        if (updateVehicleDataRequestDto.getVehicleWarrantyInformation() != null) {
            if (updateVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate() != null && updateVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate() != null) {
                if (updateVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().after(updateVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate()) || updateVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().equals(updateVehicleDataRequestDto.getVehicleWarrantyInformation().getEndDate())) {
                    messageSet.add("Vehicle warranty start date should be less than end date");
                }
                if (updateVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
                    if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null) {
                        if (updateVehicleDataRequestDto.getVehicleWarrantyInformation().getStartDate().before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                            messageSet.add("Warranty start date should be greater than or equal to purchase date");
                        }
                    }
                }
            } else {
                messageSet.add("Vehicle warranty start date or end date should not be empty");
            }
        }
        if (updateVehicleDataRequestDto.getVehicleServiceRecordlist() == null) {
            messageSet.add("Vehicle service records should not be empty");
        }
        if (!isValidVin(updateVehicleDataRequestDto.getVIN())) {
            messageSet.add("Vin is not valid");
        }

        if (updateVehicleDataRequestDto.getDealerList() == null || updateVehicleDataRequestDto.getDealerList().isEmpty()) {
            messageSet.add("Dealer list should not be empty");
        }
        if (Objects.isNull(updateVehicleDataRequestDto.getVehicle_Reg_Date())) {
            messageSet.add("Registration date should not be empty");
        }
        if (Objects.isNull(updateVehicleDataRequestDto.getVehicleWarrantyInformation())) {
            messageSet.add("Warranty information should not be empty");
        }
        if (StringUtils.isEmpty(updateVehicleDataRequestDto.getVehicle_Reg_Num())) {
            messageSet.add("Registration number should not be empty");
        }
        if (StringUtils.isEmpty(updateVehicleDataRequestDto.getVehicle_Engine_Num())) {
            messageSet.add("Engine number should not be empty");
        }
        List<VehicleServiceRecord> serviceRecords = updateVehicleDataRequestDto.getVehicleServiceRecordlist();
        if (updateVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
            if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null && updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate() != null) {
                if (serviceRecords != null) {
                    if (serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.after(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())).count() > 0 ||
                            serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date != null).filter(date -> date.before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())).count() > 0) {
                        messageSet.add("Vehicle service record date should be greater than purchase date and lesser than sale date");
                    }
                    if (serviceRecords.stream().map(VehicleServiceRecord::getServiceDate).filter(date -> date == null).collect(Collectors.toList()).size() > 0) {
                        messageSet.add("Vehicle service record date should not be empty");
                    }
                }
            }
            if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                if (Objects.nonNull(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate())) {
                    if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                        messageSet.add("Vehicle purchase date should be less than sale date");
                    }
                }
            } else {
                messageSet.add("Vehicle purchase date should not be null");
            }
            if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getOwnedBy() == null || updateVehicleDataRequestDto.getVehicleOwnershipRecord().getOwnedBy().isEmpty()) {
                messageSet.add("Owner's name should not be blank");
            }
        } else {
            messageSet.add("Owner's record should not be empty");
        }

        if (updateVehicleDataRequestDto.getVehicle_Reg_Date() != null) {
            if (updateVehicleDataRequestDto.getVehicleOwnershipRecord() != null) {
                if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate() != null) {
                    if (updateVehicleDataRequestDto.getVehicle_Reg_Date().before(updateVehicleDataRequestDto.getVehicleOwnershipRecord().getPurchaseDate())) {
                        messageSet.add("Vehicle registration date should be grater than or equal to purchase date");
                    }
                    if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate() != null) {
                        if (updateVehicleDataRequestDto.getVehicleOwnershipRecord().getSaleDate().before(updateVehicleDataRequestDto.getVehicle_Reg_Date())) {
                            messageSet.add("Vehicle registration date should be lesser than sale date");
                        }
                    }
                }
            }
        }

        dto.setMessage(messageSet);
        dto.setTimestamp(new Timestamp(new Date().getTime()));
        dto.setStatus(HttpStatus.BAD_REQUEST);
        dto.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return dto;
    }
}

