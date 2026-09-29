package com.example.banking.config;

import com.example.banking.security.JwtAuthenticationFilter;
import com.example.banking.security.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class securityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public securityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers(
                "/login.html",
                "/loginstyle.css",
                "/loginjs.js",
                "/registeruser.html",
                "/regiseruserstyle.css",
                "/registeruserjs.js",
                "/dashboard.html",
                "/dashboardstyle.css",
                "/dashboardjs.js",
                "/userapi/**",
        ).permitAll().requestMatchers(
                "/createaccount.html",
                "/accountapi/**"
        ).authenticated().anyRequest().denyAll()).addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
