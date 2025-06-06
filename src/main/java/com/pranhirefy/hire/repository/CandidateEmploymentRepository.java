package com.pranhirefy.hire.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranhirefy.hire.entity.CandidateEmployment;

public interface CandidateEmploymentRepository extends JpaRepository<CandidateEmployment, Integer> {
    List<CandidateEmployment> findByDeleted(String deleted);

	boolean existsByCandidateIdAndDeleted(Integer candidateId, String string);
}
