package com.malunjkar.model;

import com.malunjkar.constant.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author adesh.malunjkar
 */

@Builder
public class Event {
    private String eventId;
    private EventType eventType;
    private Object payload;
    private String callbackUrl;
    private LocalDateTime createdAt;
}
