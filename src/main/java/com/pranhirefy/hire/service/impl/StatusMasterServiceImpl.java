
package com.pranhirefy.hire.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import com.pranhirefy.hire.dto.StatusMasterDto;
import com.pranhirefy.hire.entity.StatusMasterEntity;
import com.pranhirefy.hire.exception.StatusMasterException;
import com.pranhirefy.hire.exception.StatusMasterValidationException;
import com.pranhirefy.hire.mapper.StatusMasterMapper;
import com.pranhirefy.hire.repository.StatusMasterRepository;
import com.pranhirefy.hire.service.StatusMasterService;

import io.micrometer.common.util.StringUtils;

@Service
public class StatusMasterServiceImpl implements StatusMasterService {

	private static final Logger logger = LoggerFactory.getLogger(StatusMasterServiceImpl.class);

	private final StatusMasterRepository statusmasterrepository;

	public StatusMasterServiceImpl(StatusMasterRepository statusmasterrepository) {
		this.statusmasterrepository = statusmasterrepository;
		logger.info("StatusMasterServiceImpl initialized");
	}


	@Override
	public StatusMasterDto createStatusMaster(StatusMasterDto dto) {
	    logger.info("Creating StatusMaster with data: {}", dto);
	    
	    validateDtoForCreate(dto);

	    // Null check
	    if (dto == null) {
	        throw new StatusMasterException("Request body is null or empty.");
	    }

	    
	    
	    StringBuilder errorMessage = new StringBuilder();

	    if (dto.getModifiedDate() != null) {
	        errorMessage.append("Field modifiedDate should be derived automatically from 'createdDate' during creation"
	        		);
	        
	    }

	    if (dto.getModifiedBy() != null && !dto.getModifiedBy().trim().isEmpty()) {
	        errorMessage.append("Field modifiedBy should be derived automatically from 'createdBy' during creation.");
	    }

	    if (errorMessage.length() > 0) {
	        throw new StatusMasterException(errorMessage.toString().trim());
	    }

	    // Perform custom field-level validation
	    validateDtoForCreate(dto);

	    // Check for duplicate statusMasterCode
	    if (statusmasterrepository.existsByStatusMasterCode(dto.getStatusMasterCode())) {
	        throw new StatusMasterException("StatusMaster with code " + dto.getStatusMasterCode() + " already exists");
	    }

	    // Generate and assign timestamps
	    LocalDate now = LocalDate.now();
	    dto.setCreatedDate(now);
	    dto.setModifiedDate(now);

	    // Copy createdBy as modifiedBy
	    dto.setModifiedBy(dto.getCreatedBy());

	    // Save
	    StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(dto);
	    StatusMasterEntity saved = statusmasterrepository.save(entity);
	    logger.info("StatusMaster saved with ID: {}", saved.getId());

	    return StatusMasterMapper.mapToStatusMasterDto(saved);
	}

	
	@Override
	public StatusMasterDto updateStatusMaster(Long id, StatusMasterDto dto) {
	    logger.info("Updating StatusMaster with ID: {} and data: {}", id, dto);

	    if (id == null || id <= 0) {
	        throw new StatusMasterException("Invalid StatusMaster ID");
	    }

	    validateDtoForUpdate(dto, id);


	    StatusMasterEntity existing = statusmasterrepository.findById(id)
	            .orElseThrow(() -> new StatusMasterException("StatusMaster not found with ID: " + id));

	    // Prevent changing of createdBy and createdDate
	    if (dto.getCreatedBy() != null && !dto.getCreatedBy().equals(existing.getCreatedBy())) {
	        throw new StatusMasterException("Field createdBy cannot be changed during update.");
	    }

	    if (dto.getCreatedDate() != null && !dto.getCreatedDate().equals(existing.getCreatedDate())) {
	        throw new StatusMasterException("Field createdDate cannot be changed during update.");
	    }
	    if (dto.getModifiedDate() != null&& !dto.getModifiedDate().equals(existing.getModifiedDate())) {
	        throw new StatusMasterException("Field modifiedDate should be generated automatically and must not be changed manually.");
	    }

	    existing.setStatusMasterCode(dto.getStatusMasterCode());
	    existing.setModifiedBy(dto.getModifiedBy()); // ModifiedBy should be new value from user
	    existing.setModifiedDate(LocalDate.now());  // Auto-update modified date
	    existing.setStatusMasterDescription(dto.getStatusMasterDescription());

	    StatusMasterEntity updated = statusmasterrepository.save(existing);
	    logger.info("Updated StatusMaster with ID: {}", updated.getId());

	    return StatusMasterMapper.mapToStatusMasterDto(updated);
	}

	
	
	@Override
	public StatusMasterDto getStatusMasterById(Long id) {
	    logger.info("Fetching StatusMaster with ID: {}", id);

	    if (id == null || id <= 0) {
	        throw new StatusMasterException("Invalid StatusMaster ID");
	    }

	    // Only fetch if not deleted
	    StatusMasterEntity entity = statusmasterrepository.findByIdAndIsDeleted(id, "No")
	            .orElseThrow(() -> new StatusMasterException("StatusMaster not found with ID: " + id));

	    return StatusMasterMapper.mapToStatusMasterDto(entity);
	}
	
	
	@Override
	public List<StatusMasterDto> getAllMasters() {
	    logger.info("Fetching all StatusMasters");

	    List<StatusMasterEntity> entities = statusmasterrepository.findAllByIsDeleted("No");

	    if (entities.isEmpty()) {
	        throw new StatusMasterException("No StatusMasters available");
	    }

	    // ✅ Sort by ID in descending order
	    entities.sort(Comparator.comparing(StatusMasterEntity::getId).reversed());

	    return entities.stream()
	                   .map(StatusMasterMapper::mapToStatusMasterDto)
	                   .toList();
	}


	@Override
	public void deleteMaster(Long id) {
	    StatusMasterEntity entity = statusmasterrepository.findByIdAndIsDeleted(id, "No")
	        .orElseThrow(() -> new StatusMasterException("StatusMaster not found or already deleted with ID: " + id));

	    entity.setIsDeleted("Yes");
	    statusmasterrepository.save(entity);

	    logger.info("Soft-deleted StatusMaster with ID: {}", id);
	}

	private void validateDtoForCreate(StatusMasterDto dto) {
	    Map<String, String> errors = new HashMap<>();

	    if (dto == null) {
	        errors.put("body", "Request body is null or empty.");
	        throw new StatusMasterValidationException("Validation failed", errors);
	    }
	    
	 // Validate statusMasterCode
	    if (dto.getStatusMasterCode() == null) {
	        errors.put("statusMasterCode", "Field statusMasterCode must be an integer.");
	    }

	    if (dto.getStatusMasterCode() == null) {
	        errors.put("statusMasterCode", "Field statusMasterCode should not be null or empty.");
	    }

	    if (dto.getStatusMasterCode() != null &&
	    	    !statusmasterrepository.findByStatusMasterCode(dto.getStatusMasterCode()).isEmpty()) {
	    	    errors.put("statusMasterCode", "StatusMasterCode '" + dto.getStatusMasterCode() + "' already exists.");
	    	}


	    if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty()) {
	        errors.put("createdBy", "Field createdBy should not be null or empty.");
	    } else if (!dto.getCreatedBy().matches("^[A-Za-z ]+$")) {
	        errors.put("createdBy", "Field createdBy must contain only letters and spaces.");
	    }

	    if (dto.getStatusMasterDescription() == null || dto.getStatusMasterDescription().trim().isEmpty()) {
	        errors.put("statusMasterDescription", "Field statusMasterDescription should not be null or empty.");
	    } else {
	        String desc = dto.getStatusMasterDescription().trim();
	        if (desc.matches("^\\d+$")) {
	            errors.put("statusMasterDescription", "Field statusMasterDescription must not be numbers only.");
	        }
	        if (!desc.matches("^[a-zA-Z0-9\\s.,-]+$")) {
	            errors.put("statusMasterDescription", "Field statusMasterDescription contains invalid characters.");
	        }
	    }

	    if (!errors.isEmpty()) {
	        throw new StatusMasterValidationException("Validation failed", errors);
	    }
	}

	
	
	private void validateDtoForUpdate(StatusMasterDto dto, Long id) {
	    Map<String, String> errors = new HashMap<>();

	    if (dto == null) {
	        throw new StatusMasterException("Request body is null or empty.");
	    }

	    if (dto.getStatusMasterCode() == null) {
	        errors.put("statusMasterCode", "Field statusMasterCode should not be null or empty.");
	    } else {
	        List<StatusMasterEntity> existingList = statusmasterrepository.findByStatusMasterCode(dto.getStatusMasterCode());

	        for (StatusMasterEntity entity : existingList) {
	            if (!Long.valueOf(entity.getId()).equals(id)) {
	                errors.put("statusMasterCode", "StatusMasterCode '" + dto.getStatusMasterCode() + "' already exists.");
	                break;
	            }
	        }
	    }

	    if (dto.getModifiedBy() == null || dto.getModifiedBy().trim().isEmpty()) {
	        errors.put("modifiedBy", "Field modifiedBy should not be null or empty.");
	    } else if (dto.getModifiedBy().matches("^\\d+$")) {
	        errors.put("modifiedBy", "Field modifiedBy must be a valid string, not a number.");
	    }

	    if (dto.getStatusMasterDescription() == null || dto.getStatusMasterDescription().trim().isEmpty()) {
	        errors.put("statusMasterDescription", "Field statusMasterDescription should not be null or empty.");
	    } else {
	        String description = dto.getStatusMasterDescription().trim();
	        if (description.matches("^\\d+$")) {
	            errors.put("statusMasterDescription", "Field statusMasterDescription must not be numbers only.");
	        } else if (!description.matches("^[a-zA-Z0-9\\s.,-]+$")) {
	            errors.put("statusMasterDescription", "Field statusMasterDescription contains invalid characters.");
	        }
	    }

	    if (!errors.isEmpty()) {
	        throw new StatusMasterValidationException("Validation failed", errors);
	    }
	}


	
	@Override
	public List<StatusMasterDto> searchStatusMasters(String statusMasterDescription) {
	    logger.info("Searching StatusMasters with description containing: {}", statusMasterDescription);

	    List<StatusMasterEntity> entities =
	        statusmasterrepository.findByStatusMasterDescriptionContainingIgnoreCaseAndIsDeleted(
	            statusMasterDescription, "No"
	        );

	    if (entities.isEmpty()) {
	        logger.warn("No StatusMasters found matching description: {}", statusMasterDescription);
	        throw new StatusMasterException("No StatusMasters found with statusMasterDescription matching: " + statusMasterDescription);
	    }

	    return entities.stream()
	                   .map(StatusMasterMapper::mapToStatusMasterDto)
	                   .toList();
	}


}





