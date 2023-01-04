package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.ProductCartItem;
import com.nagarjun.estorebackend.entity.ProductQuantity;

public interface CartService {

    Cart addProduct(Long cartId, Long productId, ProductQuantity productQuantity);
    void deleteProduct(Long cartId, Long productId);
    List<ProductCartItem> getProducts(Long cartId);
    Cart getProductsByCartIdAndProductId(Long cartId, Long productId);
    
}
