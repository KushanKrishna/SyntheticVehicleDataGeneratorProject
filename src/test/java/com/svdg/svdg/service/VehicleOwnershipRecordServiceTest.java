package com.svdg.svdg.service;

import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.serviceImpl.VehicleOwnershipRecordServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleOwnershipRecordServiceTest {

    @Mock
    private VehicleOwnershipRecordService vehicleOwnershipRecordService;

    @InjectMocks
    private VehicleOwnershipRecordServiceImpl vehicleOwnershipRecordServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveVehicleOwnershipRecord() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        when(vehicleOwnershipRecordService.saveVehicleOwnershipRecord(record)).thenReturn(record);

    //    VehicleOwnershipRecord savedRecord = vehicleOwnershipRecordServiceImpl.saveVehicleOwnershipRecord(record);

       // assertEquals(record, savedRecord);
     //   verify(vehicleOwnershipRecordService, times(1)).saveVehicleOwnershipRecord(record);
    }
}
