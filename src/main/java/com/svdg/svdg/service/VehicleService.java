package com.svdg.svdg.service;

import com.svdg.svdg.dto.AddVehicleDataRequestDto;
import com.svdg.svdg.dto.UpdateVehicleDataRequestDto;
import com.svdg.svdg.model.VehicleModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface VehicleService {
     VehicleModel saveVehicle( VehicleModel vehicleModel) throws SQLException;
     List<VehicleModel> getVehicleData(Integer limit);
     List<VehicleModel> getAllVehicleData(Integer page, Integer limit);
     boolean deleteData(String vin);
     boolean updateData(String vin,VehicleModel vehicleModel);
     public VehicleModel buildAddVehicleRequest(AddVehicleDataRequestDto addVehicleDataRequestDto);
     VehicleModel buildVehicleUpdateRequest(UpdateVehicleDataRequestDto updateVehicleDataRequestDto);

     Boolean isValidAddRequest(AddVehicleDataRequestDto addVehicleDataRequestDto);

     List<VehicleModel> uploadData() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException;


     boolean isValidUpdateRequest(UpdateVehicleDataRequestDto updateVehicleDataRequestDto);
}
