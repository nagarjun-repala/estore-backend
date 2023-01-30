package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.dto.OrderDetailsDto;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ResourceEmptyException;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.OrderProductQuantityRepository;
import com.nagarjun.estorebackend.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductQuantityRepository orderProductQuantityRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Override
    public OrderDetailsDto createOrder(String username, Long addressId) {
        Cart cart = cartService.getUserCart(username);
        if(cart.getCartItems().isEmpty()) throw new ResourceEmptyException(Constants.CART);
        Address address = addressService.getAddress(addressId);
        User user = cart.getUser();
        List<CartItem> cartItems = cart.getCartItems();
        Set<Product> products = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            products.add(cartItem.getProduct());
        }
        Order order = new Order();
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        order.setProducts(products);
        order.setUser(user);
        order.setTotal(cart.getTotal());
        order.setAddress(address);
        order.setCreatedOn(currentLocalDateTime);
        order.setUpdatedOn(currentLocalDateTime);
        order.setStatus(Constants.SUCCESS);
        Order savedOrder = orderRepository.save(order);
        List<OrderProductQuantity> updatedOrderProductQuantity = updateOrderProductQuantity(savedOrder, cart);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto(savedOrder, updatedOrderProductQuantity);
        cart.setTotal(0);
        cart.setCartItems(new ArrayList<CartItem>());
        cartService.saveCart(cart);
        return orderDetailsDto;
    }

    private List<OrderProductQuantity> updateOrderProductQuantity(Order order, Cart cart){
        List<OrderProductQuantity> orderProductQuantities = new ArrayList<OrderProductQuantity>();
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            OrderProductQuantity orderProductQuantity = new OrderProductQuantity();
            orderProductQuantity.setOrder(order);
            orderProductQuantity.setProduct(cartItem.getProduct());
            orderProductQuantity.setQuantity(cartItem.getQuantity());
            orderProductQuantity.setTotal(cartItem.getTotal());
            orderProductQuantity.setPrice(cartItem.getProduct().getPrice());
            orderProductQuantities.add(orderProductQuantity);
        }
        List<OrderProductQuantity> savedOrderProductQuantity = (List<OrderProductQuantity>) orderProductQuantityRepository.saveAll(orderProductQuantities);
        cartService.deleteAllCartItems(cart.getId());
        return savedOrderProductQuantity;
    }

    @Override
    public List<Order> getOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        if(orders.isEmpty()) throw new ResourceNotFoundException(Constants.ORDER);
        return orders;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrder(Long orderId, Long addressId) {
        Order order = getOrder(orderId);
        Address address = addressService.getAddress(addressId);
        order.setAddress(address);
        order.setUpdatedOn(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders(String username) {
        List<Order> orders = orderRepository.findByUserUsername(username);
        if(orders.isEmpty()) throw new ResourceNotFoundException(Constants.ORDER);
        return orders;
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) throw new ResourceNotFoundException(orderId, Constants.ORDER);
        return order.get();
    }

    @Override
    public List<OrderProductQuantity> getOrderProductQuantities(Long orderId) {
        List<OrderProductQuantity> orderProductQuantities = orderProductQuantityRepository.findByOrderId(orderId);
        if(orderProductQuantities.isEmpty()) throw new ResourceNotFoundException(Constants.ORDER);
        return orderProductQuantities;
    }

}
