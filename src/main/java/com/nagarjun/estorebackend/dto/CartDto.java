package com.nagarjun.estorebackend.dto;

import java.util.List;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.CartItem;
import lombok.*;

@Getter
@Setter
public class CartDto {

    private Long id;
    private List<CartItem> items;
    private Integer total;

    public CartDto(Cart cart){
        this.id = cart.getId();
        this.items = cart.getCartItems();
        this.total = cart.getTotal();        
    }
    
}
