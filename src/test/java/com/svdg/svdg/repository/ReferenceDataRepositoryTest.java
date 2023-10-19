package com.svdg.svdg.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.svdg.svdg.model.ReferenceData;

@ExtendWith(MockitoExtension.class)
public class ReferenceDataRepositoryTest {

    @Mock
    private ReferenceDataRepository referenceDataRepository;

    @Test
    public void testFindByIdExists() {
        long id = 1L;
        ReferenceData expectedReferenceData = new ReferenceData();

        when(referenceDataRepository.findById(id)).thenReturn(Optional.of(expectedReferenceData));

        Optional<ReferenceData> actualReferenceData = referenceDataRepository.findById(id);

        assertTrue(actualReferenceData.isPresent());
        assertEquals(expectedReferenceData, actualReferenceData.get());
    }

    @Test
    public void testFindByIdNotExists() {
        long id = 2L;

        when(referenceDataRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ReferenceData> actualReferenceData = referenceDataRepository.findById(id);

        assertFalse(actualReferenceData.isPresent());
    }

    @Test
    public void testSaveReferenceData() {
        ReferenceData referenceDataToSave = new ReferenceData();
        ReferenceData savedReferenceData = new ReferenceData();

        when(referenceDataRepository.save(referenceDataToSave)).thenReturn(savedReferenceData);

        ReferenceData actualSavedReferenceData = referenceDataRepository.save(referenceDataToSave);

        assertNotNull(actualSavedReferenceData);
        assertEquals(savedReferenceData, actualSavedReferenceData);
    }

    // Add more test cases for other methods if needed
}
