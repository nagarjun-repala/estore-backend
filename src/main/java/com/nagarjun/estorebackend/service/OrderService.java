package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Order;

public interface OrderService {

    Order getOrderById(Long orderId);
    Order getOrderByUserIdAndProductId(Long userId, Long productId);
    Order getOrderByUserId(Long userId);
    Order createOrder(Order order, Long userId, Long productId);
    Order updateOrder(Long orderId, Order order);
    void deleteOrder(Long orderId);
    List<Order> getOrders();
    
}
