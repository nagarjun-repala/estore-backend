package com.nagarjun.estorebackend.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.nagarjun.estorebackend.security.filter.AuthenticationFilter;
import com.nagarjun.estorebackend.security.filter.ExceptionHandlerFilter;
import com.nagarjun.estorebackend.security.filter.JWTAuthorizationFilter;
import com.nagarjun.estorebackend.security.manager.CustomAuthenticationManager;
@AllArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);

        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_PATH);
        http
            .cors()
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers().denyAll()
            .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
            .antMatchers(HttpMethod.GET, "/status").permitAll()             
            .antMatchers(SecurityConstants.ADMIN_PATH).hasRole(SecurityConstants.ROLE_ADMIN)
            .antMatchers(SecurityConstants.USER_PATH).hasAnyRole(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_USER)
            .antMatchers(HttpMethod.GET).hasAnyRole(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_USER)
            .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
            .antMatchers(HttpMethod.DELETE, SecurityConstants.USER_CART_PATH).hasAnyRole(SecurityConstants.ROLE_ADMIN, SecurityConstants.ROLE_USER)
            .antMatchers(HttpMethod.DELETE).hasRole(SecurityConstants.ROLE_ADMIN)
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}

