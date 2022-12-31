package com.nagarjun.estorebackend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("User id: '"  + id + "' does not exist in our records");
    }

    public UserNotFoundException(String username) {
        super("Username: '"  + username + "' does not exist in our records");
    }
}
