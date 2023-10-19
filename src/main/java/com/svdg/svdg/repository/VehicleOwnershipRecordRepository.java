package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleOwnershipRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleOwnershipRecordRepository extends JpaRepository<VehicleOwnershipRecord,Long> {
}
