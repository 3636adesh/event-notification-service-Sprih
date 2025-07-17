package com.malunjkar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Sample:
 * {
 *   "recipient": "user@example.com",
 *   "message": "Welcome to our service!"
 * }
 *
 * @author adesh.malunjkar
 */
@Data
public class EmailPayload {

    @Email(message = "recipient must be a valid email")
    @NotBlank(message = "recipient is required")
    private String recipient;

    @NotBlank(message = "message is required")
    private String message;
}
