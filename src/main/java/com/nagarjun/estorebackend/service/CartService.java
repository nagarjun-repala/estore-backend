package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;

public interface CartService {

    CartItem addProduct(Long cartId, Long productId, CartItem cartItem);
    void deleteProduct(Long cartId, Long productId);
    List<CartItem> getProducts(Long userId);
    Integer cartTotal(Long cartId);
    Cart getCartById(Long cartId);
    Cart getCartByUserId(Long userId);
    
}
