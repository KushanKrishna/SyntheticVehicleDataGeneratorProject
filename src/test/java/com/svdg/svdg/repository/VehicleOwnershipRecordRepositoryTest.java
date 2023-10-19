package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.serviceImpl.VehicleOwnershipRecordServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleOwnershipRecordRepositoryTest {

    @Mock
    private VehicleOwnershipRecordRepository vehicleOwnershipRecordRepository;

    @InjectMocks
    private VehicleOwnershipRecordServiceImpl repository;

    @Test
    public void testFindById() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setOwnershipRecordKey(1L);
      
    }

    @Test
    public void testSave() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setOwnershipRecordKey(1L);
     
    }
}
