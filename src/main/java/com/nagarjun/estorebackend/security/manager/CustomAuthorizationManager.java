package com.nagarjun.estorebackend.security.manager;

import java.util.function.Supplier;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomAuthorizationManager implements AuthorizationManager{


    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        // TODO Auto-generated method stub
        System.out.println(authentication + ": Auth");
        System.out.println(object + " Object");
        return null;
    }
    
    @Override
    public void verify(Supplier authentication, Object object) {
        // TODO Auto-generated method stub
        AuthorizationManager.super.verify(authentication, object);
    }
    
}
