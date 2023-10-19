package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.VehicleModel;
import com.svdg.svdg.repository.SvdgRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class VehicleServiceUtilImplTest {
    @Mock
    private SvdgRepository svdgRepository;

    @InjectMocks
    private VehicleServiceUtilImpl vehicleServiceUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testIsRegNoPresent() {
        when(svdgRepository.findAllRegNo()).thenReturn(new ArrayList<>());
        boolean result = vehicleServiceUtil.isRegNoPresent("ABC123");
        assertFalse(result);
    }

    @Test
    public void testIsVinPresent() {
        when(svdgRepository.findAllVins()).thenReturn(new ArrayList<>());
        boolean result = vehicleServiceUtil.isVinPresent("123456789");
        assertFalse(result);
    }

    @Test
    public void testIsValidVin() {
        assertTrue(vehicleServiceUtil.isValidVin("1234567890ABCDEFG"));
        assertFalse(vehicleServiceUtil.isValidVin("InvalidVin"));
    }

    @Test
    public void testIsEngineNoPresent() {
        when(svdgRepository.findAllEngineNo()).thenReturn(new ArrayList<>());
        boolean result = vehicleServiceUtil.isEngineNoPresent("ENGINE123");
        assertFalse(result);
    }

}
