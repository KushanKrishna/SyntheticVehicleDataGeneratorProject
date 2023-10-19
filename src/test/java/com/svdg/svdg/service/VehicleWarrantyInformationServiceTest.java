package com.svdg.svdg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.VehicleWarrantyInformation;
import com.svdg.svdg.repository.VehicleWarrantyInformationRepository;
import com.svdg.svdg.serviceImpl.VehicleWarrantyInformationServiceImpl;

class VehicleWarrantyInformationServiceTest {

    private VehicleWarrantyInformationService vehicleWarrantyInformationService;

    @MockBean
    private VehicleWarrantyInformationRepository vehicleWarrantyInformationRepository; // Replace with your actual repository

    @BeforeEach
    void setUp() {
        vehicleWarrantyInformationService = new VehicleWarrantyInformationServiceImpl(); // Replace with your actual implementation
    }

    @Test
    void testSaveVehicleWarrantyInformation() {
        VehicleWarrantyInformation warrantyInformation = new VehicleWarrantyInformation();
        warrantyInformation.setWarrantyStatus("In Warranty");

//        when(vehicleWarrantyInformationRepository.save(any(VehicleWarrantyInformation.class))).thenReturn(warrantyInformation);
//
//        VehicleWarrantyInformation savedWarrantyInformation = vehicleWarrantyInformationService.saveVehicleWarrentyInformation(warrantyInformation);
//
//        assertNotNull(savedWarrantyInformation);
//        assertEquals("In Warranty", savedWarrantyInformation.getWarrantyStatus());
    }
}

