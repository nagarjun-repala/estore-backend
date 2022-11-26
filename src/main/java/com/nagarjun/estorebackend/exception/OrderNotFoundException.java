package com.nagarjun.estorebackend.exception;

public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(Long id) {
        super("OrderId : '"  + id + "' does not exist in our records");
    }
    
    public OrderNotFoundException(Long userId, Long productId) {
        super("UserId: '"  + userId + "' and ProductId: '" + productId + "' does not exist in our records");
    }

    public OrderNotFoundException(Long userId, String user) {
        super("Orders for " + user + "Id: '"  + userId + "' does not exist in our records");

    }
}

