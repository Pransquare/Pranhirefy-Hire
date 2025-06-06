package com.pranhirefy.hire.service;



import java.util.List;

import com.pranhirefy.hire.Dto.CandidateEmployeementDto;

//import com.pranhirefy.hire.dto.CandidateEmploymentDto;

public interface CandidateEmploymentService {

    CandidateEmployeementDto createCandidateEmployment(CandidateEmployeementDto dto);
    List<CandidateEmployeementDto> getAllCandidateEmployments();
    CandidateEmployeementDto getCandidateEmploymentById(Integer id);
    CandidateEmployeementDto updateCandidateEmployment(Integer id, CandidateEmployeementDto dto);
    void deleteCandidateEmployment(Integer id);
}
