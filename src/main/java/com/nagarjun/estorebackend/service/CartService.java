package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.dto.CartDto;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;

public interface CartService {

    CartItem addProduct(String username, Long productId, CartItem cartItem);
    void deleteCartItem(String username, Long productId);
    List<CartItem> getCartItems(Long userId);
    List<CartItem> getCartItems(String username);
    Cart getCart(Long cartId);
    CartDto getCartDto(String username);
    Cart getUserCart(String username);
    Cart getUserCart(Long userId);
    void deleteAllCartItems(Long cartId);
    
}
