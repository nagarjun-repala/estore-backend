package com.nagarjun.estorebackend.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import com.nagarjun.estorebackend.service.OrderService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDto {

    @Autowired
    private OrderService orderService;
    

    private Long orderId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Integer orderTotal;
    private List<OrderProductQuantityDto> products;
    private String status;

    public OrderDetailsDto(Order order) {
        this.orderId = order.getId();
        this.createdOn = order.getCreatedOn();
        this.updatedOn = order.getUpdatedOn();
        this.orderTotal = order.getTotal();
        this.products = getOrderProductQuantity(orderService.getOrderProductQuantities(order.getId()));
    }

    private List<OrderProductQuantityDto> getOrderProductQuantity(List<OrderProductQuantity> orderProductQuantities){

        List<OrderProductQuantityDto> allProducts = new ArrayList<>();
        for (OrderProductQuantity product : orderProductQuantities) {
            OrderProductQuantityDto productOrderQuantity = new OrderProductQuantityDto(product);
            allProducts.add(productOrderQuantity);
        }
        return allProducts;

    }
}
