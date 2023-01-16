package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.AddressRepository;
import com.nagarjun.estorebackend.repository.CartItemRepository;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.OrderRepository;
import com.nagarjun.estorebackend.repository.ProductRepository;
import com.nagarjun.estorebackend.repository.UserRepository;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Order createOrder(Long cartId, Long addressId) {
        Order order = new Order();
        Cart cart = cartRepository.findById(cartId).get();
        User user = cart.getUser();
        Address address = addressRepository.findById(addressId).get();
        List<CartItem> cartItems = cart.getCartItems();
        LocalDateTime currentDateTime = LocalDateTime.now();
        order.setAddress(address);
        order.setCartItems(cartItems);
        order.setTotal(cart.getTotal());
        order.setUser(user);
        order.setCreatedOn(currentDateTime);
        order.setUpdatedOn(currentDateTime);
        order.setCartItems(cartItems);
        Order savedOrder = orderRepository.save(order);
        for (CartItem cartItem : cartItems) {
            cartItem.setOrder(savedOrder);
        }
        cartItemRepository.saveAll(cartItems);
        return savedOrder;
    }

}
