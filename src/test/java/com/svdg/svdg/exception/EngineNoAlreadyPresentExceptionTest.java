package com.svdg.svdg.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineNoAlreadyPresentExceptionTest {

    @Test
    void testEngineNoAlreadyPresentException() {
        String message = "Engine number already present.";
        EngineNoAlreadyPresentException engineNoAlreadyPresentException = new EngineNoAlreadyPresentException(message);
        assertEquals(message, engineNoAlreadyPresentException.getMessage());
    }
}
