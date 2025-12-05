package com.seknova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status; // "ok" or "not"
    private String message;
    private T data;
    
    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
