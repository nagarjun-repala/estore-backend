package com.nagarjun.estorebackend.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(Long id) {
        super("Address id: '"  + id + "' does not exist in our records");
    }    
}
