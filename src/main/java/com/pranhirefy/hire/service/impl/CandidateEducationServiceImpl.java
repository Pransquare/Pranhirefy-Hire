package com.pranhirefy.hire.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranhirefy.hire.dto.CandidateEducationDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateEducation;
//import com.pranhirefy.hire.exception.ResourceNotFoundException;
import com.pranhirefy.hire.exception.ResourceNotsFoundException;
import com.pranhirefy.hire.mapper.CandidateEducationMapper;
import com.pranhirefy.hire.repository.CandidateEducationRepository;
import com.pranhirefy.hire.repository.CandidateRepository;
import com.pranhirefy.hire.service.CandidateEducationService;

@Service
public class CandidateEducationServiceImpl implements CandidateEducationService {

    @Autowired
    private CandidateEducationRepository educationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<CandidateEducationDto> getAllEducations() {
        return educationRepository.findByDeleted("No")
                .stream()
                .map(CandidateEducationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateEducationDto getEducationById(Integer id) {
        CandidateEducation entity = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotsFoundException("CandidateEducation not found with id: " + id));
        return CandidateEducationMapper.toDto(entity);
    }

    @Override
    public CandidateEducationDto createEducation(CandidateEducationDto dto) {
      //  Candidate candidate = candidateRepository.findById(dto.getCandidateId())
             //   .orElseThrow(() -> new ResourceNotsFoundException("Candidate not found with id: " + dto.getCandidateId()));
    	Candidate candidate = candidateRepository.findById(dto.getCandidateId())
    	        .orElseThrow(() -> new ResourceNotsFoundException("Candidate not found with id: " + dto.getCandidateId()));

        CandidateEducation entity = CandidateEducationMapper.toEntity(dto, candidate);
        return CandidateEducationMapper.toDto(educationRepository.save(entity));
    }
    
    
    
    @Override
    public CandidateEducationDto updateEducation(Integer id, CandidateEducationDto dto) {
        CandidateEducation existing = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotsFoundException("CandidateEducation not found with id: " + id));

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new ResourceNotsFoundException("Candidate not found with id: " + dto.getCandidateId()));

        existing.setDegree(dto.getDegree());
        existing.setInstitute(dto.getInstitute());
        existing.setUniversity(dto.getUniversity());
        existing.setCompletedOn(dto.getCompletedOn());
        existing.setCountry(dto.getCountry());
        existing.setState(dto.getState());
        existing.setGradeMarks(dto.getGradeMarks());
        existing.setMode(dto.getMode());
        existing.setCreatedBy(dto.getCreatedBy());
        existing.setModifiedBy(dto.getModifiedBy());
        existing.setCreatedDate(dto.getCreatedDate());
        existing.setModifiedDate(dto.getModifiedDate());
        existing.setDeleted(dto.getDeleted());
        existing.setCandidate(candidate);

        CandidateEducation updated = educationRepository.save(existing);
        return CandidateEducationMapper.toDto(updated);
    }
    
        
    
    @Override
    public void deleteEducation(Integer id) {
        CandidateEducation entity = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotsFoundException("CandidateEducation not found with id: " + id));

        entity.setDeleted("Yes");
        educationRepository.save(entity);
    }

}
