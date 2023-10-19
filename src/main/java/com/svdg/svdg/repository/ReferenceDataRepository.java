package com.svdg.svdg.repository;

import com.svdg.svdg.model.ReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData,Long> {

}
