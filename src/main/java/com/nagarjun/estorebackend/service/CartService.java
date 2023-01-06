package com.nagarjun.estorebackend.service;

import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.exception.ResourceExistException;

public interface CartService {

    CartItem addProduct(Long cartId, Long productId, CartItem cartItem) throws ResourceExistException;
    void deleteProduct(Long cartId, Long productId);
    Cart getProductsByCartIdAndProductId(Long cartId, Long productId);
    
}
