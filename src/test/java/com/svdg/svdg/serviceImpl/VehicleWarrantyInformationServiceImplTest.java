package com.svdg.svdg.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.VehicleWarrantyInformation;
import com.svdg.svdg.repository.VehicleWarrantyInformationRepository;
import com.svdg.svdg.service.VehicleWarrantyInformationService;

@SpringBootTest
class VehicleWarrantyInformationServiceImplTest {

    @Autowired
    private VehicleWarrantyInformationService vehicleWarrantyInformationService;

    @MockBean
    private VehicleWarrantyInformationRepository vehicleWarrantyInformationRepository;

    @BeforeEach
    void setUp() {
        // Mock the behavior of vehicleWarrantyInformationRepository
        VehicleWarrantyInformation warrantyInformation = new VehicleWarrantyInformation();
        warrantyInformation.setVehicleWarrantyPk(1);;
        warrantyInformation.setWarrantyStatus("In Warranty");

        when(vehicleWarrantyInformationRepository.save(any(VehicleWarrantyInformation.class))).thenReturn(warrantyInformation);
    }

    @Test
    void testSaveVehicleWarrantyInformation() {
        VehicleWarrantyInformation warrantyInformation = new VehicleWarrantyInformation();
        warrantyInformation.setWarrantyStatus("In Warranty");

        VehicleWarrantyInformation savedWarrantyInformation = vehicleWarrantyInformationService.saveVehicleWarrentyInformation(warrantyInformation);

        verify(vehicleWarrantyInformationRepository, times(1)).save(warrantyInformation);
        assertNotNull(savedWarrantyInformation);
        assertEquals("In Warranty", savedWarrantyInformation.getWarrantyStatus());
    }
}
