package com.inventory.auth.service.impl;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inventory.auth.dto.*;
import com.inventory.auth.entity.*;
import com.inventory.auth.exception.DuplicateRecordException;
import com.inventory.auth.repository.UserRepository;
import com.inventory.auth.security.JwtUtil;
import com.inventory.auth.service.AuthService;
@Service public class AuthServiceImpl implements AuthService { private final UserRepository userRepository; private final PasswordEncoder passwordEncoder; private final AuthenticationManager authenticationManager; private final JwtUtil jwtUtil; public AuthServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtUtil jwtUtil){this.userRepository=userRepository;this.passwordEncoder=passwordEncoder;this.authenticationManager=authenticationManager;this.jwtUtil=jwtUtil;} public AuthResponse register(UserDto dto){if(userRepository.existsByUsername(dto.getUsername()))throw new DuplicateRecordException("Username already taken: "+dto.getUsername());if(userRepository.existsByEmail(dto.getEmail()))throw new DuplicateRecordException("Email already registered: "+dto.getEmail());User user=new User();user.setUsername(dto.getUsername());user.setEmail(dto.getEmail());user.setPassword(passwordEncoder.encode(dto.getPassword()));user.setRole(dto.getRole()!=null?dto.getRole():Role.EMPLOYEE);userRepository.save(user);return new AuthResponse(jwtUtil.generateToken(user.getUsername(), user.getRole().name()),user.getUsername(),user.getRole().name());} public AuthResponse login(LoginRequest request){Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));User user=userRepository.findByUsername(auth.getName()).orElseThrow();return new AuthResponse(jwtUtil.generateToken(user.getUsername(), user.getRole().name()),user.getUsername(),user.getRole().name());} }
