package com.svdg.svdg.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.svdg.svdg.dto.AddVehicleDataRequestDto;
import com.svdg.svdg.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.svdg.svdg.constraints.Constraints;
import com.svdg.svdg.exception.HttpMessageNotReadableException;
import com.svdg.svdg.exception.RegNoAlreadyExistsException;
import com.svdg.svdg.exception.VinAlreayExistsException;
import com.svdg.svdg.model.Dealer;
import com.svdg.svdg.model.VehicleModel;
import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.model.VehicleWarrantyInformation;

class VehicleControllerTest {

    @Mock
    private VehicleServiceUtil vehicleServiceUtil;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVehicleData() throws HttpMessageNotReadableException, VinAlreayExistsException, RegNoAlreadyExistsException, SQLException {

    }


    @Test
    void testGetVehicleData() {
        int page = 0;
        int limit = 10;
        List<VehicleModel> vehicleModels = new ArrayList<>();
        vehicleModels.add(new VehicleModel());
        when(vehicleService.getAllVehicleData(page, limit)).thenReturn(vehicleModels);
        ResponseEntity<List<VehicleModel>> responseEntity = vehicleController.getVehicleData(page, limit);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(vehicleModels);
    }

}
