package com.seknova.api.controller;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.EventRequest;
import com.seknova.api.service.AuthService;
import com.seknova.api.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;
    private final AuthService authService;
    
    private Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return authService.getUserIdFromToken(token);
    }
    
    @GetMapping("/Eventall")
    public ApiResponse<?> getAllEvents(
            @RequestHeader("Authorization") String token) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return eventService.getAllEvents(userId);
    }
    
    @PostMapping("/add")
    public ApiResponse<?> addEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody EventRequest request) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return eventService.addEvent(userId, request);
    }
    
    @PatchMapping("/change")
    public ApiResponse<?> changeEvent(
            @RequestHeader("Authorization") String token,
            @RequestBody EventRequest request,
            @RequestParam Long id) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return eventService.changeEvent(id, request);
    }
    
    @DeleteMapping("/delete")
    public ApiResponse<?> deleteEvent(
            @RequestHeader("Authorization") String token,
            @RequestParam Long id) {
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return new ApiResponse<>("not", "Invalid token");
        }
        return eventService.deleteEvent(id);
    }
}
