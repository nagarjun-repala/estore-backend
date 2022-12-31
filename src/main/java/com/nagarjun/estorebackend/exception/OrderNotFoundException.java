package com.nagarjun.estorebackend.exception;

public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(Long id) {
        super("OrderId : '"  + id + "' does not exist in our records");
    }
    
    public OrderNotFoundException(Long userId, Long productId) {
        super("UserId: '"  + userId + "' and ProductId: '" + productId + "' does not exist in our records");
    }

    public OrderNotFoundException(Long id, String userOrProductOrAddress) {
        super("Orders for " + userOrProductOrAddress + "Id: '"  + id + "' does not exist in our records");

    }
}

