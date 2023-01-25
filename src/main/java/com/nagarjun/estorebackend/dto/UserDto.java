package com.nagarjun.estorebackend.dto;

import java.util.List;
import java.util.Set;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import lombok.*;

@Getter
@Setter
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private List<Order> orders;
    private List<Address> addresses;
    private Cart cart;
    private Set<Role> roles;

    public UserDto(User user){
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.addresses = user.getAddresses();
        this.cart = user.getCart();
        this.orders = user.getOrders();
        this.roles = user.getRoles();
    }
}
