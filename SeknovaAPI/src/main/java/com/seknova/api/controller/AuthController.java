package com.seknova.api.controller;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.AuthRequest;
import com.seknova.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody AuthRequest request) {
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
