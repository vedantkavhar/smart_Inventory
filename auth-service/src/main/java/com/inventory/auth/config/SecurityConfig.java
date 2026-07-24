package com.inventory.auth.config;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig { @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{return http.csrf(csrf->csrf.disable()).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()).build();} @Bean public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();} @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{return config.getAuthenticationManager();} }
