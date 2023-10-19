package com.svdg.svdg.service;

import com.svdg.svdg.model.VehicleModel;
import com.svdg.svdg.serviceImpl.VehicleServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VehicleServiceTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleServiceImpl vehicleServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
   }

    @Test
    public void testSaveVehicle() throws SQLException {
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleService.saveVehicle(vehicleModel)).thenReturn(vehicleModel);

//        VehicleModel savedVehicle = vehicleServiceImpl.saveVehicle(vehicleModel);
//
//        assertEquals(vehicleModel, savedVehicle);
//        verify(vehicleService, times(1)).saveVehicle(vehicleModel);
    }



}
