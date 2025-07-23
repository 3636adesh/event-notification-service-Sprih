package com.malunjkar.controller;


import com.malunjkar.model.EmailEvent;
import com.malunjkar.model.Event;
import com.malunjkar.model.EventResponse;
import com.malunjkar.model.PushEvent;
import com.malunjkar.model.SmsEvent;
import com.malunjkar.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody Event event) {
        if (!isValidEvent(event)) {
            return ResponseEntity.badRequest().body(new EventResponse(null, "Invalid event type or payload"));
        }
        String eventId = eventService.addEvent(event);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new EventResponse(eventId, "Event accepted for processing."));
    }

    private boolean isValidEvent(Event event) {
        try {
            switch (event.getEventType()) {
                case EMAIL:
                    EmailEvent.class.cast(event.getPayload());
                    break;
                case SMS:
                    SmsEvent.class.cast(event.getPayload());
                    break;
                case PUSH:
                    PushEvent.class.cast(event.getPayload());
                    break;
                default:
                    return false;
            }
            return event.getCallbackUrl() != null && !event.getCallbackUrl().isEmpty();
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }
}