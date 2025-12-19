package group3.com.demoMymemories.service;

import group3.com.demoMymemories.dto.LoginRequest;
import group3.com.demoMymemories.dto.RegisterRequest;
import group3.com.demoMymemories.dto.RegisterRespone;
import group3.com.demoMymemories.dto.AuthResponse;

public interface AuthService {
    RegisterRespone register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
