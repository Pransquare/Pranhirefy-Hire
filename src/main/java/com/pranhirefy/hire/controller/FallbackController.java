package com.pranhirefy.hire.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);

    // This will catch ALL unmapped requests (all HTTP methods)
    @RequestMapping(value = "/**")
    public ResponseEntity<String> fallback() {
        logger.warn("Invalid endpoint accessed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid Base URL");
    }
}
