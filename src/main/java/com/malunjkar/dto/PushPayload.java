package com.malunjkar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Sample:
 * {
 * "deviceId": "abc-123-xyz",
 * "message": "Your order has been shipped!"
 * }
 *
 * @author Adesh Malunjkar
 */
public class PushPayload {

    @NotBlank(message = "deviceId is required")
    private String deviceId;

    @NotBlank(message = "message is required")
    private String message;
}
