package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleServiceRecordRepository extends JpaRepository<VehicleServiceRecord,Long> {
}
