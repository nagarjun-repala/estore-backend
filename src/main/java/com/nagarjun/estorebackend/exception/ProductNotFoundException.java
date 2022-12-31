package com.nagarjun.estorebackend.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id) {
        super("Product id: '"  + id + "' does not exist in our records");
    }
}
