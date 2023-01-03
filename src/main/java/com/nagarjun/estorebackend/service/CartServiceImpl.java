package com.nagarjun.estorebackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartDetails;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.ProductCartItem;
import com.nagarjun.estorebackend.exception.ProductNotFoundException;
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
    public void deleteProduct(Long cartId, Long productId) {
        cartDetailsRepository.deleteByCartIdAndProductId(cartId, productId);
        
    }

    @Override
    public List<ProductCartItem> getProducts(Long cartId) {

        List<ProductCartItem> products = new ArrayList<ProductCartItem>(); 
        List<CartDetails> cartItems = cartDetailsRepository.findAllByCartId(cartId).get();
        if(cartItems.isEmpty()) throw new ProductNotFoundException(cartId, "Cart");
        for (CartDetails rawItem : cartItems) {
            Product cartRawProduct = new ProductCartItem(rawItem.getProduct());
            ProductCartItem item = (ProductCartItem) cartRawProduct;
            item.setQuantity(rawItem.getQuantity());
            
            products.add(item);
        }
        return products;
    }

    @Override
    public CartDetails getProductsByCartIdAndProductId(Long cartId, Long productId) {
        
        return (CartDetails) cartDetailsRepository.findByCartIdAndProductId(cartId, productId);
    }
    
}
