package com.pranhirefy.hire.service;



import java.util.List;

import com.pranhirefy.hire.Dto.CandidateOfferLetterDTO;

//import com.pranhirefy.hire.dto.CandidateOfferLetterDto;

public interface CandidateOfferLetterService {
    List<CandidateOfferLetterDTO> getAllOfferLetters();
    CandidateOfferLetterDTO getOfferLetterById(Integer id);
    CandidateOfferLetterDTO createOfferLetter(CandidateOfferLetterDTO dto);
    CandidateOfferLetterDTO updateOfferLetter(Integer id, CandidateOfferLetterDTO dto);
    void deleteOfferLetter(Integer id);
}
