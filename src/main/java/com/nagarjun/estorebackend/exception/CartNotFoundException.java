package com.nagarjun.estorebackend.exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Long id, String resource) {
        super(resource + " id: '"  + id + "' does not exist in our records");
    }  
}
