package com.seknova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
    private String country; // Only for register
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class AuthResponse {
    private String status;
    private String message;
    private String token; // Only for login
}
