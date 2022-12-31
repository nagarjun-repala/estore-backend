package com.nagarjun.estorebackend.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.nagarjun.estorebackend.security.filter.AuthenticationFilter;
import com.nagarjun.estorebackend.security.filter.ExceptionHandlerFilter;
import com.nagarjun.estorebackend.security.filter.JWTAuthorizationFilter;
import com.nagarjun.estorebackend.security.manager.CustomAuthenticationManager;
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_PATH);
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
            .antMatchers(HttpMethod.DELETE).hasAuthority(SecurityConstants.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET).hasAnyAuthority(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_USER)
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)            
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}

