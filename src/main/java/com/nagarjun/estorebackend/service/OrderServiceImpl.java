package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.AddressRepository;
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

    @Override
    public Order getOrderById(Long orderId) {
        
        Optional<Order> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) throw new ResourceNotFoundException(orderId, Constants.ORDER);
        return orderEntity.get();
    }

    @Override
    public Order createOrder(Order order, Long userId, Long productId, Long addressId) {

        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        Address address = addressRepository.findById(addressId).get();
        Integer quantity = order.getQuantity();
        LocalDateTime currentDateTime = LocalDateTime.now();
        order.setCreatedOn(currentDateTime);
        order.setUpdatedOn(currentDateTime);
        order.setProduct(product);
        order.setUser(user);
        order.setQuantity(quantity);
        order.setAddress(address);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()) throw new ResourceNotFoundException(orderId, Constants.ORDER);
        Order updateOrder = orderEntity.get();
        updateOrder.setUpdatedOn(LocalDateTime.now());
        updateOrder.setQuantity(order.getQuantity());
        return updateOrder;
        
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders() {
        
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderByUserIdAndProductId(Long userId, Long productId) {
        
        Optional<Order> orderEntity = orderRepository.findByUserIdAndProductId(userId, productId);
        if(orderEntity.isEmpty()) throw new ResourceNotFoundException(userId, Constants.USER, productId, Constants.PRODUCT);
        return orderEntity.get();
    }    

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        
       List<Order> orders = orderRepository.findAllByUserId(userId).get();
        if(orders.isEmpty()) throw new ResourceNotFoundException(userId, Constants.USER);        
        return orders;
    }       

    @Override
    public List<Order> getOrdersByProductId(Long productId) {

        List<Order> orders = orderRepository.findAllByProductId(productId).get();
        if(orders.isEmpty()) throw new ResourceNotFoundException(productId, Constants.PRODUCT);        
        return orders;
    }

    @Override
    public List<Order> getOrdersByAddressId(Long addressId) {

        List<Order> orders = orderRepository.findAllByAddressId(addressId).get();
        if(orders.isEmpty()) throw new ResourceNotFoundException(addressId, Constants.ADDRESS);        
        return orders;
    }

}
