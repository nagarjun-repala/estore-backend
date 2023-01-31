package com.nagarjun.estorebackend.exception;

public class ResourceExistException extends RuntimeException{

    public ResourceExistException(Long id, String resource) {
        super(resource + " id: '"  + id + "' already exist in the cart");
    }

    public ResourceExistException(String message) {
        super(message);
    }    
     
}
