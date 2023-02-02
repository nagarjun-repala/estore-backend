package com.nagarjun.estorebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Order;



@Service("accessService")
public class AccessService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    public boolean hasAccessToOrder(Long orderId, Long userId) {

        Order order = orderService.getOrder(orderId);
        return order.getUser().getId() == userId;
    }
    
    public boolean hasAccessToAddress(Long adressId, Long userId) {

        Address address = addressService.getAddress(adressId);
        return address.getUser().getId() == userId;
    } 


    
}
