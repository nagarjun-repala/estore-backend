package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Order;

public interface OrderService {

    Order getOrder(Long orderId);
    Order getOrder(Long userId, Long productId);
    Order createOrder(Order order, Long userId, Long productId);
    Order updateOrder(Long orderId, Order order);
    void deleteOrder(Long orderId);
    List<Order> getOrders();

    
}
