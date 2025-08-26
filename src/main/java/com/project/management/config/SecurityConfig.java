package com.project.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for API endpoints
                .authorizeHttpRequests(authz -> authz
                        // Allow Swagger UI and API docs without authentication
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/api-docs/**",
                                "/api/**"
                        ).permitAll()
                        // Allow authentication endpoints without authentication
                        .requestMatchers("/api/auth/**").permitAll()
                        // Secure all other endpoints
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable) // Disable form login
                .httpBasic(AbstractHttpConfigurer::disable); // Disable basic auth

        return http.build();
    }
}