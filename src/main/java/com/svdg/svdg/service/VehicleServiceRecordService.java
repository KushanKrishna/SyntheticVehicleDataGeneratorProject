package com.svdg.svdg.service;

import com.svdg.svdg.model.VehicleServiceRecord;

import java.util.List;

public interface VehicleServiceRecordService {

    public VehicleServiceRecord saveVehicleServiceRecord(VehicleServiceRecord vehicleServiceRecord);

    public List<VehicleServiceRecord> getAllServiceRecord();

//    public List<VehicleServiceRecord> getByVehiclePk(long pk);

    public VehicleServiceRecord updateVehicleServiceRecord(VehicleServiceRecord vehicleServiceRecord);

}
