package com.nagarjun.estorebackend.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDto {

    private Long orderId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Integer orderTotal;
    private List<OrderProductQuantityDto> products;
    private String status;

    public OrderDetailsDto(Order order, List<OrderProductQuantity> productQuantities) {
        this.orderId = order.getId();
        this.createdOn = order.getCreatedOn();
        this.updatedOn = order.getUpdatedOn();
        this.orderTotal = order.getTotal();
        // this.products = getOrderProductQuantity(orderService.getOrderProductQuantities(order.getId()));
        this.products = getOrderProductQuantity(productQuantities);
        this.status = order.getStatus();

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
