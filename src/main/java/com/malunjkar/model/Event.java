package com.malunjkar.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.malunjkar.constant.EventType;

public class Event {
    private EventType eventType;
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "eventType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = EmailEvent.class, name = "EMAIL"),
            @JsonSubTypes.Type(value = SmsEvent.class, name = "SMS"),
            @JsonSubTypes.Type(value = PushEvent.class, name = "PUSH")
    })
    private Object payload;
    private String callbackUrl;

    public Event() {
    }

    public Event(EventType eventType, Object payload, String callbackUrl) {
        this.eventType = eventType;
        this.payload = payload;
        this.callbackUrl = callbackUrl;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", payload=" + payload +
                ", callbackUrl='" + callbackUrl + '\'' +
                '}';
    }
}
