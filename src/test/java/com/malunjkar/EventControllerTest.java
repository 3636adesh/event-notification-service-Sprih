// src/test/java/com/sprih/controller/EventControllerTest.java
package com.malunjkar;

import com.malunjkar.constant.EventType;
import com.malunjkar.controller.EventController;
import com.malunjkar.model.EmailEvent;
import com.malunjkar.model.Event;
import com.malunjkar.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventService eventService;

    @Test
    public void testValidEmailEvent() throws Exception {
        Event event = new Event();
        event.setEventType(EventType.EMAIL);
        EmailEvent payload = new EmailEvent();
        payload.setRecipient("user@example.com");
        payload.setMessage("Test email");
        payload.setCallbackUrl("http://client.com/callback");
        event.setPayload(payload);
        event.setCallbackUrl("http://client.com/callback");

        when(eventService.addEvent(any(Event.class))).thenReturn("e123");

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"eventType\":\"EMAIL\",\"payload\":{\"recipient\":\"user@example.com\",\"message\":\"Test email\",\"callbackUrl\":\"http://client.com/callback\"},\"callbackUrl\":\"http://client.com/callback\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.eventId").value("e123"))
                .andExpect(jsonPath("$.message").value("Event accepted for processing."));
    }

    @Test
    public void testInvalidEventType() throws Exception {
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"eventType\":\"INVALID\",\"payload\":{},\"callbackUrl\":\"http://client.com/callback\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid event type or payload"));
    }

    @Test
    public void testMissingPayloadFields() throws Exception {
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"eventType\":\"EMAIL\",\"payload\":{\"recipient\":\"user@example.com\"},\"callbackUrl\":\"http://client.com/callback\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid event type or payload"));
    }
}