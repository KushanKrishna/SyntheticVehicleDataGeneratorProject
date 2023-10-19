package com.svdg.svdg.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.svdg.svdg.model.ReferenceData;
import com.svdg.svdg.repository.ReferenceDataRepository;

public class ReferenceDataServiceImplTest {

    @Mock
    private ReferenceDataRepository referenceDataRepository;

    @InjectMocks
    private ReferenceDataServiceImpl referenceDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReferenceDataExists() {
        long id = 1L;
        ReferenceData expectedReferenceData = new ReferenceData();

        when(referenceDataRepository.existsById(id)).thenReturn(true);
        when(referenceDataRepository.findById(id)).thenReturn(Optional.of(expectedReferenceData));

        ReferenceData actualReferenceData = referenceDataService.getReferenceData(id);

        assertNotNull(actualReferenceData);
        assertEquals(expectedReferenceData, actualReferenceData);
    }

    @Test
    public void testGetReferenceDataNotExists() {
        long id = 2L;

        when(referenceDataRepository.existsById(id)).thenReturn(false);

        ReferenceData actualReferenceData = referenceDataService.getReferenceData(id);

        assertNull(actualReferenceData);
    }

   
}
