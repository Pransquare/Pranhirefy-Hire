package com.pranhirefy.hire.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pranhirefy.hire.dto.CandidateOfferLetterDTO;
//import com.pranhirefy.hire.dto.CandidateOfferLetterDto;
import com.pranhirefy.hire.service.CandidateOfferLetterService;

@RestController
@RequestMapping("/api/offer-letters")
public class CandidateOfferLetterController {

    @Autowired
    private CandidateOfferLetterService service;

    @GetMapping
    public List<CandidateOfferLetterDTO> getAllOfferLetters() {
        return service.getAllOfferLetters();
    }

    @GetMapping("/{id}")
    public CandidateOfferLetterDTO getOfferLetterById(@PathVariable Integer id) {
        return service.getOfferLetterById(id);
    }

    @PostMapping
    public CandidateOfferLetterDTO createOfferLetter(@RequestBody CandidateOfferLetterDTO dto) {
        return service.createOfferLetter(dto);
    }

    @PutMapping("/{id}")
    public CandidateOfferLetterDTO updateOfferLetter(@PathVariable Integer id, @RequestBody CandidateOfferLetterDTO dto) {
        return service.updateOfferLetter(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOfferLetter(@PathVariable Integer id) {
        service.deleteOfferLetter(id);
        return ResponseEntity.ok("Offer Letter deleted (soft delete) successfully.");
    }
}
