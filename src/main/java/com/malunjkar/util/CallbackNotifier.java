package com.malunjkar.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malunjkar.model.Event;
import org.slf4j.Logger;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * CallbackNotifier.java
 *
 * Utility to send HTTP POST callbacks after event processing.
 *
 * Posts to the event's callbackUrl with JSON payload:
 * {
 *   "eventId": "<event-id>",
 *   "status": "COMPLETED" or "FAILED"
 * }
 *
 * @author Adesh Malunjkar
 * @since 2025-07-17
 */
public class CallbackNotifier {


    private final static Logger log = org.slf4j.LoggerFactory.getLogger(CallbackNotifier.class);


    private static final ObjectMapper mapper = new ObjectMapper();

    public static void notify(Event event) {
        try {
            String callbackUrl = event.getCallbackUrl();

            if (callbackUrl == null || callbackUrl.isBlank()) {
                log.warn("⚠️ No callback URL provided for event {}", event.getId());
                return;
            }

            URL url = new URL(callbackUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            Map<String, String> payload = new HashMap<>();
            payload.put("eventId", event.getId());
            payload.put("status", event.getStatus().name());

            try (OutputStream os = connection.getOutputStream()) {
                mapper.writeValue(os, payload);
            }

            int responseCode = connection.getResponseCode();
            log.info("🔄 Callback to {} responded with HTTP {}", callbackUrl, responseCode);

        } catch (Exception e) {
            log.error("❌ Failed to send callback for event {}: {}", event.getId(), e.getMessage(), e);
        }
    }
}
