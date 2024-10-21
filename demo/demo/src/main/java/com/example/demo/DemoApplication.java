package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // This annotation enables auto-configuration and component scanning
public class DemoApplication {

    public static void main(String[] args) {
        // Runs the Spring Boot application
        SpringApplication.run(DemoApplication.class, args);
    }

}

