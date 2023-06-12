package com.nagarjun.estorebackend.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.Cart;
import com.nagarjun.estorebackend.entity.Order;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable{

    private Long id;
    private String username;
    private String email;
    private List<Order> orders;
    private List<Address> addresses;
    private Cart cart;
    private List<String> roles;
    private String firstName;
    private String lastName;

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.addresses = user.getAddresses();
        // this.cart = user.getCart();
        this.orders = user.getOrders();
        this.roles = getRoles(user.getRoles());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
    private List<String> getRoles(Set<Role> roles){
        List<String> userRoles = new ArrayList<>();
        for (Role role : roles) {
            userRoles.add(role.getName());
        }
        return userRoles;
    }
}
