package com.inventory.smartinventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.AuthResponse;
import com.inventory.smartinventory.dto.LoginRequest;
import com.inventory.smartinventory.dto.UserDto;
import com.inventory.smartinventory.entity.Role;
import com.inventory.smartinventory.entity.User;
import com.inventory.smartinventory.exception.DuplicateRecordException;
import com.inventory.smartinventory.repository.UserRepository;
import com.inventory.smartinventory.security.JwtUtil;
import com.inventory.smartinventory.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse register(UserDto dto) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateRecordException("Username already taken: " + dto.getUsername());
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateRecordException("Email already registered: " + dto.getEmail());
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        // Default role is EMPLOYEE if not provided
        user.setRole(dto.getRole() != null ? dto.getRole() : Role.EMPLOYEE);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Authenticate using Spring Security (throws exception if credentials are wrong)
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // If authentication is successful, generate and return the JWT token
        String token = jwtUtil.generateToken(auth.getName());
        User user = userRepository.findByUsername(auth.getName()).get();
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
