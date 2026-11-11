package com.example.hello_spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Tạo Bean PasswordEncoder sử dụng BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Tạo Bean SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF vì làm REST API
                .csrf(csrf -> csrf.disable())
                // Phân quyền request
                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập tự do vào /api/v1/auth/**
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // Yêu cầu xác thực với mọi request còn lại
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}