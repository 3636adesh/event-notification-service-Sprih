// src/test/java/com/sprih/service/EventServiceTest.java
package com.malunjkar;

import com.malunjkar.constant.EventType;
import com.malunjkar.model.EmailEvent;
import com.malunjkar.model.Event;
import com.malunjkar.model.PushEvent;
import com.malunjkar.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        // Initialize with a new executor for testing
    }

    @Test
    public void testAddEventToCorrectQueue() {
        Event emailEvent = new Event();
        emailEvent.setEventType(EventType.EMAIL);
        EmailEvent emailPayload = new EmailEvent();
        emailPayload.setRecipient("user@example.com");
        emailPayload.setMessage("Test email");
        emailPayload.setCallbackUrl("http://client.com/callback");
        emailEvent.setPayload(emailPayload);
        emailEvent.setCallbackUrl("http://client.com/callback");

        String eventId = eventService.addEvent(emailEvent);
        assertNotNull(eventId);
    }

    @Test
    public void testGracefulShutdown() {
        Event event = new Event();
        event.setEventType(EventType.PUSH);
        PushEvent payload = new PushEvent();
        payload.setDeviceId("abc-123");
        payload.setMessage("Test push");
        payload.setCallbackUrl("http://client.com/callback");
        event.setPayload(payload);
        event.setCallbackUrl("http://client.com/callback");

        eventService.addEvent(event);
        eventService.shutdown();
        assertThrows(IllegalStateException.class, () -> eventService.addEvent(event));
    }
}