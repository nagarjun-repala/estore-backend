package com.nagarjun.estorebackend.security.manager;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.nagarjun.estorebackend.entity.Role;
import com.nagarjun.estorebackend.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPrincipal implements Principal{

    private Long id;
    private String username;
    private List<String> roles;

    public CustomPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = getRoles(user.getRoles());
    }

    public CustomPrincipal(Long userId, String username, List<String> roles) {
        this.id = userId;
        this.username = username;
        this.roles = roles;
    }
   
    @Override
    public String getName() {
        return this.username;
    }
    private List<String> getRoles(Set<Role> roles){
        List<String> userRoles = new ArrayList<>();
        for (Role role : roles) {
            userRoles.add(role.getName());
        }
        return userRoles;
    }
        
}
