package com.seknova.api.controller;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.RecordRequest;
import com.seknova.api.service.TokenProvider;
import com.seknova.api.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class RecordController {
    
    private final RecordService recordService;
    private final TokenProvider tokenProvider;
    
    private Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return tokenProvider.getUserIdFromToken(token);
    }
    
    @GetMapping("/Recordall")
    public ApiResponse<?> getAllRecords(
            @RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return recordService.getAllRecords(userId);
    }
    
    @PostMapping("/add")
    public ApiResponse<?> addRecord(
            @RequestHeader("Authorization") String token,
            @RequestBody RecordRequest request) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return recordService.addRecord(userId, request);
    }
    
    @PatchMapping("/change")
    public ApiResponse<?> changeRecord(
            @RequestHeader("Authorization") String token,
            @RequestBody RecordRequest request,
            @RequestParam Long id) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return recordService.changeRecord(id, request);
    }
    
    @DeleteMapping("/delete")
    public ApiResponse<?> deleteRecord(
            @RequestHeader("Authorization") String token,
            @RequestParam Long id) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return recordService.deleteRecord(id);
    }
}
