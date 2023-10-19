package com.svdg.svdg.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VinNotValidExceptionTest {

    @Test
    void testVinNotValidException() {
        String message = "Vin is not valid";
        VinNotValidException vinNotValidException = new VinNotValidException(message);
        assertEquals(message, vinNotValidException.getMessage());
    }
}
