package com.nagarjun.estorebackend.service;

import java.util.List;
import com.nagarjun.estorebackend.entity.CartItem;

public interface CartService {

    CartItem addProduct(Long cartId, Long productId, CartItem cartItem);
    void deleteProduct(Long cartId, Long productId);
    List<CartItem> getProducts(Long userId);
    
}
