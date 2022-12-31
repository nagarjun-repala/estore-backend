package com.nagarjun.estorebackend.exception;

public class ReviewNotFoundException extends RuntimeException{
    
    public ReviewNotFoundException(Long id) {
        super("Review id: '"  + id + "' does not exist in our records");
    }

    public ReviewNotFoundException(Long id, String product) {
        super("Reviews for " + product + "Id: '"  + id + "' does not exist in our records");
    }
}
