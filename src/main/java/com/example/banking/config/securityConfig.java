package com.example.banking.config;

import com.example.banking.security.JWTAuthenticationFilter;
import com.example.banking.security.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class securityConfig {

    private final JWTAuthenticationFilter jwtFilter;

    public securityConfig(JWTAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(
                csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/login.html",
                        "/registeruser.html",
                        "/userapi/login",
                        "/userapi/register",
                        "/dashboard.html"
                ).permitAll().requestMatchers(
                        "/accountapi/**"
                ).authenticated().anyRequest().permitAll());
        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}
