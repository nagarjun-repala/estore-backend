package com.nagarjun.estorebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartDetails;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.repository.CartDetailsRepository;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartDetails addProduct(Long cartId, Long productId, CartDetails cartDetails) {

        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        cartDetails.setCart(cart);
        cartDetails.setProduct(product);
        return cartDetailsRepository.save(cartDetails);
    }

    @Override
    public void deleteProduct(Long cartId, Long productId, Integer quantity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Product> getProducts(Long cartId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
