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
@RequestMapping("/user/product/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long orderId) {

        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
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
