package com.svdg.svdg.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class GlobalExceptionHandlerTest {
    @Test
    public void testHandleVinAlreadyPresentException() {
        VinAlreayExistsException ex = new VinAlreayExistsException("VIN already exists");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleVinAlreadyPresentException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testHandleRegNoAlreadyExistsException() {
        RegNoAlreadyExistsException ex = new RegNoAlreadyExistsException("Registration number already exists");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleRegNoAlreadyExistsException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testHandleVinNotValidException() {
        VinNotValidException ex = new VinNotValidException("Invalid VIN");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleVinNotValidException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Add more assertions if needed
    }

    @Test
    public void testHandleSQLException() {
        SQLException ex = new SQLException("Database error");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleSQLException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testHandleParseException() {
       ParseException ex = new ParseException("Parsing error", 0);
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
       ResponseEntity<?> response = handler.handleParseException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Message not readable");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleHttpMessageNotReadableException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void testHandleEngineNoAlreadyPresentException() {
        EngineNoAlreadyPresentException ex = new EngineNoAlreadyPresentException("Engine number already exists");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<?> response = handler.handleEngineNoAlreadyPresentException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }


}
