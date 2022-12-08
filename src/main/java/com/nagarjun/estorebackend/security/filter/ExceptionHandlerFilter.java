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
                } catch (UserNotFoundException e){
                    GlobalMethods.errorResponseFilter(response, "User not found", HttpServletResponse.SC_NOT_FOUND);
                } catch (JWTVerificationException e){
                    GlobalMethods.errorResponseFilter(response, "Not a valid JWT", HttpServletResponse.SC_FORBIDDEN);
                } catch (RuntimeException e) {
                    GlobalMethods.errorResponseFilter(response, "BAD REQUEST", HttpServletResponse.SC_BAD_REQUEST);
                }        
    }
}
