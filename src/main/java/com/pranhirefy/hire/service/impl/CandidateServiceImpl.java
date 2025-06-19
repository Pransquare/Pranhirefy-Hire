package com.pranhirefy.hire.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranhirefy.hire.dto.CandidateDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.exception.CandidateNotFoundException;
//import com.pranhirefy.hire.exception.ResourceNotFoundException;
import com.pranhirefy.hire.mapper.CandidateMapper;
import com.pranhirefy.hire.repository.CandidateRepository;
import com.pranhirefy.hire.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<CandidateDto> getAllCandidates() {
        logger.info("Fetching all non-deleted candidates");
        return candidateRepository.findByDeleted("No").stream()
                .map(CandidateMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDto getCandidateById(Integer id) {
        logger.debug("Fetching candidate by ID: {}", id);
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate", "id", id));
        return CandidateMapper.toDto(candidate);
    }

    @Override
    public CandidateDto createCandidate(CandidateDto dto) {
        Candidate candidate = CandidateMapper.toEntity(dto);
        logger.info("Creating new candidate: {}", candidate.getFirstName());
        return CandidateMapper.toDto(candidateRepository.save(candidate));
    }

//    @Override
//    public CandidateDto updateCandidate(Integer id, CandidateDto dto) {
//        Candidate candidate = candidateRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));
//        logger.info("Updating candidate with ID: {}", id);
//
//        candidate.setFirstName(dto.getFirstName());
//        candidate.setLastName(dto.getLastName());
//        candidate.setEmailAddress(dto.getEmailAddress());
//        candidate.setPanId(dto.getPanId());
//        candidate.setAadhaarNo(dto.getAadhaarNo());
//        candidate.setGender(dto.getGender());
//        candidate.setBudget(dto.getBudget());
//        candidate.setVisa(dto.getVisa());
//        candidate.setPrimaryMobileNo(dto.getPrimaryMobileNo());
//        candidate.setStatus(dto.getStatus());
//        candidate.setPriority(dto.getPriority());
//        candidate.setDob(dto.getDob());
//        candidate.setDoj(dto.getDoj());
//
//        return CandidateMapper.toDto(candidateRepository.save(candidate));
//    }
    
    @Override
    public CandidateDto updateCandidate(Integer id, CandidateDto dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate", "id", id));
        logger.info("Updating candidate with ID: {}", id);

        candidate.setFirstName(dto.getFirstName());
        candidate.setMiddleName(dto.getMiddleName());
        candidate.setLastName(dto.getLastName());
        candidate.setEmailAddress(dto.getEmailAddress());
        candidate.setPrimaryMobileNo(dto.getPrimaryMobileNo());
        candidate.setAlternateMobileNo(dto.getAlternateMobileNo());
        candidate.setEmergencyMobileNo(dto.getEmergencyMobileNo());
        candidate.setPanId(dto.getPanId());
        candidate.setAadhaarNo(dto.getAadhaarNo());
        candidate.setGender(dto.getGender());
        candidate.setVisa(dto.getVisa());
        candidate.setVisaCountry(dto.getVisaCountry());
        candidate.setVisaExpiry(dto.getVisaExpiry());
        candidate.setPassportNumber(dto.getPassportNumber());
        candidate.setDocumentName(dto.getDocumentName());
        candidate.setDocumentType(dto.getDocumentType());
        candidate.setDocumentNumber(dto.getDocumentNumber());
        candidate.setBudget(dto.getBudget());
        candidate.setBudgetComment(dto.getBudgetComment());
        candidate.setStatus(dto.getStatus());
        candidate.setWorkflowStatus(dto.getWorkflowStatus());
        candidate.setPriority(dto.getPriority());
        candidate.setPrimarySkill(dto.getPrimarySkill());
        candidate.setRequirementId(dto.getRequirementId());
        candidate.setRequirementDesc(dto.getRequirementDesc());
        candidate.setEmploymentType(dto.getEmploymentType());
        candidate.setNationality(dto.getNationality());
        candidate.setWorkLocation(dto.getWorkLocation());
        candidate.setCtcType(dto.getCtcType());
        candidate.setCreatedBy(dto.getCreatedBy());
        candidate.setModifiedBy(dto.getModifiedBy());
        candidate.setDeleted(dto.getDeleted());
        candidate.setExceptedCtc(dto.getExceptedCtc());
        candidate.setGrantedCtc(dto.getGrantedCtc());
        candidate.setManagementComment(dto.getManagementComment());
        candidate.setVendorName(dto.getVendorName());
        candidate.setExpiry(dto.getExpiry());
        candidate.setDob(dto.getDob());
        candidate.setDoj(dto.getDoj());
        candidate.setCreatedDate(dto.getCreatedDate());
        candidate.setModifiedDate(dto.getModifiedDate());

        return CandidateMapper.toDto(candidateRepository.save(candidate));
    }

    
    
    

    @Override
    public void softDeleteCandidate(Integer id) {
        logger.warn("Soft deleting candidate with ID: {}", id);
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate", "id", id));
        candidate.setDeleted("Yes");
        candidateRepository.save(candidate);
    }



}
