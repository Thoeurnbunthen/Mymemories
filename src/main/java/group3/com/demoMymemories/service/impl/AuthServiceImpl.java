package group3.com.demoMymemories.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group3.com.demoMymemories.dto.LoginRequest;
import group3.com.demoMymemories.dto.RegisterRequest;
import group3.com.demoMymemories.dto.RegisterRespone;
import group3.com.demoMymemories.dto.AuthResponse;
import group3.com.demoMymemories.entity.User;
import group3.com.demoMymemories.repository.UserRepository;
import group3.com.demoMymemories.security.JwtProvider;
import group3.com.demoMymemories.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // ✅ REGISTER
    @Override
    public RegisterRespone register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Request → Entity
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        // Entity → Response
        RegisterRespone response = new RegisterRespone();
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setMessage("Register successfully");

        return response;
    }

    // ✅ LOGIN
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getEmail());

        return new AuthResponse(token, "Login successfully");
    }
}
