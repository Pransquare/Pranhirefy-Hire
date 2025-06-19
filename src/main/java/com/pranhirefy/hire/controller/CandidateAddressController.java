package com.pranhirefy.hire.controller;

import com.pranhirefy.hire.dto.CandidateAddressDto;
import com.pranhirefy.hire.exception.CandidateAddressDataException;
import com.pranhirefy.hire.exception.CandidateAddressNotFoundException;
import com.pranhirefy.hire.exception.ResourceNotFoundsException;
import com.pranhirefy.hire.service.CandidateAddressService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateAddressController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateAddressController.class);

    @Autowired
    private CandidateAddressService addressService;

    @PostMapping("/CandidateAddressCreate")
    public ResponseEntity<CandidateAddressDto> create(@Valid @RequestBody CandidateAddressDto dto) {
        logger.info("Received request to create address");
        return new ResponseEntity<>(addressService.createAddress(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateAddressDto> getById(@PathVariable Integer id) {
        logger.info("Received request to fetch address with ID: {}", id);
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/GettingAll")
    public ResponseEntity<List<CandidateAddressDto>> getAll() {
        logger.info("Received request to fetch all addresses");
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CandidateAddressDto> update(@PathVariable Integer id, @RequestBody CandidateAddressDto dto) {
//        logger.info("Received request to update address with ID: {}", id);
//        return ResponseEntity.ok(addressService.updateAddress(id, dto));
//    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @Valid @RequestBody CandidateAddressDto dto,
            BindingResult bindingResult) {

        logger.info("Received request to update address with ID: {}", id);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        CandidateAddressDto updatedDto = addressService.updateAddress(id, dto);
        return ResponseEntity.ok(updatedDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        logger.info("Received request to delete address with ID: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address marked as deleted");
    }


    
 // ✅ Handle business rule exception (like duplicate 'Present' address)
    @ExceptionHandler(CandidateAddressDataException.class)
    public ResponseEntity<Map<String, Object>> handleCandidateAddressDataException(CandidateAddressDataException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Candidate Address Conflict");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ✅ Handle resource not found
    @ExceptionHandler(ResourceNotFoundsException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundsException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Resource Not Found");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // ✅ Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Validation Error");

        List<String> fieldErrors = new ArrayList<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.add(err.getField() + ": " + err.getDefaultMessage());
        }
        errors.put("messages", fieldErrors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
 // 🔹 Handle "Candidate not found" error
    @ExceptionHandler(CandidateAddressNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCandidateAddressNotFound(CandidateAddressNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Candidate Id Not Found In Candidate");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    

    @RestController
    public class FallbackController {

      @RequestMapping(value = "/**")
      public ResponseEntity<String> fallback() {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested URL was not found on this server");
      }
    }
}
