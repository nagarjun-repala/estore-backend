package com.nagarjun.estorebackend.security.manager;

import org.springframework.security.core.GrantedAuthority;

public class GrantAuthority implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return role;
    }

    
}
