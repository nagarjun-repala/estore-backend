package com.nagarjun.estorebackend.dto;

import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import com.nagarjun.estorebackend.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductQuantityDto {

    private Product product;
    private Integer quantity;

    public OrderProductQuantityDto(OrderProductQuantity orderProductQuantity) {
        this.product = orderProductQuantity.getProduct();
        this.quantity = orderProductQuantity.getQuantity();
    }

}
