package com.inventory.auth.service;
import com.inventory.auth.dto.*;
public interface AuthService { AuthResponse register(UserDto dto); AuthResponse login(LoginRequest request); }
