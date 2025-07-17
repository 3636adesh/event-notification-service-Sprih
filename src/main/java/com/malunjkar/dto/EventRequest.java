package com.malunjkar.dto;

import com.malunjkar.constant.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author adesh.malunjkar
 */
@Data
public class EventRequest {

    @NotNull(message = "eventType is required")
    private EventType eventType;

    @NotNull(message = "payload is required")
    private Object payload;

    @NotBlank(message = "callbackUrl is required")
    private String callbackUrl;
}
