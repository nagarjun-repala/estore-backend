package com.nagarjun.estorebackend.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.security.SecurityConstants;
import com.nagarjun.estorebackend.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private CustomAuthenticationManager customAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

                try {
                    User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                    return customAuthenticationManager.authenticate(authentication);
                } catch (IOException e){
                    throw new RuntimeException();
                }
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(failed.getMessage());
                response.getWriter().flush();

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

                String token = JWT.create()
                .withSubject(authResult.getName())
                .withArrayClaim("AUTHORITIES", getRolesForUser(authResult))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign((Algorithm.HMAC512(SecurityConstants.SECRET_KEY)));
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);                

    }
    //Authorization : Bearer JWTTOKEN

    private String[] getRolesForUser(Authentication authResult) {

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        String[] roles = new String[authorities.size()];
        int i = 0;
        for (GrantedAuthority grantedAuthority : authResult.getAuthorities()) {
            roles[i] = grantedAuthority.toString();
            i++;
        }
        return roles;
    }
}
