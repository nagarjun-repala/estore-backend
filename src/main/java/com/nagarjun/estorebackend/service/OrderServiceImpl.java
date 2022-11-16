package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarjun.estorebackend.Repository.OrderRepository;
import com.nagarjun.estorebackend.entity.Order;



public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getOrder(Long orderId) {
        
        Optional<Order> orderEntity = orderRepository.findById(orderId);
        return unwrappOrder(orderEntity, orderId);
    }

    @Override
    public Order createOrder(Order order) {
        
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);

        Order unwrappedOrder = unwrappOrder(orderEntity, orderId);

        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        

        
    }

    @Override
    public List<Order> getOrders() {
        
        return null;
    }

    static Order unwrappOrder(Optional<Order> orderEntity, Long orderId) {
        if (orderEntity.isPresent()) return orderEntity.get();
        else throw new EntityNotFoundException();
    }
    
}
