package com.pranhirefy.hire.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pranhirefy.hire.Dto.CandidateAddressDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateAddress;
import com.pranhirefy.hire.exception.CandidateAddressDataException;
import com.pranhirefy.hire.exception.CandidateAddressNotFoundException;
import com.pranhirefy.hire.mapper.CandidateAddressMapper;
import com.pranhirefy.hire.repository.CandidateAddressRepository;
import com.pranhirefy.hire.repository.CandidateRepository;
import com.pranhirefy.hire.service.CandidateAddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateAddressServiceImpl implements CandidateAddressService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateAddressServiceImpl.class);

    @Autowired
    private CandidateAddressRepository addressRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CandidateAddressDto createAddress(CandidateAddressDto dto) {
        logger.info("Creating address for candidate ID: {}", dto.getCandidateId());

        validateDto(dto);

        // Check if this candidate already has the same address type and not soft-deleted
        boolean typeExists = addressRepository
                .existsByCandidateIdAndTypeAndDeleted(dto.getCandidateId(), dto.getType(), "No");
        if (typeExists) {
            throw new CandidateAddressDataException("Address of type '" + dto.getType() + "' already exists for candidateId: " + dto.getCandidateId());
        }

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new CandidateAddressNotFoundException("Candidate", "id", dto.getCandidateId()));

        dto.setCreatedDate(new Date());
        dto.setDeleted("No");

        CandidateAddress address = CandidateAddressMapper.toEntity(dto, candidate);
        CandidateAddress saved = addressRepository.save(address);

        return CandidateAddressMapper.toDto(saved);
    }

    @Override
    public CandidateAddressDto getAddressById(Integer id) {
        logger.info("Fetching address by ID: {}", id);
        CandidateAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new CandidateAddressNotFoundException("CandidateAddress", "id", id));
        return CandidateAddressMapper.toDto(address);
    }

    @Override
    public List<CandidateAddressDto> getAllAddresses() {
        logger.info("Fetching all addresses");
        return addressRepository.findByDeleted("No")
                .stream()
                .map(CandidateAddressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateAddressDto updateAddress(Integer id, CandidateAddressDto dto) {
        logger.info("Updating address with ID: {}", id);

        validateDto(dto);

        CandidateAddress existing = addressRepository.findById(id)
                .orElseThrow(() -> new CandidateAddressNotFoundException("CandidateAddress", "id", id));

        // If updating to a different candidate or address type already exists
        if (!existing.getCandidate().getId().equals(dto.getCandidateId()) || !existing.getType().equals(dto.getType())) {
            boolean duplicateType = addressRepository.existsByCandidateIdAndTypeAndDeleted(dto.getCandidateId(), dto.getType(), "No");
            if (duplicateType) {
                throw new CandidateAddressDataException("Another address of type '" + dto.getType() + "' already exists for candidateId: " + dto.getCandidateId());
            }
        }

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new CandidateAddressNotFoundException("Candidate", "id", dto.getCandidateId()));

        existing.setLine1(dto.getLine1());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setCountry(dto.getCountry());
        existing.setPostalCode(dto.getPostalCode());
        existing.setType(dto.getType());
        existing.setModifiedBy(dto.getModifiedBy());
        existing.setCreatedBy(dto.getCreatedBy());
        existing.setModifiedDate(new Date());
        existing.setCandidate(candidate);
        existing.setDeleted(dto.getDeleted());

        CandidateAddress updated = addressRepository.save(existing);
        return CandidateAddressMapper.toDto(updated);
    }

    @Override
    public void deleteAddress(Integer id) {
        logger.info("Soft deleting address with ID: {}", id);
        CandidateAddress existing = addressRepository.findById(id)
                .orElseThrow(() -> new CandidateAddressNotFoundException("CandidateAddress", "id", id));
        existing.setDeleted("Yes");
        addressRepository.save(existing);
    }

    private void validateDto(CandidateAddressDto dto) {
        StringBuilder errors = new StringBuilder();

        if (dto.getCandidateId() == null)
            errors.append("Candidate ID must not be null. ");
        if (dto.getLine1() == null || dto.getLine1().trim().isEmpty())
            errors.append("Line1 must not be blank. ");
        if (dto.getCity() == null || dto.getCity().trim().isEmpty())
            errors.append("City must not be blank. ");
        if (dto.getState() == null || dto.getState().trim().isEmpty())
            errors.append("State must not be blank. ");
        if (dto.getCountry() == null || dto.getCountry().trim().isEmpty())
            errors.append("Country must not be blank. ");
        if (dto.getPostalCode() == null)
            errors.append("Postal Code must not be null. ");
        if (dto.getType() == null || dto.getType().trim().isEmpty())
            errors.append("Type must not be blank. ");
        if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty())
            errors.append("CreatedBy must not be blank. ");

        if (errors.length() > 0) {
            throw new CandidateAddressDataException(errors.toString().trim());
        }
    }
}
