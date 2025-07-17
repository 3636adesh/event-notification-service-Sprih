package com.malunjkar.processor;

import com.malunjkar.constant.EventStatus;
import com.malunjkar.dto.PushPayload;
import com.malunjkar.model.Event;
import com.malunjkar.util.CallbackNotifier;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * PushProcessor.java
 *
 * Simulates sending PUSH notifications and callback posting.
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
@Component
public class PushProcessor implements EventProcessor {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(PushProcessor.class);


    private final Random random = new Random();

    @Override
    public void process(Event event) {
        PushPayload payload = (PushPayload) event.getPayload();

        try {
            Thread.sleep(700); // simulate delay

            if (random.nextBoolean()) {
                log.info("✅ Push sent to device {}: {}", payload.getDeviceId(), payload.getMessage());
                event.setStatus(EventStatus.COMPLETED);
            } else {
                throw new RuntimeException("Simulated Push failure");
            }

        } catch (Exception e) {
            log.error("❌ Failed to send Push to device {}: {}", payload.getDeviceId(), e.getMessage());
            event.setStatus(EventStatus.FAILED);
        }

        CallbackNotifier.notify(event);
    }
}
