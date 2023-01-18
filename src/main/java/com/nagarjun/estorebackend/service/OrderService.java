package com.nagarjun.estorebackend.service;

import com.nagarjun.estorebackend.entity.Order;

public interface OrderService {


    Order createOrder(Long cartId, Long addressId);

    
}
