package com.malunjkar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Sample:
 * {
 * "phoneNumber": "+911234567890",
 * "message": "Your OTP is 123456"
 * }
 *
 * @author adesh.malunjkar
 */
@Data
public class SmsPayload {

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    @NotBlank(message = "message is required")
    private String message;
}
