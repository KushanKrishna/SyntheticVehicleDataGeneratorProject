package com.svdg.svdg.exception;


import com.svdg.svdg.constraints.Constraints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {
    @ExceptionHandler(value = VinAlreayExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleVinAlreadyPresentException(VinAlreayExistsException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", ex.getMessage());
        map.put("cause", Constraints.VIN_ALREADY_PRESENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = RegNoAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleRegNoAlreadyExistsException(RegNoAlreadyExistsException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message",ex.getMessage());
        map.put("cause", Constraints.REG_ALREADY_PRESENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = VinNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleVinNotValidException(VinNotValidException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", ex.getMessage());
        map.put("cause", Constraints.VIN_NOT_VALID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", ex.getMessage());
//        map.put("cause", ex.getCause());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(value = ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleParseException(ParseException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", ex.getMessage());
        map.put("cause", ex.getCause());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "HTTP MESSAGE NOT READABLE")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", Constraints.MSG_NOT_READABLE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ENGINE NUMBER ALREADY EXISTS")
    @ExceptionHandler(EngineNoAlreadyPresentException.class)
    public ResponseEntity<?> handleEngineNoAlreadyPresentException(EngineNoAlreadyPresentException ex) {
        Map map = new HashMap();
        map.put("Status", HttpStatus.BAD_REQUEST);
        map.put("Success", false);
        map.put("message", Constraints.VEHICLE_DATA_NOT_ADDED);
        map.put("cause", Constraints.ENG_NO_ALREADY_PRESENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }


}
