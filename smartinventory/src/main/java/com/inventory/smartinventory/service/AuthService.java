package com.inventory.smartinventory.service;

import com.inventory.smartinventory.dto.AuthResponse;
import com.inventory.smartinventory.dto.LoginRequest;
import com.inventory.smartinventory.dto.UserDto;

public interface AuthService {
    AuthResponse register(UserDto dto);
    AuthResponse login(LoginRequest request);
}
