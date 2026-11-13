package com.flowin.wms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
//        http.csrf(x -> x.ignoringRequestMatchers("/api/**"));
//        http.authorizeHttpRequests(auth ->
//                auth.requestMatchers(HttpMethod.GET, "/api/health").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/skus").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/skus/1").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/skus/10").permitAll()
//                auth.requestMatchers(HttpMethod.POST, "/api/**").permitAll());
//                .requestMatchers(HttpMethod.GET, "/api/skus/2").permitAll().anyRequest().authenticated());
        //  http.formLogin(Customizer.withDefaults()); // needs to turning off after implementing JWT system

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll());

        return http.build();
    }
}
