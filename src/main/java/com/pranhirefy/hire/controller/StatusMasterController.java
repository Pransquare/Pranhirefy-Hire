



package com.pranhirefy.hire.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pranhirefy.hire.dto.StatusMasterDto;
import com.pranhirefy.hire.exception.StatusMasterException;
import com.pranhirefy.hire.exception.StatusMasterValidationException;
import com.pranhirefy.hire.service.StatusMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/statusMaster")

public class StatusMasterController {

    private static final Logger logger = LoggerFactory.getLogger(StatusMasterController.class);

    private final StatusMasterService statusMasterService;

    public StatusMasterController(StatusMasterService statusMasterService) {
        this.statusMasterService = statusMasterService;
        logger.info("StatusMasterController initialized");
    }


    
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStatusMaster(@RequestBody(required = false) StatusMasterDto dto) {
        logger.info("Creating StatusMaster with statusMasterCode: {}, statusMasterDescription: {}",
                dto != null ? dto.getStatusMasterCode() : "null",
                dto != null ? dto.getStatusMasterDescription() : "null");

        StatusMasterDto created = statusMasterService.createStatusMaster(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "StatusMaster created successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
    
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateStatusMaster(@PathVariable Long id,
                                                                  @RequestBody(required = false) StatusMasterDto dto) {
        logger.info("Updating StatusMaster with ID {} and statusMasterCode: {}, statusMasterDescription: {}",
                id,
                dto != null ? dto.getStatusMasterCode() : "null",
                dto != null ? dto.getStatusMasterDescription() : "null");

        StatusMasterDto updated = statusMasterService.updateStatusMaster(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "StatusMaster updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<StatusMasterDto> getStatusMasterById(@PathVariable Long id) {
        logger.info("Fetching StatusMaster by ID: {}", id);
        StatusMasterDto result = statusMasterService.getStatusMasterById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StatusMasterDto>> getAllStatusMasters() {
        logger.info("Fetching all StatusMasters");
        List<StatusMasterDto> list = statusMasterService.getAllMasters();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMaster(@PathVariable("id") Long masterId) {
        logger.info("Deleting StatusMaster with ID: {}", masterId);
        statusMasterService.deleteMaster(masterId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Soft Deletion successful for StatusMaster with ID: " + masterId);
        

        return ResponseEntity.ok(response);
    }


   
    @GetMapping("/search")
    public ResponseEntity<List<StatusMasterDto>> searchByDescription(@RequestParam(required = false) String statusMasterDescription) {
        if (statusMasterDescription == null || statusMasterDescription.isBlank()) {
            throw new StatusMasterException("The 'statusMasterDescription' query parameter is missing. Please provide it to perform search.");
        }

        List<StatusMasterDto> results = statusMasterService.searchStatusMasters(statusMasterDescription);
        return ResponseEntity.ok(results);
    }

    // Exception Handlers

    @ExceptionHandler(StatusMasterException.class)
    public ResponseEntity<Map<String, Object>> handleStatusMasterException(StatusMasterException e) {
        logger.error("StatusMasterException: {}", e.getMessage());
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("Malformed JSON request or invalid input types: {}", ex.getMessage());

        Map<String, String> errorResponse = new HashMap<>();
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            List<Reference> path = ife.getPath();

            String fieldName = path.stream()
                    .map(Reference::getFieldName)
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining("."));

            String targetType = ife.getTargetType() != null ? ife.getTargetType().getSimpleName() : "Unknown";
            String invalidValue = ife.getValue() != null ? ife.getValue().toString() : "null";

            String message = String.format("Field %s received invalid value '%s'. Expected type: %s.",
                    fieldName, invalidValue, targetType);

            errorResponse.put(fieldName, message);
        } else {
            errorResponse.put("error", "Malformed JSON request or invalid input types");
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        logger.error("Invalid parameter type: {}", e.getMessage());
        return new ResponseEntity<>("Invalid parameter type: " + e.getName(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String fieldName = ex.getPath().get(0).getFieldName();
        String msg = "Field '" + fieldName + "' must be a valid integer.";
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred", ex);
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    


    @RequestMapping(value = "/**")
    public ResponseEntity<String> handleInvalidSubPath() {
        logger.warn("Invalid endpoint accessed under /api/status-master");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid point, please check the URL.");
    }
    @ExceptionHandler(StatusMasterValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(StatusMasterValidationException ex) {
        return new ResponseEntity<>(ex.getFieldErrors(), HttpStatus.BAD_REQUEST);
    }

  
    

}
