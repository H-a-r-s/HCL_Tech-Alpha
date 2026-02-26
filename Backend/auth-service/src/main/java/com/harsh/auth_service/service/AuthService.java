package com.harsh.auth_service.service;

import com.harsh.auth_service.dto.AuthResponse;
import com.harsh.auth_service.dto.LoginRequest;
import com.harsh.auth_service.dto.RegisterRequest;
import com.harsh.auth_service.entity.Role;
import com.harsh.auth_service.entity.User;
import com.harsh.auth_service.repository.UserRepository;
import com.harsh.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role("USER")
                .build();

        repo.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token,user.getRole());
    }
}