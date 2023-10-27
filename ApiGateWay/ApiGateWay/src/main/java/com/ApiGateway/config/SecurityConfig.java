package com.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/auth/login") // Secure the "/auth/login" endpoint
                .authenticated()
                .anyExchange()
                .permitAll() // Allow all other endpoints without authentication
                .and()
                .oauth2Login() // Enable OAuth2 login
                .and()
                .oauth2ResourceServer()
                .jwt(); // Use JWT tokens for resource server

        return http.build();
    }
}
