package com.svdg.svdg.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.svdg.svdg.model.VehicleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.repository.VehicleServiceRecordRepository;
import com.svdg.svdg.service.VehicleServiceRecordService;

@SpringBootTest
class VehicleServiceRecordImplTest {

    @Autowired
    private VehicleServiceRecordService vehicleServiceRecordService;
    @Mock
    private VehicleModel vehicleModel;
    @MockBean
    private VehicleServiceRecordRepository vehicleServiceRecordRepository;

    @BeforeEach
    void setUp() {
        // Mock the behavior of vehicleServiceRecordRepository
        VehicleServiceRecord serviceRecord = new VehicleServiceRecord();
        serviceRecord.setVehicleModel(vehicleModel);
        serviceRecord.setServiceDescription("Maintenance");

        List<VehicleServiceRecord> serviceRecords = new ArrayList<>();
        serviceRecords.add(serviceRecord);

        when(vehicleServiceRecordRepository.save(any(VehicleServiceRecord.class))).thenReturn(serviceRecord);
        when(vehicleServiceRecordRepository.findAll()).thenReturn(serviceRecords);
    }

    @Test
    void testSaveVehicleServiceRecord() {
        VehicleServiceRecord serviceRecord = new VehicleServiceRecord();
        serviceRecord.setServiceDescription("Maintenance");

        VehicleServiceRecord savedServiceRecord = vehicleServiceRecordService.saveVehicleServiceRecord(serviceRecord);

        verify(vehicleServiceRecordRepository, times(1)).save(serviceRecord);
        assertNotNull(savedServiceRecord);
        assertEquals("Maintenance", savedServiceRecord.getServiceDescription());
    }

    @Test
    void testGetAllServiceRecord() {
        List<VehicleServiceRecord> serviceRecords = vehicleServiceRecordService.getAllServiceRecord();

        verify(vehicleServiceRecordRepository, times(1)).findAll();
        assertNotNull(serviceRecords);
        assertEquals(1, serviceRecords.size());
    }
}

