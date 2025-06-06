package com.pranhirefy.hire.mapper;



import com.pranhirefy.hire.Dto.CandidateOfferLetterDTO;
//import com.pranhirefy.hire.dto.CandidateOfferLetterDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateOfferLetter;

public class CandidateOfferLetterMapper {

    public static CandidateOfferLetterDTO toDto(CandidateOfferLetter entity) {
        CandidateOfferLetterDTO dto = new CandidateOfferLetterDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setFilePath(entity.getFilePath());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setDeleted(entity.getDeleted());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCandidateId(entity.getCandidate() != null ? entity.getCandidate().getId() : null);
        return dto;
    }

    public static CandidateOfferLetter toEntity(CandidateOfferLetterDTO dto, Candidate candidate) {
        CandidateOfferLetter entity = new CandidateOfferLetter();
        entity.setId(dto.getId());
        entity.setFileName(dto.getFileName());
        entity.setFilePath(dto.getFilePath());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setDeleted(dto.getDeleted());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCandidate(candidate);
        return entity;
    }
}
