package com.svdg.svdg.exception;

public class RegNoAlreadyExistsException extends RuntimeException{

    private String message;
    public  RegNoAlreadyExistsException(){}
    public RegNoAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }

}
