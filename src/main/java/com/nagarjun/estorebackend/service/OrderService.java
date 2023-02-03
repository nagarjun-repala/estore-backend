package com.nagarjun.estorebackend.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;

import com.nagarjun.estorebackend.dto.OrderDetailsDto;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;

public interface OrderService {


    OrderDetailsDto createOrder(String username, Long addressId);
    List<Order> getOrders(Long userId);
    List<Order> getOrders(String username);
    void deleteOrder(Long orderId);
    Order updateOrder(Long orderId, Long addressId);
    @PostAuthorize("(hasRole('ADMIN')) OR (returnObject.user.username == principal.username)")
    Order getOrder(Long orderId);
    List<OrderProductQuantity> getOrderProductQuantities(Long orderId);
    void cancelOrder(Long orderId);

    
}
