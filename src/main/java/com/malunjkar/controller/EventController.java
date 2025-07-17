package com.malunjkar.controller;

import com.malunjkar.model.Event;
import com.malunjkar.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * EventController.java
 *
 * REST controller to receive event submissions.
 *
 * Example Endpoint: POST /api/events
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<String> receiveEvent(@Valid @RequestBody Event event) {
        String eventId = eventService.submitEvent(event);
        return ResponseEntity.ok(eventId);
    }
}
