package com.malunjkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * * Main application class for the Event Notification Service.
 */
@RestController
@SpringBootApplication
public class EventNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventNotificationServiceApplication.class, args);
	}

	@GetMapping
	String home() {
		return "Welcome to the Event Notification Service!";
	}
}
