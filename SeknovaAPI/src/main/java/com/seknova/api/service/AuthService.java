package com.seknova.api.service;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.AuthRequest;
import com.seknova.api.entity.UserAuth;
import com.seknova.api.repository.UserAuthRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
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
            
            String token = generateToken(userAuth.getUserId());
            return new ApiResponse<>("ok", "Login successful", token);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Login failed: " + e.getMessage());
        }
    }
    
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public Long getUserIdFromToken(String token) {
        try {
            String subject = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return Long.parseLong(subject);
        } catch (Exception e) {
            return null;
        }
    }
}
