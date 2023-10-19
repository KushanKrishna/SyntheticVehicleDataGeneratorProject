package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.*;
import com.svdg.svdg.repository.SvdgRepository;
import com.svdg.svdg.service.VehicleServiceUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehicleServiceImplTest {


    @Mock
    private SvdgRepository svdgRepository;

    @Mock
    private VehicleServiceUtil vehicleServiceUtil;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveVehicle() throws SQLException {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setVehicle_PK(1L);
        vehicleModel.setVIN("ABCDEFGHI12345");
        vehicleModel.setVehicle_Reg_Num("Reg123");
        vehicleModel.setVehicle_Reg_Date(new Date());
        vehicleModel.setVehicle_Engine_Num("Engine123");
        vehicleModel.setVehicle_Brand("Brand");
        vehicleModel.setVehicle_Model("Model");
        vehicleModel.setVehicle_Exterior_Color("Red");
        vehicleModel.setVehicle_Interior_Color("Black");
        vehicleModel.setVehicle_Type_ID(2L);
        vehicleModel.setVehicle_Emission_Class("Euro 6");
        vehicleModel.setVehicle_Mileage(15000.0);
        vehicleModel.setVehicle_Engine_Type(3L);
        vehicleModel.setVehicle_Transmission_Type(4L);
        vehicleModel.setVehicle_Price(25000.0);
        vehicleModel.setVehicle_Feature_ID(5L);
        vehicleModel.setVehicle_Year(2022);
        vehicleModel.setVehicle_Condition_ID(6L);
        vehicleModel.setVehicle_Location_ID(7L);
        vehicleModel.setVehicle_Status_ID(8L);

        Dealer dealer = new Dealer();
        dealer.setVehicleModel(vehicleModel);
        dealer.setDealerId(1L);
        dealer.setDealer("Dealer 1");
        dealer.setAddress("Nagpur");
        dealer.setGSTIN("54234234l");
        dealer.setTurnoverRatio(0.04);
        List<Dealer> dealerList = new ArrayList<>();
        dealerList.add(dealer);
        vehicleModel.setDealerList(dealerList);

        VehicleServiceRecord vehicleServiceRecord = new VehicleServiceRecord();
        vehicleServiceRecord.setServiceDescription("Maintainence");
        vehicleServiceRecord.setServiceDate(new Date());
        vehicleServiceRecord.setVehicleModel(vehicleModel);
        vehicleServiceRecord.setServiceRecordKey(1L);

        List<VehicleServiceRecord> serviceRecords = new ArrayList<>();
        serviceRecords.add(vehicleServiceRecord);
        vehicleModel.setVehicleServiceRecordlist(serviceRecords);

        VehicleOwnershipRecord ownershipRecord = new VehicleOwnershipRecord();
        ownershipRecord.setVehicleModel(vehicleModel);
        ownershipRecord.setOwnershipRecordKey(1L);
        ownershipRecord.setOwnedBy("Owner A");
        ownershipRecord.setPurchaseDate(new Date());
        ownershipRecord.setSaleDate(new Date());
        vehicleModel.setVehicleOwnershipRecord(ownershipRecord);

        VehicleWarrantyInformation warrantyInformation = new VehicleWarrantyInformation();
        warrantyInformation.setVehicleModel(vehicleModel);
        warrantyInformation.setVehicleWarrantyPk(1L);
        warrantyInformation.setWarrantyStatus("In Warrenty");
        warrantyInformation.setCoverage("All Parts");
        warrantyInformation.setEndDate(new Date());
        warrantyInformation.setStartDate(new Date());
        vehicleModel.setVehicleWarrantyInformation(warrantyInformation);


        when(svdgRepository.save(vehicleModel)).thenReturn(vehicleModel);
        when(vehicleServiceUtil.isValidVin(vehicleModel.getVIN())).thenReturn(true);

        VehicleModel savedVehicle = vehicleService.saveVehicle(vehicleModel);

        assertEquals(vehicleModel, savedVehicle);
        verify(svdgRepository, times(1)).save(vehicleModel);
    }



    @Test
    public void testGetAllVehicleData() {
        Page<VehicleModel> mockPage = mock(Page.class);
        List<VehicleModel> mockList = new ArrayList<>();
        when(mockPage.stream()).thenReturn(mockList.stream());
        when(svdgRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<VehicleModel> resultList = vehicleService.getAllVehicleData(1, 10);

        assertEquals(mockList, resultList);
        verify(svdgRepository, times(1)).findAll(PageRequest.of(1, 10));
    }
}

