package com.nagarjun.estorebackend.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(Long id) {
        super("Address id: '"  + id + "' does not exist in our records");
    }
    public AddressNotFoundException(Long userId, String user) {
        super("Addresses for userId: '"  + userId + "' does not exist in our records");
    }          
}
