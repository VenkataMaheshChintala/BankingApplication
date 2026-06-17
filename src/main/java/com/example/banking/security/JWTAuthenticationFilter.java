package com.example.banking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    public JWTAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT FILTER HIT");
        System.out.println("URI : " + request.getRequestURI());
        String authHeader = request.getHeader("Authorization");
        System.out.println("HEADER = [" + authHeader + "]");
        if(authHeader == null || !authHeader.startsWith("Bearer")) {
            System.out.println("Function returned!");
            filterChain.doFilter(request,response);
            return;
        }
        String token = "";
        try {
            token = authHeader.substring(6);
            System.out.println("STEP 1 : ");
            System.out.println("TOKEN = " + token);
        } catch (Exception e) {
            System.out.println("SUBSTRING FAILED");
            e.printStackTrace();
        }
        try {
            System.out.println("STEP 2 : ");
            System.out.println("USERNAME : " + jwtService.extractUsername(token));
            System.out.println("STEP 3 : ");
            System.out.println("VALID : " + jwtService.validateToken(token));
        } catch (Exception e) {
            System.out.println("STEP FAILED");
            e.printStackTrace();
        }
        if(jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null, Collections.emptyList());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
