package com.svdg.svdg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.svdg.svdg.model.ReferenceData;
import com.svdg.svdg.repository.ReferenceDataRepository;
import com.svdg.svdg.serviceImpl.ReferenceDataServiceImpl;

public class ReferenceDataServiceTest {

    @Mock
    private ReferenceDataRepository referenceDataRepository;

    @InjectMocks
    private ReferenceDataServiceImpl referenceDataService;

    @BeforeEach
    public void setUp() {


        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReferenceData() {
        long id = 1L;
        ReferenceData expectedReferenceData = new ReferenceData();

        when(referenceDataRepository.existsById(id)).thenReturn(true);
        when(referenceDataRepository.findById(id)).thenReturn(Optional.of(expectedReferenceData));

        ReferenceData actualReferenceData = referenceDataService.getReferenceData(id);

        assertEquals(expectedReferenceData, actualReferenceData);
    }

    @Test
    public void testGetReferenceDataNotFound() {
        long id = 2L;

        when(referenceDataRepository.existsById(id)).thenReturn(false);

        ReferenceData actualReferenceData = referenceDataService.getReferenceData(id);

        assertEquals(null, actualReferenceData);
    }
}
