package com.pranhirefy.hire.mapper;



import com.pranhirefy.hire.dto.CandidateEducationDto;
//import com.pranhirefy.hire.dto.CandidateEducationDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateEducation;

public class CandidateEducationMapper {

    public static CandidateEducationDto toDto(CandidateEducation entity) {
        CandidateEducationDto dto = new CandidateEducationDto();
        dto.setId(entity.getId());
        dto.setDegree(entity.getDegree());
        dto.setInstitute(entity.getInstitute());
        dto.setUniversity(entity.getUniversity());
        dto.setCompletedOn(entity.getCompletedOn());
        dto.setCountry(entity.getCountry());
        dto.setState(entity.getState());
        dto.setGradeMarks(entity.getGradeMarks());
        dto.setMode(entity.getMode());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setDeleted(entity.getDeleted());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCandidateId(entity.getCandidate().getId());
        return dto;
    }

    public static CandidateEducation toEntity(CandidateEducationDto dto, Candidate candidate) {
        CandidateEducation entity = new CandidateEducation();
        entity.setId(dto.getId());
        entity.setDegree(dto.getDegree());
        entity.setInstitute(dto.getInstitute());
        entity.setUniversity(dto.getUniversity());
        entity.setCompletedOn(dto.getCompletedOn());
        entity.setCountry(dto.getCountry());
        entity.setState(dto.getState());
        entity.setGradeMarks(dto.getGradeMarks());
        entity.setMode(dto.getMode());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setDeleted(dto.getDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
       // entity.setCandidate(candidate);
        entity.setCandidate(candidate);

        return entity;
    }
}
