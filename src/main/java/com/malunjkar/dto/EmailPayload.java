package com.malunjkar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Sample:
 * {
 *   "recipient": "user@example.com",
 *   "message": "Welcome to our service!"
 * }
 *
 * @author adesh.malunjkar
 */

public class EmailPayload {

    @Email(message = "recipient must be a valid email")
    @NotBlank(message = "recipient is required")
    private String recipient;

    @NotBlank(message = "message is required")
    private String message;

    public EmailPayload() {
        // Default constructor
    }

    public EmailPayload(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public @Email(message = "recipient must be a valid email") @NotBlank(message = "recipient is required") String getRecipient() {
        return recipient;
    }

    public void setRecipient(@Email(message = "recipient must be a valid email") @NotBlank(message = "recipient is required") String recipient) {
        this.recipient = recipient;
    }

    public @NotBlank(message = "message is required") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "message is required") String message) {
        this.message = message;
    }
}
