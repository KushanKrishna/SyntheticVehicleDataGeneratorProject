package com.svdg.svdg.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.repository.VehicleServiceRecordRepository;
import com.svdg.svdg.service.VehicleServiceRecordService;
import com.svdg.svdg.serviceImpl.VehicleServiceRecordImpl;

class VehicleServiceRecordServiceTest {

    @Autowired
    private VehicleServiceRecordService vehicleServiceRecordService;

    @MockBean
    private VehicleServiceRecordRepository vehicleServiceRecordRepository; // Replace with your actual repository

    @BeforeEach
    void setUp() {
        vehicleServiceRecordService = new VehicleServiceRecordImpl(); // Replace with your actual implementation

    }

    @Test
    void testSaveVehicleServiceRecord() {
        VehicleServiceRecord serviceRecord = new VehicleServiceRecord();
        serviceRecord.setServiceDescription("Maintenance");

//        when(vehicleServiceRecordRepository.save(any(VehicleServiceRecord.class))).thenReturn(serviceRecord);

//        VehicleServiceRecord savedServiceRecord = vehicleServiceRecordService.saveVehicleServiceRecord(serviceRecord);

//        assertNotNull(savedServiceRecord);
//        assertEquals("Maintenance", savedServiceRecord.getServiceDescription());
    }

    @Test
    void testGetAllServiceRecord() {
        List<VehicleServiceRecord> serviceRecords = new ArrayList<>();
        serviceRecords.add(new VehicleServiceRecord());
        serviceRecords.add(new VehicleServiceRecord());

//
      //  List<VehicleServiceRecord> retrievedServiceRecords = vehicleServiceRecordService.getAllServiceRecord();
//        when(vehicleServiceRecordRepository.findAll()).thenReturn(serviceRecords);
//
//        assertNotNull(retrievedServiceRecords);
//        assertEquals(2, retrievedServiceRecords.size());
    }
}

