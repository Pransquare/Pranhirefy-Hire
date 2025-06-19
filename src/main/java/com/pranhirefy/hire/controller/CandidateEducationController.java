package com.pranhirefy.hire.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pranhirefy.hire.dto.CandidateEducationDto;
import com.pranhirefy.hire.service.CandidateEducationService;

@RestController
@RequestMapping("/api/educations")
public class CandidateEducationController {

    @Autowired
    private CandidateEducationService educationService;

    @GetMapping
    public List<CandidateEducationDto> getAllEducations() {
        return educationService.getAllEducations();
    }

    @GetMapping("/{id}")
    public CandidateEducationDto getEducationById(@PathVariable Integer id) {
        return educationService.getEducationById(id);
    }

    @PostMapping
    public CandidateEducationDto createEducation(@RequestBody CandidateEducationDto dto) {
        return educationService.createEducation(dto);
    }
    @PutMapping("/{id}")
    public CandidateEducationDto updateEducation(@PathVariable Integer id, @RequestBody CandidateEducationDto dto) {
        return educationService.updateEducation(id, dto);
    }
      
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Integer id) {
        educationService.deleteEducation(id);
    }

}
