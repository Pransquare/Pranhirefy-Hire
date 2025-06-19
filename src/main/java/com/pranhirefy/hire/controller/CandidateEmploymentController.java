package com.pranhirefy.hire.controller;

import com.pranhirefy.hire.dto.CandidateEmployeementDto;
import com.pranhirefy.hire.exception.*;
import com.pranhirefy.hire.service.CandidateEmploymentService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employments")
public class CandidateEmploymentController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateEmploymentController.class);

    private final CandidateEmploymentService service;

    public CandidateEmploymentController(CandidateEmploymentService service) {
        this.service = service;
    }

//    @PostMapping("/CandidateEmployeecreate")
//    public ResponseEntity<?> create(@Valid @RequestBody CandidateEmployeementDto dto) {
//        logger.info("POST /api/employments called");
//        CandidateEmployeementDto created = service.createCandidateEmployment(dto);
//        return new ResponseEntity<>(created, HttpStatus.CREATED);
//    }
    
    @PostMapping("/CandidateEmployeecreate")
    public ResponseEntity<?> create(@Valid @RequestBody CandidateEmployeementDto dto) {
        logger.info("POST /api/employments/CandidateEmployeecreate called");
        CandidateEmployeementDto created = service.createCandidateEmployment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/GettingAll")
    public ResponseEntity<?> getAll() {
        logger.info("GET /api/employments called");
        List<CandidateEmployeementDto> list = service.getAllCandidateEmployments();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/Getting/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        logger.info("GET /api/employments/{} called", id);
        CandidateEmployeementDto dto = service.getCandidateEmploymentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody CandidateEmployeementDto dto) {
        logger.info("PUT /api/employments/{} called", id);
        CandidateEmployeementDto updated = service.updateCandidateEmployment(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        logger.info("DELETE /api/employments/{} called", id);
        service.deleteCandidateEmployment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ========== ERROR HANDLING ==========

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.error("Validation exception: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourcesNotFoundException ex) {
        logger.error("Not found: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CandidateEmployeementValidationException.class)
    public ResponseEntity<?> handleValidation(CandidateEmployeementValidationException ex) {
        logger.error("Custom validation: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidateEmployeementDuplicateCandidateEmploymentException.class)
    public ResponseEntity<?> handleDuplicate(CandidateEmployeementDuplicateCandidateEmploymentException ex) {
        logger.error("Duplicate employment: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        logger.error("Unhandled exception: ", ex);
        return new ResponseEntity<>(new ErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
 // ✅ Fallback method for wrong URLs under /api/employments
    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> handleInvalidEmploymentPath() {
        logger.warn("Invalid endpoint under /api/employments");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid  Employment API path ");
    }


    // Fallback for unmatched routes
    @RequestMapping("/api/employments/**")
    public ResponseEntity<?> fallback() {
        return ResponseEntity.status(404).body("Invalid employment API path");
    }

    // Response wrapper
    static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
