package com.malunjkar.service;

import com.malunjkar.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class CallbackService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    public void sendCallback(Event event, String eventId, boolean isSuccess, String errorMessage) {
        Map<String, Object> callbackBody = new HashMap<>();
        callbackBody.put("eventId", eventId);
        callbackBody.put("status", isSuccess ? "COMPLETED" : "FAILED");
        callbackBody.put("eventType", event.getEventType().toString());
        callbackBody.put("processedAt", Instant.now().toString());
        if (!isSuccess) {
            callbackBody.put("errorMessage", errorMessage);
        }
        try {
            restTemplate.postForEntity(event.getCallbackUrl(), callbackBody, String.class);
        } catch (Exception e) {
            // Log failure (in production, use proper logging)
            System.err.println("Callback failed for eventId: " + eventId + ", URL: " + event.getCallbackUrl());
        }
    }

    public boolean simulateRandomFailure() {
        return random.nextDouble() < 0.1; // 10% failure rate
    }
}