package com.svdg.svdg.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegNoAleadyExistsExceptionTest {
    @Test
    void testRegNoAlreadyExistsException() {
        String message = "Registration number already exists.";
        RegNoAlreadyExistsException regNoAlreadyExistsException = new RegNoAlreadyExistsException(message);
        assertEquals(message, regNoAlreadyExistsException.getMessage());
    }
}
