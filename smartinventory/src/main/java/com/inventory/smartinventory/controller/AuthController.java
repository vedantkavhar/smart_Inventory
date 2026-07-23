package com.inventory.smartinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.smartinventory.dto.AuthResponse;
import com.inventory.smartinventory.dto.LoginRequest;
import com.inventory.smartinventory.dto.UserDto;
import com.inventory.smartinventory.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/auth/register - Register a new user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDto dto) {
        AuthResponse response = authService.register(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // POST /api/auth/login - Login and get JWT token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
