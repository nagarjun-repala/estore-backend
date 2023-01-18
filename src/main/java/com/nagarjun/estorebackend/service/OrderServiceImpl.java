package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.repository.AddressRepository;
import com.nagarjun.estorebackend.repository.CartItemRepository;
import com.nagarjun.estorebackend.repository.CartRepository;
import com.nagarjun.estorebackend.repository.OrderProductQuantityRepository;
import com.nagarjun.estorebackend.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderProductQuantityRepository orderProductQuantityRepository;

    @Override
    public Order createOrder(Long cartId, Long addressId) {
        Order order = new Order();
        Cart cart = cartRepository.findById(cartId).get();
        Address address = addressRepository.findById(addressId).get();
        User user = cart.getUser();
        List<CartItem> cartItems = cart.getCartItems();
        Set<Product> products = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            products.add(cartItem.getProduct());
        }
        order.setCreatedOn(LocalDateTime.now());
        order.setProducts(products);
        order.setUser(user);
        order.setTotal(cart.getTotal());
        order.setAddress(address);
        Order savedOrder = orderRepository.save(order);
        updateOrderProductQuantity(savedOrder, cart);
        return savedOrder;
    }

    private void updateOrderProductQuantity(Order order, Cart cart){

        List<OrderProductQuantity> orderProductQuantities = new ArrayList<OrderProductQuantity>();
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId()).get();
        for (CartItem cartItem : cartItems) {

            OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
            orderProductQuantity.setOrder(order);
            orderProductQuantity.setProduct(cartItem.getProduct());
            orderProductQuantity.setQuantity(cartItem.getQuantity());
            orderProductQuantity.setTotal(cartItem.getTotal());
            orderProductQuantity.setPrice(cartItem.getProduct().getPrice());
            orderProductQuantities.add(orderProductQuantity);
        }
        orderProductQuantityRepository.saveAll(orderProductQuantities);
        cartItemRepository.deleteAllByCartId(cart.getId());
    }

}
