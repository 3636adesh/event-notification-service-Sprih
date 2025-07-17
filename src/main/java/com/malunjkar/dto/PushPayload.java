package com.malunjkar.dto;

import jakarta.validation.constraints.NotBlank;


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

    public PushPayload() {
        // Default constructor
    }

    public PushPayload(String deviceId, String message) {
        this.deviceId = deviceId;
        this.message = message;
    }


    public @NotBlank(message = "deviceId is required") String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NotBlank(message = "deviceId is required") String deviceId) {
        this.deviceId = deviceId;
    }

    public @NotBlank(message = "message is required") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "message is required") String message) {
        this.message = message;
    }
}
