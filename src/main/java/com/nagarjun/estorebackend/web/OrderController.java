package com.nagarjun.estorebackend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.dto.OrderDetailsDto;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.security.manager.CustomPrincipal;
import com.nagarjun.estorebackend.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
   

    @PostMapping("/address/{addressId}")
    public ResponseEntity<OrderDetailsDto> createOrder(@AuthenticationPrincipal CustomPrincipal principal, @PathVariable Long addressId) {

        return new ResponseEntity<>(orderService.createOrder(principal.getUsername(), addressId), HttpStatus.CREATED);
    }

    @GetMapping("/listOrders")
    public ResponseEntity<List<Order>> getOrders(@AuthenticationPrincipal CustomPrincipal principal) {

        return new ResponseEntity<>(orderService.getOrders(principal.getUserId()), HttpStatus.OK);
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
    

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId, @AuthenticationPrincipal CustomPrincipal principal) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }
    
    @DeleteMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);     
    }    
        

}
