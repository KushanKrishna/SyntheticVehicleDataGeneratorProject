package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SvdgRepository extends JpaRepository<VehicleModel,Long> , PagingAndSortingRepository<VehicleModel,Long> {

    @Query("select v.VIN from VehicleModel v")
    List<String> findAllVins();
    @Query("select v.Vehicle_Reg_Num from VehicleModel v")
    List<String> findAllRegNo();
    @Query("select v.Vehicle_Engine_Num from VehicleModel v")
    List<String> findAllEngineNo();
    @Query("select v from VehicleModel v where v.VIN = :vin")
    VehicleModel findByVin(String vin);



}
