package com.svdg.svdg.controller;

import com.svdg.svdg.constraints.Constraints;
import com.svdg.svdg.dto.*;
import com.svdg.svdg.exception.*;
import com.svdg.svdg.model.*;
import com.svdg.svdg.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/svdg/")
public class VehicleController {

    //    @Autowired
//    private ReferenceDataService referenceDataService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleServiceUtil vehicleServiceUtil;

    @PostMapping("/saveVehicle")
    public ResponseEntity<?> saveVehicleData(@Valid @RequestBody AddVehicleDataRequestDto addVehicleDataRequestDto) throws HttpMessageNotReadableException, VinAlreayExistsException, RegNoAlreadyExistsException, SQLException {
        AddVehicleDataResponseDto addVehicleDataResponseDto = new AddVehicleDataResponseDto();
        if (this.vehicleService.isValidAddRequest(addVehicleDataRequestDto)) {
            VehicleModel vehicleModel = this.vehicleService.buildAddVehicleRequest(addVehicleDataRequestDto);
            VehicleModel vehicleModel1 = this.vehicleService.saveVehicle(vehicleModel);
            if (Objects.nonNull(vehicleModel1)) {
                addVehicleDataResponseDto.setMessage(Constraints.VEHICLE_ADDED);
                addVehicleDataResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
                addVehicleDataResponseDto.setStatus("Success");
                addVehicleDataResponseDto.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(addVehicleDataResponseDto, HttpStatus.OK);
            } else {
                addVehicleDataResponseDto.setMessage(Constraints.VEHICLE_DATA_NOT_ADDED);
                addVehicleDataResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
                addVehicleDataResponseDto.setStatus("Fail");
                addVehicleDataResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(addVehicleDataResponseDto, HttpStatus.BAD_REQUEST);
            }
        } else {
            AddVehicleDataFailedResponseDto addVehicleDataFailedResponseDto = this.vehicleServiceUtil.buildFailedResponse(addVehicleDataRequestDto);
            return new ResponseEntity<>(addVehicleDataFailedResponseDto, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getAllVehicleData")
    public ResponseEntity<List<VehicleModel>> getVehicleData(@RequestParam(value = "page", required = false) Integer page,
                                                             @RequestParam(value = "limit", required = false) Integer limit) {
        long startTime = System.currentTimeMillis();
        Integer pageValue = Objects.isNull(page) || page <= 0 ? 0 : page;
        Integer limitValue = Objects.isNull(limit) || limit <= 0 ? 10 : limit;
        List<VehicleModel> vehicleModels = this.vehicleService.getAllVehicleData(pageValue, limitValue);
        System.out.println(System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(vehicleModels, HttpStatus.OK);

    }

//    @GetMapping("/getReferenceData")
//    public ResponseEntity<ReferenceData> getReferenceData(@RequestParam("id") Long id) {
//        ReferenceData referenceData = referenceDataService.getReferenceData(id);
//        if (referenceData != null)
//            return new ResponseEntity<>(referenceData, HttpStatus.OK);
//        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/deleteData")
    public ResponseEntity<?> deleteVehicleData(@RequestParam String vin) throws VinNotValidException {
        DeleteVehicleDataResponseDto deleteVehicleDataResponseDto = new DeleteVehicleDataResponseDto();
        Boolean bool = this.vehicleService.deleteData(vin);
        if (bool.equals(true)) {
            deleteVehicleDataResponseDto.setMessage("Vehicle data deleted successfully!");
            deleteVehicleDataResponseDto.setStatus("success");
            deleteVehicleDataResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
            deleteVehicleDataResponseDto.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(deleteVehicleDataResponseDto, HttpStatus.OK);
        } else {
            throw new VinNotValidException(vin);
        }
    }

    @GetMapping("/getVehicleDataByVin/{vin}")
    public ResponseEntity<?> getDataByVin(@PathVariable String vin) throws VinNotValidException {
        VehicleModel vehicleModel = this.vehicleServiceUtil.getByVin(vin);
        if (Objects.nonNull(vehicleModel)) {
            return new ResponseEntity<>(vehicleModel, HttpStatus.OK);
        } else {
            throw new VinNotValidException("Enter valid Vin");
        }
    }

    @PutMapping("/updateVehicleData/{vin}")
    public ResponseEntity<?> updateVehicleData(@Valid @PathVariable("vin") String vin, @RequestBody UpdateVehicleDataRequestDto updateVehicleDataRequestDto) throws VinNotValidException {
        long startTime = System.currentTimeMillis();
        UpdateVehicleDataResponseDto updateVehicleDataResponseDto = new UpdateVehicleDataResponseDto();
        if (this.vehicleServiceUtil.isVinPresent(vin)) {
            if (this.vehicleService.isValidUpdateRequest(updateVehicleDataRequestDto)) {
                VehicleModel vehicleModel = this.vehicleService.buildVehicleUpdateRequest(updateVehicleDataRequestDto);
                Boolean bool = this.vehicleService.updateData(vin, vehicleModel);
                if (bool.equals(true)) {
                    updateVehicleDataResponseDto.setMessage(Constraints.VEHICLE_DATA_UPDATED);
                    updateVehicleDataResponseDto.setStatus("success");
                    updateVehicleDataResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
                    updateVehicleDataResponseDto.setStatusCode(HttpStatus.OK.value());
                    System.out.println(System.currentTimeMillis() - startTime);
                    return new ResponseEntity<>(updateVehicleDataResponseDto, HttpStatus.OK);
                } else {
                    updateVehicleDataResponseDto.setMessage(Constraints.VEHICLE_DATA_NOT_UPDATED);
                    updateVehicleDataResponseDto.setStatus("fail");
                    updateVehicleDataResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
                    updateVehicleDataResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    System.out.println(System.currentTimeMillis() - startTime);
                    return new ResponseEntity<>(updateVehicleDataResponseDto, HttpStatus.BAD_REQUEST);
                }

            } else {
                UpdateVehicleDataFailedResponseDto dto = this.vehicleServiceUtil.buildFailedUpdateResponse(updateVehicleDataRequestDto);
                return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
            }
        } else throw new VinNotValidException(vin + " is not valid");
    }

    //    @PostMapping("/uploadData")
//    public ResponseEntity<List<VehicleModel>> excelToObjConvertor() throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
//        Long startTime = System.currentTimeMillis();
//        List<VehicleModel> list = this.vehicleService.uploadData();
//
//        for(VehicleModel vehicleModel: list){
//            this.vehicleService.saveVehicle(vehicleModel);
//        }
//        System.out.println(System.currentTimeMillis()-startTime);
//            return new ResponseEntity<>(list,HttpStatus.OK);
//    }

    @GetMapping("/getVehicleData/{limit}")
    public ResponseEntity<List<VehicleModel>> getVehicleData(@PathVariable("limit") Integer limit) {
        Long startTime = System.currentTimeMillis();

        if (limit >= 0) {
            List<VehicleModel> vehicleData = this.vehicleService.getVehicleData(limit);
            System.out.println(System.currentTimeMillis() - startTime);
            return new ResponseEntity<>(vehicleData, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
