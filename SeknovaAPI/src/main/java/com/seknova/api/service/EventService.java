package com.seknova.api.service;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.EventRequest;
import com.seknova.api.entity.Event;
import com.seknova.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    
    private final EventRepository eventRepository;
    
    public ApiResponse<?> addEvent(Long userId, EventRequest request) {
        try {
            Event event = new Event();
            event.setUserId(userId);
            event.setDateTime(request.getDateTime());
            event.setDisplayTime(request.getDisplayTime());
            event.setEventAttribute(request.getEventAttribute());
            event.setEventId(request.getEventId());
            event.setEventValue(request.getEventValue());
            event.setNote(request.getNote());
            event.setCheck(request.getCheck());
            
            eventRepository.save(event);
            return new ApiResponse<>("ok", "Event added successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to add event: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> getAllEvents(Long userId) {
        try {
            List<Event> events = eventRepository.findByUserId(userId);
            return new ApiResponse<>("ok", "Events retrieved successfully", events);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to retrieve events: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> changeEvent(Long eventId, EventRequest request) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new Exception("Event not found"));
            
            if (request.getDateTime() != null) event.setDateTime(request.getDateTime());
            if (request.getDisplayTime() != null) event.setDisplayTime(request.getDisplayTime());
            if (request.getEventAttribute() != null) event.setEventAttribute(request.getEventAttribute());
            if (request.getEventId() != null) event.setEventId(request.getEventId());
            if (request.getEventValue() != null) event.setEventValue(request.getEventValue());
            if (request.getNote() != null) event.setNote(request.getNote());
            if (request.getCheck() != null) event.setCheck(request.getCheck());
            
            eventRepository.save(event);
            return new ApiResponse<>("ok", "Event updated successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to update event: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> deleteEvent(Long eventId) {
        try {
            if (!eventRepository.existsById(eventId)) {
                return new ApiResponse<>("not", "Event not found");
            }
            
            eventRepository.deleteById(eventId);
            return new ApiResponse<>("ok", "Event deleted successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to delete event: " + e.getMessage());
        }
    }
}
