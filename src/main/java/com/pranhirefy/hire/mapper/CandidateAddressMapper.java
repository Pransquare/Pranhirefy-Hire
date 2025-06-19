package com.pranhirefy.hire.mapper;



import com.pranhirefy.hire.dto.CandidateAddressDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateAddress;

public class CandidateAddressMapper {

    public static CandidateAddressDto toDto(CandidateAddress address) {
        CandidateAddressDto dto = new CandidateAddressDto();
        dto.setId(address.getId());
        dto.setLine1(address.getLine1());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setPostalCode(address.getPostalCode());
        dto.setType(address.getType());
        dto.setDeleted(address.getDeleted());
        dto.setCreatedBy(address.getCreatedBy());
        dto.setModifiedBy(address.getModifiedBy());
        dto.setCreatedDate(address.getCreatedDate());
        dto.setModifiedDate(address.getModifiedDate());
        dto.setCandidateId(address.getCandidate() != null ? address.getCandidate().getId() : null);
        return dto;
    }

    public static CandidateAddress toEntity(CandidateAddressDto dto, Candidate candidate) {
        CandidateAddress address = new CandidateAddress();
        address.setId(dto.getId());
        address.setLine1(dto.getLine1());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setPostalCode(dto.getPostalCode());
        address.setType(dto.getType());
        address.setDeleted(dto.getDeleted());
        address.setCreatedBy(dto.getCreatedBy());
        address.setModifiedBy(dto.getModifiedBy());
        address.setCreatedDate(dto.getCreatedDate());
        address.setModifiedDate(dto.getModifiedDate());
        //address.setCandidate(candidate);
        address.setCandidate(candidate); // This links the FK correctly
        return address;
    }
}
