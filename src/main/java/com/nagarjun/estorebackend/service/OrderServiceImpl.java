package com.nagarjun.estorebackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.Repository.OrderRepository;
import com.nagarjun.estorebackend.entity.Order;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrder(Long orderId) {
        
        Optional<Order> orderEntity = orderRepository.findById(orderId);
        return unwrappOrder(orderEntity, orderId);
    }

    @Override
    public Order createOrder(Order order) {

        order.setCreatedOn(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);

        Order unwrappedOrder = unwrappOrder(orderEntity, orderId);

        // unwrappedOrder.set

        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

        Optional<Order> orderEntity = orderRepository.findById(orderId);

        if(orderEntity.isPresent()) orderRepository.deleteById(orderId);
        else throw new EntityNotFoundException();
 
    }

    @Override
    public List<Order> getOrders() {
        
        return (List<Order>) orderRepository.findAll();
    }

    static Order unwrappOrder(Optional<Order> orderEntity, Long orderId) {
        if (orderEntity.isPresent()) return orderEntity.get();
        else throw new EntityNotFoundException();
    }
    
}
