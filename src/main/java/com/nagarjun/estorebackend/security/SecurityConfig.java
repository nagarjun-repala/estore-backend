package com.nagarjun.estorebackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET).permitAll()
            .antMatchers(HttpMethod.POST).permitAll()
            .antMatchers(HttpMethod.PUT).permitAll()
            .antMatchers(HttpMethod.DELETE).permitAll()
            .antMatchers(HttpMethod.PATCH).permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();

        return http.build();
    }
    
}
