package com.malunjkar.service;

import com.malunjkar.constant.EventStatus;
import com.malunjkar.model.Event;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * EventService.java
 * <p>
 * Handles creation and dispatching of events to the appropriate queue.
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
@Service
public class EventService {


    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EventService.class);

    private final EventQueueManager queueManager;

    public EventService(EventQueueManager queueManager) {
        this.queueManager = queueManager;
    }

    public String submitEvent(Event event) {
        event.setId(UUID.randomUUID().toString());
        event.setStatus(EventStatus.PENDING);

        queueManager.enqueue(event);

        log.info("📨 Event submitted: ID={}, Type={}, Callback={}", event.getId(), event.getType(), event.getCallbackUrl());

        return event.getId();
    }
}
