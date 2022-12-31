package com.nagarjun.estorebackend.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nagarjun.estorebackend.GlobalMethods;
import com.nagarjun.estorebackend.exception.UserNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHandlerFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    filterChain.doFilter(request, response);
                } catch (UserNotFoundException error){
                    GlobalMethods.responseHandler(response, error.getMessage(), HttpServletResponse.SC_NOT_FOUND);
                } catch (JWTVerificationException error){
                    GlobalMethods.responseHandler(response, error.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
                } catch (RuntimeException error) {
                    GlobalMethods.responseHandler(response, error.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
                }        
    }
}
