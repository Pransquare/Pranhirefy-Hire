package com.pranhirefy.hire.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranhirefy.hire.Dto.CandidateEmployeementDto;
import com.pranhirefy.hire.entity.Candidate;
import com.pranhirefy.hire.entity.CandidateEmployment;
import com.pranhirefy.hire.exception.CandidateEmployeementDuplicateCandidateEmploymentException;
import com.pranhirefy.hire.exception.CandidateEmployeementValidationException;
//import com.pranhirefy.hire.exception.DuplicateCandidateEmploymentException;
import com.pranhirefy.hire.exception.ResourcesNotFoundException;
//import com.pranhirefy.hire.exception.ValidationException;
import com.pranhirefy.hire.mapper.CandidateEmploymentMapper;
import com.pranhirefy.hire.repository.CandidateEmploymentRepository;
import com.pranhirefy.hire.repository.CandidateRepository;
import com.pranhirefy.hire.service.CandidateEmploymentService;

@Service
public class CandidateEmploymentServiceImpl implements CandidateEmploymentService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateEmploymentServiceImpl.class);

    @Autowired
    private CandidateEmploymentRepository repository;

    @Autowired
    private CandidateRepository candidateRepository;

    private void validateDto(CandidateEmployeementDto dto) {
        StringBuilder errors = new StringBuilder();

        if (dto.getEmployer() == null || dto.getEmployer().trim().isEmpty())
            errors.append("Employer is required; ");

        if (dto.getEmpFrom() == null || dto.getEmpFrom().trim().isEmpty())
            errors.append("Employment From is required; ");

        if (dto.getEmpTo() == null || dto.getEmpTo().trim().isEmpty())
            errors.append("Employment To is required; ");

        if (dto.getNoticePeriod() == null || dto.getNoticePeriod().trim().isEmpty())
            errors.append("Notice Period is required; ");

        if (dto.getReason() == null || dto.getReason().trim().isEmpty())
            errors.append("Reason is required; ");

        if (dto.getCtc() == null || dto.getCtc().trim().isEmpty())
            errors.append("CTC is required; ");

        if (dto.getDoj() == null || dto.getDoj().trim().isEmpty())
            errors.append("DOJ is required; ");

        if (dto.getAddress() == null || dto.getAddress().trim().isEmpty())
            errors.append("Address is required; ");

        if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty())
            errors.append("CreatedBy is required; ");

        if (dto.getCandidateId() == null)
            errors.append("Candidate ID is required; ");

        if (errors.length() > 0) {
            logger.error("Validation errors: {}", errors.toString());
            throw new CandidateEmployeementValidationException(errors.toString());
        }
    }

    private void checkDuplicateCandidateEmployment(Integer candidateId) {
        boolean exists = repository.existsByCandidateIdAndDeleted(candidateId, "No");
        if (exists) {
            String message = "Employment record already exists for candidate id: " + candidateId;
            logger.error(message);
            throw new CandidateEmployeementDuplicateCandidateEmploymentException(message);
        }
    }

    @Override
    public CandidateEmployeementDto createCandidateEmployment(CandidateEmployeementDto dto) {
        logger.info("Creating Candidate Employment for candidateId: {}", dto.getCandidateId());

        validateDto(dto);

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> {
                    String msg = "Candidate not found with id: " + dto.getCandidateId();
                    logger.error(msg);
                    return new ResourcesNotFoundException(msg);
                });

        // Check unique candidate employment before create
        checkDuplicateCandidateEmployment(dto.getCandidateId());

        CandidateEmployment entity = CandidateEmploymentMapper.toEntity(dto, candidate);

        CandidateEmployment saved = repository.save(entity);
        logger.info("Candidate Employment created with id: {}", saved.getId());
        return CandidateEmploymentMapper.toDto(saved);
    }

    @Override
    public List<CandidateEmployeementDto> getAllCandidateEmployments() {
        logger.info("Fetching all Candidate Employments");
        return repository.findByDeleted("No").stream()
                .map(CandidateEmploymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateEmployeementDto getCandidateEmploymentById(Integer id) {
        logger.info("Fetching Candidate Employment with id: {}", id);
        CandidateEmployment entity = repository.findById(id)
                .orElseThrow(() -> {
                    String msg = "Employment not found with id: " + id;
                    logger.error(msg);
                    return new ResourcesNotFoundException(msg);
                });
        return CandidateEmploymentMapper.toDto(entity);
    }

    @Override
    public CandidateEmployeementDto updateCandidateEmployment(Integer id, CandidateEmployeementDto dto) {
        logger.info("Updating Candidate Employment with id: {}", id);

        validateDto(dto);

        CandidateEmployment entity = repository.findById(id)
                .orElseThrow(() -> {
                    String msg = "Employment not found with id: " + id;
                    logger.error(msg);
                    return new ResourcesNotFoundException(msg);
                });

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> {
                    String msg = "Candidate not found with id: " + dto.getCandidateId();
                    logger.error(msg);
                    return new ResourcesNotFoundException(msg);
                });

        // Check duplicate candidate id only if candidate changed in update
        if (!entity.getCandidate().getId().equals(dto.getCandidateId())) {
            checkDuplicateCandidateEmployment(dto.getCandidateId());
        }

        entity.setEmployer(dto.getEmployer());
        entity.setEmpFrom(dto.getEmpFrom());
        entity.setEmpTo(dto.getEmpTo());
        entity.setNoticePeriod(dto.getNoticePeriod());
        entity.setReason(dto.getReason());
        entity.setCtc(dto.getCtc());
        entity.setDoj(dto.getDoj());
        entity.setAddress(dto.getAddress());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCandidate(candidate);

        CandidateEmployment updated = repository.save(entity);
        logger.info("Candidate Employment updated with id: {}", updated.getId());
        return CandidateEmploymentMapper.toDto(updated);
    }

    @Override
    public void deleteCandidateEmployment(Integer id) {
        logger.info("Deleting Candidate Employment with id: {}", id);
        CandidateEmployment entity = repository.findById(id)
                .orElseThrow(() -> {
                    String msg = "Employment not found with id: " + id;
                    logger.error(msg);
                    return new ResourcesNotFoundException(msg);
                });
        entity.setDeleted("Yes");
        repository.save(entity);
        logger.info("Candidate Employment soft-deleted with id: {}", id);
    }
}
