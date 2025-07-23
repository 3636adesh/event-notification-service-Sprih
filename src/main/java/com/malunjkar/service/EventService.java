package com.malunjkar.service;

import com.malunjkar.constant.EventType;
import com.malunjkar.model.Event;
import com.malunjkar.processor.EventProcessor;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class EventService {

    private final BlockingQueue<Event> emailQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Event> smsQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Event> pushQueue = new LinkedBlockingQueue<>();
    private volatile boolean acceptingEvents = true;

    @Autowired
    private CallbackService callbackService;

    @Autowired
    public EventService(ExecutorService executorService) {
        executorService.submit(new EventProcessor(emailQueue, callbackService, EventType.EMAIL, 5000));
        executorService.submit(new EventProcessor(smsQueue, callbackService, EventType.SMS, 3000));
        executorService.submit(new EventProcessor(pushQueue, callbackService, EventType.PUSH, 2000));
    }

    public String addEvent(Event event) {
        if (!acceptingEvents) {
            throw new IllegalStateException("System is shutting down, not accepting new events.");
        }
        String eventId = UUID.randomUUID().toString();
        event.setEventType(event.getEventType());
        switch (event.getEventType()) {
            case EMAIL:
                emailQueue.add(event);
                break;
            case SMS:
                smsQueue.add(event);
                break;
            case PUSH:
                pushQueue.add(event);
                break;
        }
        return eventId;
    }

    @PreDestroy
    public void shutdown() {
        acceptingEvents = false;
        // Allow queues to process remaining events
        while (!emailQueue.isEmpty() || !smsQueue.isEmpty() || !pushQueue.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}