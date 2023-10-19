package com.svdg.svdg.exception;

public class EngineNoAlreadyPresentException extends RuntimeException{

    String message;
    public EngineNoAlreadyPresentException() {
    }
    public EngineNoAlreadyPresentException(String message) {
        super(message);
        this.message = message;
    }
}
