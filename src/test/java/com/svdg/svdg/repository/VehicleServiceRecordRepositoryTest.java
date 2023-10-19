package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleServiceRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleServiceRecordRepositoryTest {

    @Mock
    private VehicleServiceRecordRepository vehicleServiceRecordRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveVehicleServiceRecord() {
        VehicleServiceRecord record = new VehicleServiceRecord();
        when(vehicleServiceRecordRepository.save(record)).thenReturn(record);

        VehicleServiceRecord savedRecord = vehicleServiceRecordRepository.save(record);

        assertEquals(record, savedRecord);
        verify(vehicleServiceRecordRepository, times(1)).save(record);
    }
}
