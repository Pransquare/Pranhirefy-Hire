package com.pranhirefy.hire.service;



import java.util.List;

import com.pranhirefy.hire.dto.CandidateDto;



public interface CandidateService {
    List<CandidateDto> getAllCandidates();
    CandidateDto getCandidateById(Integer id);
    CandidateDto createCandidate(CandidateDto dto);
    CandidateDto updateCandidate(Integer id, CandidateDto dto);
    void softDeleteCandidate(Integer id);
	//CandidateDto updateCandidate(Integer oldId, Integer newId);
}

