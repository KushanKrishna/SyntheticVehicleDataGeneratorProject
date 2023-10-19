package com.svdg.svdg.repository;

import com.svdg.svdg.model.VehicleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SvdgRepositoryTest {

    @Mock
    private SvdgRepository svdgRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllVins() {
        List<String> vinList = new ArrayList<>();
        when(svdgRepository.findAllVins()).thenReturn(vinList);

        List<String> resultList = svdgRepository.findAllVins();

        assertEquals(vinList, resultList);
        verify(svdgRepository, times(1)).findAllVins();
    }

    @Test
    public void testFindAllRegNo() {
        List<String> regNoList = new ArrayList<>();
        when(svdgRepository.findAllRegNo()).thenReturn(regNoList);

        List<String> resultList = svdgRepository.findAllRegNo();

        assertEquals(regNoList, resultList);
        verify(svdgRepository, times(1)).findAllRegNo();
    }

    @Test
    public void testFindAllEngineNo() {
        List<String> engineNoList = new ArrayList<>();
        when(svdgRepository.findAllEngineNo()).thenReturn(engineNoList);

        List<String> resultList = svdgRepository.findAllEngineNo();

        assertEquals(engineNoList, resultList);
        verify(svdgRepository, times(1)).findAllEngineNo();
    }
}
