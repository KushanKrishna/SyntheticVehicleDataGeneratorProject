package com.svdg.svdg.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.svdg.svdg.model.VehicleServiceRecord;
import org.mockito.Mock;

import java.util.Date;

class VehicleServiceRecordTest {

    @Mock
    private VehicleModel vehicleModel;

    @Test
    void testGettersAndSetters() {
        VehicleServiceRecord serviceRecord = new VehicleServiceRecord();
        serviceRecord.setServiceRecordKey(1L);
        serviceRecord.setVehicleModel(vehicleModel);
        serviceRecord.setServiceDescription("Oil Change");
        serviceRecord.setServiceDate(new Date());

        assertEquals(1L, serviceRecord.getServiceRecordKey());
        assertEquals(vehicleModel, serviceRecord.getVehicleModel());
        assertEquals("Oil Change", serviceRecord.getServiceDescription());
        assertNotNull(serviceRecord.getServiceDate());
    }

    @Test
    void testToString() {
        VehicleServiceRecord serviceRecord = new VehicleServiceRecord();
        serviceRecord.setServiceRecordKey(1L);
        serviceRecord.setVehicleModel(vehicleModel);
        serviceRecord.setServiceDescription("Oil Change");
        serviceRecord.setServiceDate(new Date());

        String expectedToString = "VehicleServiceRecord{" +
                "ServiceRecordKey=1, " +
                "ServiceDescription='Oil Change', " +
                "ServiceDate=" + serviceRecord.getServiceDate() +
                '}';

        assertEquals(expectedToString, serviceRecord.toString());
    }
}
