package com.nagarjun.estorebackend.dto;



import com.nagarjun.estorebackend.entity.OrderProductQuantity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderProductQuantityDto implements Serializable {

    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Integer productTotal;

    public OrderProductQuantityDto(OrderProductQuantity orderProductQuantity) {
        this.setProductId(orderProductQuantity.getProduct().getId());
        this.setName(orderProductQuantity.getProduct().getName());
        this.setDescription(orderProductQuantity.getProduct().getDescription());
        this.setPrice(orderProductQuantity.getProduct().getPrice());
        this.setQuantity(orderProductQuantity.getQuantity());
        this.setProductTotal(orderProductQuantity.getTotal());
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer productTotal() {
        return this.quantity;
    }

    public void setProductTotal(Integer productTotal) {
        this.productTotal = productTotal;
    }

}
