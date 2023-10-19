package com.svdg.svdg.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

class VehicleWarrantyInformationTest {

    @Mock
    private VehicleModel vehicleModel;

    @Test
    void testGettersAndSetters() {
        VehicleWarrantyInformation warrantyInfo = new VehicleWarrantyInformation();
        warrantyInfo.setVehicleWarrantyPk(1L);
        warrantyInfo.setVehicleModel(vehicleModel);
        warrantyInfo.setCoverage("Basic Warranty");
        warrantyInfo.setStartDate(new Date());
        warrantyInfo.setEndDate(new Date());
        warrantyInfo.setWarrantyStatus("Active");

        assertEquals(1L, warrantyInfo.getVehicleWarrantyPk());
        assertEquals(vehicleModel, warrantyInfo.getVehicleModel());
        assertEquals("Basic Warranty", warrantyInfo.getCoverage());
        assertNotNull(warrantyInfo.getStartDate());
        assertNotNull(warrantyInfo.getEndDate());
        assertEquals("Active", warrantyInfo.getWarrantyStatus());
    }
}

