package com.svdg.svdg.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VinAlreadyExistsExceptionTest {

    @Test
    void testVinAlreadyExistsException() {
        String message = "Vin already exists.";

        VinAlreayExistsException vinAlreayExistsException = new VinAlreayExistsException(message);
        assertEquals(message, vinAlreayExistsException.getMessage());
    }
}
