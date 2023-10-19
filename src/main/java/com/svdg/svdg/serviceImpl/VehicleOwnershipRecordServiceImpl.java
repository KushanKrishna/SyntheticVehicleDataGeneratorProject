package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.repository.VehicleOwnershipRecordRepository;
import com.svdg.svdg.service.VehicleOwnershipRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleOwnershipRecordServiceImpl implements VehicleOwnershipRecordService {
    @Autowired
    VehicleOwnershipRecordRepository vehicleOwnershipRecordRepository;

    @Override
    public VehicleOwnershipRecord saveVehicleOwnershipRecord(VehicleOwnershipRecord vehicleOwnershipRecord) {
        return this.vehicleOwnershipRecordRepository.save(vehicleOwnershipRecord);
    }

    @Override
    public VehicleOwnershipRecord updateVehicleOwnershipRecord(VehicleOwnershipRecord vehicleOwnershipRecord) {
        if(this.vehicleOwnershipRecordRepository.existsById(vehicleOwnershipRecord.getOwnershipRecordKey())){
            return this.vehicleOwnershipRecordRepository.save(vehicleOwnershipRecord);
        }
        else return null;
    }
}
