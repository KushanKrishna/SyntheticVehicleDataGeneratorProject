package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.VehicleWarrantyInformation;
import com.svdg.svdg.repository.VehicleWarrantyInformationRepository;
import com.svdg.svdg.service.VehicleWarrantyInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleWarrantyInformationServiceImpl implements VehicleWarrantyInformationService{

    @Autowired
    VehicleWarrantyInformationRepository vehicleWarrantyInformationRepository;

    @Override
    public VehicleWarrantyInformation saveVehicleWarrentyInformation(VehicleWarrantyInformation vehicleWarrantyInformation) {
        return this.vehicleWarrantyInformationRepository.save(vehicleWarrantyInformation);
    }

    @Override
    public VehicleWarrantyInformation updateVehicleWarrentyInformation(VehicleWarrantyInformation vehicleWarrantyInformation) {
        if(this.vehicleWarrantyInformationRepository.existsById(vehicleWarrantyInformation.getVehicleWarrantyPk())){
          return this.vehicleWarrantyInformationRepository.save(vehicleWarrantyInformation);
        }
        else return null;
    }
}
