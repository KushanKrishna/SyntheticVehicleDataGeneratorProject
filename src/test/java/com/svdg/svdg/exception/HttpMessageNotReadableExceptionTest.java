package com.svdg.svdg.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpMessageNotReadableExceptionTest {
    @Test
    void testHttpMessageNotReadableException() {
        String message = "HTTP message not readable";
        HttpMessageNotReadableException httpMessageNotReadableException = new HttpMessageNotReadableException(message);
        assertEquals(message, httpMessageNotReadableException.getMessage());
    }
}
