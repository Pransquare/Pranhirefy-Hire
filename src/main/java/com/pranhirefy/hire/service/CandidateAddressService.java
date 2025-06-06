package com.pranhirefy.hire.service;



import java.util.List;
import com.pranhirefy.hire.Dto.CandidateAddressDto;

public interface CandidateAddressService {
    CandidateAddressDto createAddress(CandidateAddressDto dto);
    CandidateAddressDto getAddressById(Integer id);
    List<CandidateAddressDto> getAllAddresses();
    CandidateAddressDto updateAddress(Integer id, CandidateAddressDto dto);
    void deleteAddress(Integer id);
}
