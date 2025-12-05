package com.seknova.api.controller;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.service.TokenProvider;
import com.seknova.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthInfoController {
    
    private final UserService userService;
    private final TokenProvider tokenProvider;
    
    private Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return tokenProvider.getUserIdFromToken(token);
    }
    
    @GetMapping("/UserAuth")
    public ApiResponse<?> getUserAuth(
            @RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return userService.getUserAuth(userId);
    }
}
