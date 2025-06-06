package com.pranhirefy.hire.service;



import java.util.List;

import com.pranhirefy.hire.Dto.CandidateEducationDto;

//import com.pranhirefy.hire.dto.CandidateEducationDto;

public interface CandidateEducationService {
    List<CandidateEducationDto > getAllEducations();
    CandidateEducationDto getEducationById(Integer id);
    CandidateEducationDto createEducation(CandidateEducationDto dto);
    
    CandidateEducationDto updateEducation(Integer id, CandidateEducationDto dto);
      
    void deleteEducation(Integer id);

}

