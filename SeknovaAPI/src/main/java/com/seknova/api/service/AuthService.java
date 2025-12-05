package com.seknova.api.service;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.AuthRequest;
import com.seknova.api.entity.UserAuth;
import com.seknova.api.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    
    public ApiResponse<?> register(AuthRequest request) {
        // Check if email already exists
        if (userAuthRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse<>("not", "Email already registered");
        }
        
        try {
            UserAuth userAuth = new UserAuth();
            userAuth.setEmail(request.getEmail());
            userAuth.setPassword(passwordEncoder.encode(request.getPassword()));
            userAuth.setCountry(request.getCountry());
            userAuth.setIsFirst(true);
            
            userAuthRepository.save(userAuth);
            return new ApiResponse<>("ok", "Registration successful");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Registration failed: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> login(AuthRequest request) {
        try {
            var userAuth = userAuthRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new Exception("User not found"));
            
            if (!passwordEncoder.matches(request.getPassword(), userAuth.getPassword())) {
                return new ApiResponse<>("not", "Invalid password");
            }
            
            String token = tokenProvider.generateToken(userAuth.getUserId());
            return new ApiResponse<>("ok", "Login successful", token);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Login failed: " + e.getMessage());
        }
    }
}
