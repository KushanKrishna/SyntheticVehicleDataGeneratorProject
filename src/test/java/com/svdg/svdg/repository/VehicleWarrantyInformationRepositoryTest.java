package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleWarrantyInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleWarrantyInformationRepositoryTest {

    @Mock
    private VehicleWarrantyInformationRepository vehicleWarrantyInformationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveVehicleWarrantyInformation() {
        VehicleWarrantyInformation warrantyInfo = new VehicleWarrantyInformation();
        when(vehicleWarrantyInformationRepository.save(warrantyInfo)).thenReturn(warrantyInfo);

        VehicleWarrantyInformation savedWarrantyInfo = vehicleWarrantyInformationRepository.save(warrantyInfo);

        assertEquals(warrantyInfo, savedWarrantyInfo);
        verify(vehicleWarrantyInformationRepository, times(1)).save(warrantyInfo);
    }
}
