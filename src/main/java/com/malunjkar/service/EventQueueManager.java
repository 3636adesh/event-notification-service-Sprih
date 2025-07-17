package com.malunjkar.service;
import com.malunjkar.model.Event;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * EventQueueManager.java
 *
 * Manages FIFO queues for different event types (EMAIL, SMS, PUSH).
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
@Component
public class EventQueueManager {

    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EventQueueManager.class);

    private final Map<String, BlockingQueue<Event>> queues = Map.of(
            "EMAIL", new LinkedBlockingQueue<>(),
            "SMS", new LinkedBlockingQueue<>(),
            "PUSH", new LinkedBlockingQueue<>()
    );

    public void enqueue(Event event) {
        BlockingQueue<Event> queue = queues.get(event.getType().toUpperCase());
        if (queue != null) {
            try {
                queue.put(event);
                log.info("📥 Event enqueued to {} queue | ID: {}", event.getType(), event.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("❌ Failed to enqueue event: {}", e.getMessage());
            }
        } else {
            log.warn("⚠️ No queue found for event type: {}", event.getType());
        }
    }

    public BlockingQueue<Event> getQueue(String type) {
        return queues.get(type.toUpperCase());
    }
}