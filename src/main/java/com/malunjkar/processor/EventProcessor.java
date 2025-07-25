package com.malunjkar.processor;

import com.malunjkar.constant.EventType;
import com.malunjkar.model.Event;
import com.malunjkar.service.CallbackService;

import java.util.concurrent.BlockingQueue;


public class EventProcessor implements Runnable {

    private final BlockingQueue<Event> queue;
    private final CallbackService callbackService;
    private final EventType eventType;
    private final long processingTime;

    public EventProcessor(BlockingQueue<Event> queue, CallbackService callbackService, EventType eventType, long processingTime) {
        this.queue = queue;
        this.callbackService = callbackService;
        this.eventType = eventType;
        this.processingTime = processingTime;
    }

    /**
     * Processes events from the queue. Each event is processed by simulating a delay,
     * and then a callback is sent to the event's callback URL.
     * If processing fails, an error message is included in the callback.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Event event = queue.take();
                String eventId = java.util.UUID.randomUUID().toString();
                Thread.sleep(processingTime);
                boolean isSuccess = !callbackService.simulateRandomFailure();
                String errorMessage = isSuccess ? null : "Simulated processing failure";
                callbackService.sendCallback(event, eventId, isSuccess, errorMessage);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}