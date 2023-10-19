package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.ReferenceData;
import com.svdg.svdg.repository.ReferenceDataRepository;
import com.svdg.svdg.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {

    @Autowired
    private ReferenceDataRepository referenceDataRepository;

    @Override
    public ReferenceData getReferenceData(Long id) {
        if (this.referenceDataRepository.existsById(id)) {
            ReferenceData obj = this.referenceDataRepository.findById(id).get();

            if (obj != null) {
                return obj;
            }
        } else return null;
        return null;
    }
}
