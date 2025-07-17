package com.malunjkar.dto;

import com.malunjkar.constant.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author adesh.malunjkar
 */
public class EventRequest {

    @NotNull(message = "eventType is required")
    private EventType eventType;

    @NotNull(message = "payload is required")
    private Object payload;

    @NotBlank(message = "callbackUrl is required")
    private String callbackUrl;

    public EventRequest(){

    }

    public EventRequest(EventType eventType, Object payload, String callbackUrl) {
        this.eventType = eventType;
        this.payload = payload;
        this.callbackUrl = callbackUrl;
    }

    public @NotNull(message = "eventType is required") EventType getEventType() {
        return eventType;
    }

    public void setEventType(@NotNull(message = "eventType is required") EventType eventType) {
        this.eventType = eventType;
    }

    public @NotNull(message = "payload is required") Object getPayload() {
        return payload;
    }

    public void setPayload(@NotNull(message = "payload is required") Object payload) {
        this.payload = payload;
    }

    public @NotBlank(message = "callbackUrl is required") String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(@NotBlank(message = "callbackUrl is required") String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
