package com.malunjkar.processor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.malunjkar.constant.EventStatus;
import com.malunjkar.dto.EmailPayload;
import com.malunjkar.model.Event;
import com.malunjkar.util.CallbackNotifier;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Simulates sending EMAIL notifications and callback posting.
 *
 * @author adesh.malunjkar
 */
@Component
public class EmailProcessor implements EventProcessor {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmailProcessor.class);

    private final Random random = new Random();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Event event) {
        try {
            EmailPayload payload = mapper.convertValue(event.getPayload(), EmailPayload.class);
            log.info("📧 Sending Email to {} | Message: {}", payload.getRecipient(), payload.getMessage());

            Thread.sleep(1000); // simulate processing time

            if (random.nextBoolean()) {
                throw new RuntimeException("Simulated email failure");
            }

            event.setStatus(EventStatus.COMPLETED);
            log.info("✅ Email sent successfully to {}", payload.getRecipient());

        } catch (Exception ex) {
            event.setStatus(EventStatus.FAILED);
            log.error("❌ Email send failed: {}", ex.getMessage());
        } finally {
            CallbackNotifier.notify(event);
        }
    }
}