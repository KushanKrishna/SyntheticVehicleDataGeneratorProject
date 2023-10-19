package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleWarrantyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleWarrantyInformationRepository extends JpaRepository<VehicleWarrantyInformation,Long> {


}
