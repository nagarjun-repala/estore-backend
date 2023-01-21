package com.nagarjun.estorebackend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
   

    @PostMapping("/cart/{cartId}/address/{addressId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long cartId, @PathVariable Long addressId) {

        return new ResponseEntity<>(orderService.createOrder(cartId, addressId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long userId) {

        return new ResponseEntity<>(orderService.getOrders(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);     
    }    
    
    @PutMapping("/{orderId}/address/{addressId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @PathVariable Long addressId) {

        return new ResponseEntity<>(orderService.updateOrder(orderId, addressId), HttpStatus.OK);
    }    

}
