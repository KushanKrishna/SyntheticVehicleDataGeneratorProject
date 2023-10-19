package com.svdg.svdg.exception;

public class VinNotValidException extends RuntimeException{
    private String message;
    public VinNotValidException() {
    }
    public VinNotValidException(String message){
        super(message);
        this.message=message;
    }
}
