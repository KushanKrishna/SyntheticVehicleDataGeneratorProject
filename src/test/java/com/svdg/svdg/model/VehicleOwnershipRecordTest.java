package com.svdg.svdg.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleOwnershipRecordTest {

    @Mock
    private VehicleModel vehicleModel;

    @Test
    public void testGetOwnershipRecordKey() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setOwnershipRecordKey(1L);
        assertEquals(1L, record.getOwnershipRecordKey());
    }

    @Test
    public void testGetVehiclePK() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setVehicleModel(vehicleModel);
        assertEquals(vehicleModel, record.getVehicleModel());
    }

    @Test
    public void testGetOwnedBy() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setOwnedBy("John Doe");
        assertEquals("John Doe", record.getOwnedBy());
    }

    @Test
    public void testGetPurchaseDate() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        Date purchaseDate = new Date();
        record.setPurchaseDate(purchaseDate);
        assertEquals(purchaseDate, record.getPurchaseDate());
    }

    @Test
    public void testGetSaleDate() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        Date saleDate = new Date();
        record.setSaleDate(saleDate);
        assertEquals(saleDate, record.getSaleDate());
    }

    @Test
    public void testToString() {
        VehicleOwnershipRecord record = new VehicleOwnershipRecord();
        record.setOwnershipRecordKey(1L);
        record.setVehicleModel(vehicleModel);
        record.setOwnedBy("John Doe");
        Date purchaseDate = new Date();
        record.setPurchaseDate(purchaseDate);
        Date saleDate = new Date();
        record.setSaleDate(saleDate);

        String expectedToString = "VehicleOwnershipRecord{" +
                "OwnershipRecordKey=1, " +
                "OwnedBy='John Doe', " +
                "PurchaseDate=" + purchaseDate + ", " +
                "SaleDate=" + saleDate +
                '}';

        assertEquals(expectedToString, record.toString());
    }
}
