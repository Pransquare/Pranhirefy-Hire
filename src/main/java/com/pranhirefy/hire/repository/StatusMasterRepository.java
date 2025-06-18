//package com.pranhirefy.hire.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.pranhirefy.hire.entity.StatusMasterEntity;
//
//
//
//public interface StatusMasterRepository extends JpaRepository<StatusMasterEntity, Long>{
//
//}

package com.pranhirefy.hire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pranhirefy.hire.entity.StatusMasterEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pranhirefy.hire.entity.StatusMasterEntity;

public interface StatusMasterRepository extends JpaRepository<StatusMasterEntity, Long> {

    // ✅ Add this method to check if code already exists

    boolean existsByStatusMasterCode(Integer statusMasterCode); // ✅ CORRECT
    
 
        
        // Search by partial match, case-insensitive
        List<StatusMasterEntity> findByStatusMasterDescriptionContainingIgnoreCase(String statusMasterDescription);
        
        List<StatusMasterEntity> findAllByIsDeleted(String isDeleted);

        Optional<StatusMasterEntity> findByIdAndIsDeleted(Long id, String isDeleted);

        boolean existsByStatusMasterCodeAndIsDeleted(Integer statusMasterCode, String isDeleted);


        List<StatusMasterEntity> findByStatusMasterDescriptionContainingIgnoreCaseAndIsDeleted(String description, String isDeleted);
        
        List<StatusMasterEntity> findByStatusMasterCode(Integer code);




    }



