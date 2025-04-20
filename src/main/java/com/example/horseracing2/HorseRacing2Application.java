package com.example.horseracing2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorseRacing2Application {
    private static final Logger log = LoggerFactory.getLogger(HorseRacing2Application.class);

    public static void main(String[] args) {
        log.info("Starting HorseRacing2Application");
        SpringApplication.run(HorseRacing2Application.class, args);
        log.info("HorseRacing2Application started successfully");
    }
}
