





package com.pranhirefy.hire.mapper;


import com.pranhirefy.hire.dto.StatusMasterDto;
import com.pranhirefy.hire.entity.StatusMasterEntity;

public class StatusMasterMapper {

    public static StatusMasterDto mapToStatusMasterDto(StatusMasterEntity entity) {
        return new StatusMasterDto(
            entity.getId(),
            entity.getStatusMasterCode(),
            entity.getCreatedDate(),
            entity.getModifiedDate(),
            entity.getCreatedBy(),
            entity.getModifiedBy(),
            entity.getStatusMasterDescription(),
            entity.getIsDeleted()
        );
    }

    public static StatusMasterEntity mapToStatusMasterEntity(StatusMasterDto dto) {
        return new StatusMasterEntity(
            dto.getId(),
            dto.getStatusMasterCode(),
            dto.getCreatedDate(),
            dto.getModifiedDate(),
            dto.getCreatedBy(),
            dto.getModifiedBy(),
            dto.getStatusMasterDescription(),
            dto.getIsDeleted()
        );
    }
}

