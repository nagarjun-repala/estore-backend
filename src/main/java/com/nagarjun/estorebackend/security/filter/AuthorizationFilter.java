package com.nagarjun.estorebackend.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.nagarjun.estorebackend.security.SecurityConstants;

public class AuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                try {
                    filterChain.doFilter(request, response);
                    System.out.println(request.getHeader(SecurityConstants.AUTHORIZATION));
                    System.out.println(request);
                    System.out.println(response);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
        
    }
    
}
