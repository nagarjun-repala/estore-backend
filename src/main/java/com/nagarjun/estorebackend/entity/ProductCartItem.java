package com.nagarjun.estorebackend.entity;

public class ProductCartItem extends Product{

    private Integer quantity;

    
    public ProductCartItem(Product product) {

        super(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getOrders(), product.getReview(), product.getCartDetails());

    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductCartItem(Integer quantity) {
        this.quantity = quantity;
    }










   


}
