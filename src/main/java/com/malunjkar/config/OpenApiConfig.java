package com.malunjkar.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventNotificationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Notification API")
                        .description("Handles EMAIL, SMS, and PUSH events asynchronously")
                        .version("1.0.0"));
    }
}
