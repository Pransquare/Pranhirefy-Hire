package com.pranhirefy.hire.mapper;

import com.pranhirefy.hire.dto.CandidateDto;
import com.pranhirefy.hire.entity.Candidate;


public class CandidateMapper {
public static CandidateDto toDto(Candidate candidate) {
    CandidateDto dto = new CandidateDto();
    dto.setId(candidate.getId());
    dto.setFirstName(candidate.getFirstName());
    dto.setMiddleName(candidate.getMiddleName());
    dto.setLastName(candidate.getLastName());
    dto.setEmailAddress(candidate.getEmailAddress());
    dto.setPrimaryMobileNo(candidate.getPrimaryMobileNo());
    dto.setAlternateMobileNo(candidate.getAlternateMobileNo());
    dto.setEmergencyMobileNo(candidate.getEmergencyMobileNo());
    dto.setPanId(candidate.getPanId());
    dto.setAadhaarNo(candidate.getAadhaarNo());
    dto.setGender(candidate.getGender());
    dto.setVisa(candidate.getVisa());
    dto.setVisaCountry(candidate.getVisaCountry());
    dto.setVisaExpiry(candidate.getVisaExpiry());
    dto.setPassportNumber(candidate.getPassportNumber());
    dto.setDocumentName(candidate.getDocumentName());
    dto.setDocumentType(candidate.getDocumentType());
    dto.setDocumentNumber(candidate.getDocumentNumber());
    dto.setBudget(candidate.getBudget());
    dto.setBudgetComment(candidate.getBudgetComment());
    dto.setStatus(candidate.getStatus());
    dto.setWorkflowStatus(candidate.getWorkflowStatus());
    dto.setPriority(candidate.getPriority());
    dto.setPrimarySkill(candidate.getPrimarySkill());
    dto.setRequirementId(candidate.getRequirementId());
    dto.setRequirementDesc(candidate.getRequirementDesc());
    dto.setEmploymentType(candidate.getEmploymentType());
    dto.setNationality(candidate.getNationality());
    dto.setWorkLocation(candidate.getWorkLocation());
    dto.setCtcType(candidate.getCtcType());
    dto.setCreatedBy(candidate.getCreatedBy());
    dto.setModifiedBy(candidate.getModifiedBy());
    dto.setDeleted(candidate.getDeleted());
    dto.setExceptedCtc(candidate.getExceptedCtc());
    dto.setGrantedCtc(candidate.getGrantedCtc());
    dto.setManagementComment(candidate.getManagementComment());
    dto.setVendorName(candidate.getVendorName());
    dto.setExpiry(candidate.getExpiry());
    dto.setDob(candidate.getDob());
    dto.setDoj(candidate.getDoj());
    dto.setCreatedDate(candidate.getCreatedDate());
    dto.setModifiedDate(candidate.getModifiedDate());
    
    return dto;
}

public static Candidate toEntity(CandidateDto dto) {
    Candidate candidate = new Candidate();
    candidate.setId(dto.getId());
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
    candidate.setMiddleName(dto.getMiddleName());
    candidate.setMiddleName(dto.getMiddleName());

    return candidate;
}
}
