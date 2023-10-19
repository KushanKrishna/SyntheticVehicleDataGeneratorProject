package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.repository.VehicleOwnershipRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleOwnershipRecordServiceImplTest {

    @Mock
    private VehicleOwnershipRecordRepository vehicleOwnershipRecordRepository;

    @InjectMocks
    private VehicleOwnershipRecordServiceImpl vehicleOwnershipRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveVehicleOwnershipRecord() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        when(vehicleOwnershipRecordRepository.save(record)).thenReturn(record);

        VehicleOwnershipRecord savedRecord = vehicleOwnershipRecordService.saveVehicleOwnershipRecord(record);

        assertEquals(record, savedRecord);
        verify(vehicleOwnershipRecordRepository, times(1)).save(record);
    }
}
