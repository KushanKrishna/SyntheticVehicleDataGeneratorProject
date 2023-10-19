package com.svdg.svdg.exception;

public class HttpMessageNotReadableException extends RuntimeException{
    public String message;
    public HttpMessageNotReadableException() {
    }
    public  HttpMessageNotReadableException(String message){
        super(message);
        this.message=message;

    }
}
