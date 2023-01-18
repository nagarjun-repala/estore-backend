package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
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
            Integer totalQuantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            existingCartItem.setQuantity(totalQuantity);
            CartItem savedExistingCartItem = cartItemRepository.save(existingCartItem);
            cart.setTotal(cartTotal(cartId));
            cartRepository.save(cart);
            return savedExistingCartItem;
        }
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.setTotal(cartTotal(cartId));
        cartRepository.save(cart);
        return savedCartItem;
    }

    @Override
    public void deleteProduct(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
        Cart cart = cartRepository.findById(cartId).get();
        cart.setTotal(cartTotal(cartId));
        cartRepository.save(cart);
    }


    @Override
    public List<CartItem> getProducts(Long userId) {

        Long cartId = cartRepository.findByUserId(userId).get().getId();
        return cartItemRepository.findByCartId(cartId).get();
    }

    @Override
    public Integer cartTotal(Long cartId) {
        Integer cartTotal = 0;
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId).get();
        for (CartItem item : cartItems) {
            cartTotal += item.getTotal();
        }
        return cartTotal;
    }

    @Override
    public Cart getCartById(Long cartId) {
        Optional<Cart> cartEntity = cartRepository.findById(cartId);
        if(cartEntity.isEmpty()) throw new ResourceNotFoundException(cartId, Constants.CART);
        return cartEntity.get();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        Optional<Cart> cartEntity = cartRepository.findByUserId(userId);
        if(cartEntity.isEmpty()) throw new ResourceNotFoundException(userId, Constants.USER);
        return cartEntity.get();
    } 
}
