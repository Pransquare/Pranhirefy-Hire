package com.pranhirefy.hire.mapper;



import com.pranhirefy.hire.Dto.CandidateEmployeementDto;
//import com.pranhirefy.hire.dto.CandidateEmploymentDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateEmployment;

public class CandidateEmploymentMapper {

    public static CandidateEmployeementDto toDto(CandidateEmployment entity) {
        CandidateEmployeementDto dto = new CandidateEmployeementDto();
        dto.setId(entity.getId());
        dto.setEmployer(entity.getEmployer());
        dto.setEmpFrom(entity.getEmpFrom());
        dto.setEmpTo(entity.getEmpTo());
        dto.setNoticePeriod(entity.getNoticePeriod());
        dto.setReason(entity.getReason());
        dto.setCtc(entity.getCtc());
        dto.setDoj(entity.getDoj());
        dto.setAddress(entity.getAddress());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setDeleted(entity.getDeleted());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCandidateId(entity.getCandidate() != null ? entity.getCandidate().getId() : null);
        return dto;
    }

    public static CandidateEmployment toEntity(CandidateEmployeementDto dto, Candidate candidate) {
        CandidateEmployment entity = new CandidateEmployment();
        entity.setId(dto.getId());
        entity.setEmployer(dto.getEmployer());
        entity.setEmpFrom(dto.getEmpFrom());
        entity.setEmpTo(dto.getEmpTo());
        entity.setNoticePeriod(dto.getNoticePeriod());
        entity.setReason(dto.getReason());
        entity.setCtc(dto.getCtc());
        entity.setDoj(dto.getDoj());
        entity.setAddress(dto.getAddress());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setDeleted(dto.getDeleted() != null ? dto.getDeleted() : "No");
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCandidate(candidate);
        return entity;
    }
}
