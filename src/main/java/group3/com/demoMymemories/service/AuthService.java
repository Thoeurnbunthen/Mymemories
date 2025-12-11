package group3.com.demoMymemories.service;

import group3.com.demoMymemories.dto.LoginRequest;
import group3.com.demoMymemories.dto.RegisterRequest;
import group3.com.demoMymemories.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
