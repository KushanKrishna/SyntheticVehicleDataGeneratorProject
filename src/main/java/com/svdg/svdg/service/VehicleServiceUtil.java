package com.svdg.svdg.service;

import com.svdg.svdg.dto.AddVehicleDataFailedResponseDto;
import com.svdg.svdg.dto.AddVehicleDataRequestDto;
import com.svdg.svdg.dto.UpdateVehicleDataFailedResponseDto;
import com.svdg.svdg.dto.UpdateVehicleDataRequestDto;
import com.svdg.svdg.model.VehicleModel;

public interface VehicleServiceUtil {

    boolean isRegNoPresent(String regNO);
    boolean isVinPresent(String vin);
    boolean isValidVin(String vin);
    boolean isEngineNoPresent(String engineNo);
    VehicleModel getByVin(String vin);
    AddVehicleDataFailedResponseDto buildFailedResponse(AddVehicleDataRequestDto addVehicleDataRequestDto);

    UpdateVehicleDataFailedResponseDto buildFailedUpdateResponse(UpdateVehicleDataRequestDto updateVehicleDataRequestDto);
}
