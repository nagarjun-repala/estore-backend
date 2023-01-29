package com.nagarjun.estorebackend.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nagarjun.estorebackend.security.SecurityConstants;
import com.nagarjun.estorebackend.security.manager.CustomPrincipal;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String header = request.getHeader("Authorization");
                if(header == null || !header.startsWith(SecurityConstants.BEARER)){
                    filterChain.doFilter(request, response);
                    return;
                }

            DecodedJWT jwt = verifyJwt(header);
            Authentication authentication = getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
    }


    private DecodedJWT verifyJwt(String header ) {

        String token = header.replace(SecurityConstants.BEARER, "");
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
            .build()
            .verify(token);
        return jwt;        
    }    

    private Authentication getAuthentication(DecodedJWT jwt){
        String user = jwt.getSubject();
        Long userId = jwt.getClaim("ID").asLong();
        List<String> roles = jwt.getClaim("ROLES").asList(String.class);
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<String> authorities = jwt.getClaim("AUTHORITIES").asList(String.class);
        CustomPrincipal customPrincipal = new CustomPrincipal(userId, user, roles);
        for (String role : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));   
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(customPrincipal, null, grantedAuthorities);
        return authentication;     

    }    
    
}
