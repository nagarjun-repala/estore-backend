package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.CartDetails;
import com.nagarjun.estorebackend.entity.ProductCartItem;

public interface CartService {

    CartDetails addProduct(Long cartId, Long productId, CartDetails cartDetails);
    void deleteProduct(Long cartId, Long productId);
    List<ProductCartItem> getProducts(Long cartId);
    CartDetails getProductsByCartIdAndProductId(Long cartId, Long productId);
    
}
