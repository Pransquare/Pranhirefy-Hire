package com.pranhirefy.hire.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranhirefy.hire.entity.CandidateEducation;

public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Integer> {
    List<CandidateEducation> findByDeleted(String deleted);
}
