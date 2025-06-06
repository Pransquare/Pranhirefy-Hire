package com.pranhirefy.hire.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pranhirefy.hire.entity.CandidateAddress;

public interface CandidateAddressRepository extends JpaRepository<CandidateAddress, Integer> {
    List<CandidateAddress> findByDeleted(String deleted);
   // boolean existsByCandidateIdAndDeleted(Integer candidateId, String deleted);
    
    boolean existsByCandidateIdAndTypeAndDeleted(Integer candidateId, String type, String deleted);

}
