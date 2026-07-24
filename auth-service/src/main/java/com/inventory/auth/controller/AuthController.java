package com.inventory.auth.controller;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.inventory.auth.dto.*;
import com.inventory.auth.service.AuthService;
import jakarta.validation.Valid;
@RestController @RequestMapping("/api/auth") public class AuthController { private final AuthService authService; public AuthController(AuthService authService){this.authService=authService;} @PostMapping("/register") public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDto dto){return new ResponseEntity<>(authService.register(dto),HttpStatus.CREATED);} @PostMapping("/login") public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){return ResponseEntity.ok(authService.login(request));} }
