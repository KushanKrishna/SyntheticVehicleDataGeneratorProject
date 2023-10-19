package com.svdg.svdg.exception;

public class VinAlreayExistsException extends  RuntimeException{

    private String message;
    public VinAlreayExistsException(){}
    public VinAlreayExistsException(String message){
        super(message);
        this.message=message;
    }
}
