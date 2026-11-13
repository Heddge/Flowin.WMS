package com.flowin.wms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/health").permitAll());
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/skus").permitAll());
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/skus/1").permitAll());
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/skus/10").permitAll());
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/skus/2").permitAll().anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults()); // needs to turning off after implementing JWT system
        return http.build();
    }
}
