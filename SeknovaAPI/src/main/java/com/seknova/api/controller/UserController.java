package com.seknova.api.controller;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.UserInformationRequest;
import com.seknova.api.service.AuthService;
import com.seknova.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final AuthService authService;
    
    private Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return authService.getUserIdFromToken(token);
    }
    
    @PostMapping("/information")
    public ApiResponse<?> setUserInformation(
            @RequestHeader("Authorization") String token,
            @RequestBody UserInformationRequest request) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return userService.setUserInformation(userId, request);
    }
    
    @GetMapping("/information")
    public ApiResponse<?> getUserInformation(
            @RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return userService.getUserInformation(userId);
    }
}
