package com.example.UserAPI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // Postman用にOFF
            .httpBasic(httpBasic -> httpBasic.disable())
            .authorizeHttpRequests(auth -> auth
                 // HTML許可
                .requestMatchers(
                    "/",
                    "/login-page",
                    "/*.html",
                    "/css/**",
                    "/js/**"
                ).permitAll()
                .requestMatchers("/auth/login","/Users").permitAll() // ログインは全てのユーザーに許可
                .requestMatchers("/cart/**").authenticated()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}