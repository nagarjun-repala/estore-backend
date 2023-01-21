package com.nagarjun.estorebackend.service;

import java.util.List;
import com.nagarjun.estorebackend.entity.Order;

public interface OrderService {


    Order createOrder(Long cartId, Long addressId);
    List<Order> getOrders(Long userId);
    void deleteOrder(Long orderId);
    Order updateOrder(Long orderId, Long addressId);

    
}
