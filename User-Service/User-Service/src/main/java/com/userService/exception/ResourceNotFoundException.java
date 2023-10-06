package com.userService.exception;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource Not ound On server !!..");
    }

    public  ResourceNotFoundException(String message){
        super(message);
    }
}
