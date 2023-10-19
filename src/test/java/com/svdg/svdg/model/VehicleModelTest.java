package com.svdg.svdg.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class VehicleModelTest {

    @Test
    void testGettersAndSetters() {
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
        dealer.setGSTIN("312432423l");
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

        assertEquals(1L, vehicleModel.getVehicle_PK());
        assertEquals("ABCDEFGHI12345", vehicleModel.getVIN());
        assertEquals("Reg123", vehicleModel.getVehicle_Reg_Num());
        assertNotNull(vehicleModel.getVehicle_Reg_Date());
        assertEquals("Engine123", vehicleModel.getVehicle_Engine_Num());
        assertEquals("Brand", vehicleModel.getVehicle_Brand());
        assertEquals("Model", vehicleModel.getVehicle_Model());
        assertEquals("Red", vehicleModel.getVehicle_Exterior_Color());
        assertEquals("Black", vehicleModel.getVehicle_Interior_Color());
        assertEquals(2L, vehicleModel.getVehicle_Type_ID());
        assertEquals("Euro 6", vehicleModel.getVehicle_Emission_Class());
        assertEquals(15000.0, vehicleModel.getVehicle_Mileage());
        assertEquals(3L, vehicleModel.getVehicle_Engine_Type());
        assertEquals(4L, vehicleModel.getVehicle_Transmission_Type());
        assertEquals(25000.0, vehicleModel.getVehicle_Price());
        assertEquals(5L, vehicleModel.getVehicle_Feature_ID());
        assertEquals(2022, vehicleModel.getVehicle_Year());
        assertEquals(6L, vehicleModel.getVehicle_Condition_ID());
        assertEquals(7L, vehicleModel.getVehicle_Location_ID());
        assertEquals(8L, vehicleModel.getVehicle_Status_ID());
        assertEquals(dealerList, vehicleModel.getDealerList());
        assertEquals(serviceRecords, vehicleModel.getVehicleServiceRecordlist());
        assertEquals(ownershipRecord, vehicleModel.getVehicleOwnershipRecord());
        assertEquals(warrantyInformation, vehicleModel.getVehicleWarrantyInformation());
    }
}

