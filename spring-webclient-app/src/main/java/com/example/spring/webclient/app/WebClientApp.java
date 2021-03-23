package com.example.spring.webclient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class WebClientApp {
    public static void main(String[] args) {
        SpringApplication.run(WebClientApp.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }
}
