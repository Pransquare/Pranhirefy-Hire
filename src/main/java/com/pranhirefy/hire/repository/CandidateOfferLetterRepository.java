package com.pranhirefy.hire.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranhirefy.hire.entity.CandidateOfferLetter;

public interface CandidateOfferLetterRepository extends JpaRepository<CandidateOfferLetter, Integer> {
    List<CandidateOfferLetter> findByDeleted(String deleted);
}

