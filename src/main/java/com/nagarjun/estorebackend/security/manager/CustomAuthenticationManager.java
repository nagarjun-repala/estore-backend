package com.nagarjun.estorebackend.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.service.UserService;
@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager{

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser(authentication.getName());
        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("You provided an incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString());
    }
}
