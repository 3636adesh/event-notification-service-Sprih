package com.malunjkar.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malunjkar.constant.EventStatus;
import com.malunjkar.dto.SmsPayload;
import com.malunjkar.model.Event;
import com.malunjkar.service.EventService;
import com.malunjkar.util.CallbackNotifier;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * SmsProcessor.java
 *
 * Simulates sending SMS notifications and posts the callback.
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
@Component
public class SmsProcessor implements EventProcessor {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EventService.class);


    private final Random random = new Random();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Event event) {
        try {
            SmsPayload payload = mapper.convertValue(event.getPayload(), SmsPayload.class);

            log.info("📲 Sending SMS to {}: {}", payload.getPhoneNumber(), payload.getMessage());

            Thread.sleep(800); // simulate processing delay

            if (random.nextBoolean()) {
                log.info("✅ SMS sent successfully to {}", payload.getPhoneNumber());
                event.setStatus(EventStatus.COMPLETED);
            } else {
                throw new RuntimeException("Simulated SMS failure");
            }

        } catch (Exception e) {
            log.error("❌ Failed to send SMS: {}", e.getMessage());
            event.setStatus(EventStatus.FAILED);
        } finally {
            CallbackNotifier.notify(event);
        }
    }
}
