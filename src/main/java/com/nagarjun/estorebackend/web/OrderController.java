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
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long orderId) {

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<Order> findOrderByUserId(@PathVariable Long userId, @PathVariable Long productId) {

        return new ResponseEntity<>(orderService.getOrder(userId, productId), HttpStatus.OK);
    }    

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Order order) {

        return new ResponseEntity<>(orderService.createOrder(order, userId, productId), HttpStatus.CREATED);
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);     
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrders() {

        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

}
