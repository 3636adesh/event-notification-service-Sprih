package com.malunjkar.model;

import com.malunjkar.constant.EventStatus;


import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Event.java
 *
 * Represents a notification event with payload and callback support.
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */

public class Event {

    private String id = UUID.randomUUID().toString();

    private String type; // EMAIL, SMS, PUSH

    private Map<String, Object> payload;

    private String callbackUrl;

    private EventStatus status = EventStatus.PENDING;

    private Instant createdAt = Instant.now();

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
