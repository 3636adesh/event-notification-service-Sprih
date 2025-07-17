package com.malunjkar.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Sample:
 * {
 * "phoneNumber": "+911234567890",
 * "message": "Your OTP is 123456"
 * }
 *
 * @author adesh.malunjkar
 */
public class SmsPayload {

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    @NotBlank(message = "message is required")
    private String message;

    // Constructors
    public SmsPayload() {}

    public SmsPayload(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
