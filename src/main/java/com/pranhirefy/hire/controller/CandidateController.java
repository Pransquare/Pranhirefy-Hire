package com.pranhirefy.hire.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranhirefy.hire.dto.CandidateDto;
import com.pranhirefy.hire.exception.CandidateNotFoundException;
//import com.pranhirefy.hire.exception.ResourceNotFoundException;
import com.pranhirefy.hire.service.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService candidateService;
    
    
    @RequestMapping("*")
    public ResponseEntity<Map<String, String>> handleUnknownRequest() {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid API endpoint. Please check the URL.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/GettingAll")
    public List<CandidateDto> getAllCandidates() {
        logger.info("Received request to fetch all candidates");
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateDto getCandidateById(@PathVariable Integer id) {
        logger.debug("Received request to fetch candidate with ID: {}", id);
        return candidateService.getCandidateById(id);
    }

    @PostMapping("/candidatecreate")
    public CandidateDto createCandidate(@Valid @RequestBody CandidateDto dto) {
        logger.info("Received request to create a new candidate");
        return candidateService.createCandidate(dto);
    }

    @PutMapping("/{id}")
    public CandidateDto updateCandidate(@PathVariable Integer id, @Valid @RequestBody CandidateDto dto) {
        logger.info("Received request to update candidate with ID: {}", id);
        return candidateService.updateCandidate(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Integer id) {
        logger.warn("Received request to delete candidate with ID: {}", id);
        candidateService.softDeleteCandidate(id);
    }

    // ============= Local Exception Handlers ============= //

    
 // Validation error
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    // Unsupported HTTP method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Method Not Allowed: " + ex.getMethod());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Malformed JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Malformed or invalid request body");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Resource not found
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(CandidateNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Generic catch-all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleServerError(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error. Please check the request path or data.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Unknown request path
//    @RequestMapping("*")
//    public ResponseEntity<Map<String, String>> handleUnknownRequest1() {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", "Invalid API endpoint. Please check the URL.");
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
