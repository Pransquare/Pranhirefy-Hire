package com.pranhirefy.hire.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CandidateAddressFallbackHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", "Invalid endpoint");
        response.put("path", uri);
        response.put("message", "The requested URL is not found. Please check the endpoint.");
        return ResponseEntity.status(404).body(response);
    }
}
