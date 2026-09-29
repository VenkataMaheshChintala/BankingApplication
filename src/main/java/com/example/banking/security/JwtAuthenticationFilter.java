package com.example.banking.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JWTService jwtService;

    public JwtAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("REQUEST : " + request.getRequestURL());
        String authHeader = request.getHeader("Authorization");
        System.out.println("autheHeader : " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Token found");
            if (jwtService.validateToken(token)) {
                System.out.println("Valid token");
                String username = jwtService.extractUsername(token);
                System.out.println("Username : " + username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.emptyList()
                        );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                System.out.println("AUTHENTICATED: " + SecurityContextHolder.getContext().getAuthentication()
                );
            } else {
                System.out.println("Invalid Token");
            }
        }
        filterChain.doFilter(request, response);
    }
}
