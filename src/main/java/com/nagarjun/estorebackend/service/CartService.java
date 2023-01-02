package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.CartDetails;
import com.nagarjun.estorebackend.entity.Product;

public interface CartService {

    CartDetails addProduct(Long cartId, Long productId, CartDetails cartDetails);
    void deleteProduct(Long cartId, Long productId, Integer quantity);
    List<Product> getProducts(Long cartId);
    
}
