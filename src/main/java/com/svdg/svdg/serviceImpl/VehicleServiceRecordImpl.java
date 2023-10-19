package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.repository.VehicleServiceRecordRepository;
import com.svdg.svdg.service.VehicleServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceRecordImpl implements VehicleServiceRecordService {

    @Autowired
    VehicleServiceRecordRepository vehicleServiceRecordRepository;
    @Override
    public VehicleServiceRecord saveVehicleServiceRecord(VehicleServiceRecord vehicleServiceRecord) {
        return this.vehicleServiceRecordRepository.save(vehicleServiceRecord);
    }

    @Override
    public List<VehicleServiceRecord> getAllServiceRecord() {
        return this.vehicleServiceRecordRepository.findAll();
    }


    @Override
    public VehicleServiceRecord updateVehicleServiceRecord(VehicleServiceRecord vehicleServiceRecord) {
        if(this.vehicleServiceRecordRepository.existsById(vehicleServiceRecord.getServiceRecordKey())){
            return this.vehicleServiceRecordRepository.save(vehicleServiceRecord);
        }
        else return null;
    }
}
