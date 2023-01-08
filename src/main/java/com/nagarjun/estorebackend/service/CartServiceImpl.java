package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.repository.CartItemRepository;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addProduct(Long cartId, Long productId, CartItem cartItem) {

        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        Optional<CartItem> cartItemEntity =  cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if(cartItemEntity.isPresent()) {
            CartItem existingCartItem =  cartItemEntity.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteProduct(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
        
    }


    @Override
    public List<CartItem> getProducts(Long userId) {

        Long cartId = cartRepository.findByUserId(userId).get().getId();
        return cartItemRepository.findByCartId(cartId).get();
    }
    
}
