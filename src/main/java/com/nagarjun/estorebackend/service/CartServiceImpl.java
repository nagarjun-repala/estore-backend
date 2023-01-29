package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.dto.CartDto;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.exception.ResourceEmptyException;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.CartItemRepository;
import com.nagarjun.estorebackend.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public CartItem addProduct(String username, Long productId, CartItem cartItem) {

        Cart cart = getUserCart(username);
        Product product = productService.getProduct(productId);
        Optional<CartItem> cartItemEntity =  cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if(cartItemEntity.isPresent()) {
            CartItem existingCartItem =  cartItemEntity.get();
            Integer totalQuantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            existingCartItem.setQuantity(totalQuantity);
            CartItem savedExistingCartItem = cartItemRepository.save(existingCartItem);
            cart.setTotal(cartTotal(cart.getId()));
            cartRepository.save(cart);
            return savedExistingCartItem;
        }
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.setTotal(cartTotal(cart.getId()));
        cartRepository.save(cart);
        return savedCartItem;
    }

    @Override
    public void deleteCartItem(String username, Long productId) {

        Cart cart = getUserCart(username);
        Product product = productService.getProduct(productId);
        cartItemRepository.deleteByCartIdAndProductId(cart.getId(), product.getId());
        cart.setTotal(cartTotal(cart.getId()));
        cartRepository.save(cart);
    }


    @Override
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = getCart(userId);
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId()).get();
        if (cartItems.isEmpty()) throw new ResourceEmptyException(Constants.CART); 
        return cartItems;
    }

    public Integer cartTotal(Long cartId) {
        Integer cartTotal = 0;
        Cart cart = getCart(cartId);
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId()).get();
        if(cartItems.isEmpty()) throw new ResourceEmptyException(Constants.CART_ITEMS);
        for (CartItem item : cartItems) {
            cartTotal += item.getTotal();
        }
        return cartTotal;
    }

    @Override
    public Cart getCart(Long cartId) {
        Optional<Cart> cartEntity = cartRepository.findById(cartId);
        if(cartEntity.isEmpty()) throw new ResourceNotFoundException(cartId, Constants.CART);
        return cartEntity.get();
    }

    @Override
    public Cart getUserCart(Long userId) {
        Optional<Cart> cartEntity = cartRepository.findByUserId(userId);
        if(cartEntity.isEmpty()) throw new ResourceNotFoundException(userId, Constants.USER);
        return cartEntity.get();
    }

    @Override
    public List<CartItem> getCartItems(String username) {
        Cart cart = getUserCart(username);
        return cart.getCartItems();
    }

    @Override
    public CartDto getCartDto(String username) {
        Cart cart = getUserCart(username);
        CartDto cartDto = new CartDto(cart);
        return cartDto;
    }

    @Override
    public Cart getUserCart(String username) {
        Optional<Cart> cartEntity = cartRepository.findByUsername(username);
        if(cartEntity.isEmpty()) throw new ResourceNotFoundException(username, Constants.CART);
        return cartEntity.get();
    }

    @Override
    public void deleteAllCartItems(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
    } 
}
