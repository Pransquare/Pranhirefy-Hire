package com.pranhirefy.hire.serviceImpl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranhirefy.hire.Dto.CandidateOfferLetterDTO;
//import com.pranhirefy.hire.dto.CandidateOfferLetterDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateOfferLetter;
//import com.pranhirefy.hire.exception.ResourceNotFoundException;
import com.pranhirefy.hire.exception.ResourceNotFoundExceptions;
import com.pranhirefy.hire.mapper.CandidateOfferLetterMapper;
import com.pranhirefy.hire.repository.CandidateOfferLetterRepository;
import com.pranhirefy.hire.repository.CandidateRepository;
import com.pranhirefy.hire.service.CandidateOfferLetterService;

@Service
public class CandidateOfferLetterServiceImpl implements CandidateOfferLetterService {

    @Autowired
    private CandidateOfferLetterRepository repository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<CandidateOfferLetterDTO> getAllOfferLetters() {
        return repository.findByDeleted("No").stream()
                .map(CandidateOfferLetterMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateOfferLetterDTO getOfferLetterById(Integer id) {
        CandidateOfferLetter offerLetter = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Offer Letter not found with ID " + id));
        return CandidateOfferLetterMapper.toDto(offerLetter);
    }

    @Override
    public CandidateOfferLetterDTO createOfferLetter(CandidateOfferLetterDTO dto) {
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundExceptions("Candidate not found with ID " + dto.getCandidateId()));
        dto.setCreatedDate(new Date());
        dto.setDeleted("No");
        CandidateOfferLetter offerLetter = CandidateOfferLetterMapper.toEntity(dto, candidate);
        return CandidateOfferLetterMapper.toDto(repository.save(offerLetter));
    }

    @Override
    public CandidateOfferLetterDTO updateOfferLetter(Integer id, CandidateOfferLetterDTO dto) {
        CandidateOfferLetter existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Offer Letter not found with ID " + id));
            
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundExceptions("Candidate not found"));
        
        
        existing.setFileName(dto.getFileName());
        existing.setFilePath(dto.getFilePath());
        existing.setModifiedBy(dto.getModifiedBy());
        existing.setModifiedDate(new Date());
        
     // ✅ update the foreign key reference
        existing.setCandidate(candidate);
        
        return CandidateOfferLetterMapper.toDto(repository.save(existing));
    }

    @Override
    public void deleteOfferLetter(Integer id) {
        CandidateOfferLetter offerLetter = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Offer Letter not found with ID " + id));
        offerLetter.setDeleted("Yes");
        repository.save(offerLetter);
    }
}
