package com.pranhirefy.hire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pranhirefy.hire.dto.StatusMasterDto;




@Service
public interface StatusMasterService {
	
	
	StatusMasterDto createStatusMaster(StatusMasterDto statusmasterdto);        //Add MasterDto 
	
	StatusMasterDto getStatusMasterById(Long StatusMasterId);            //Get MasterId
	
	List<StatusMasterDto> getAllMasters();                // Get All Master IDS

	StatusMasterDto updateStatusMaster(Long id, StatusMasterDto dto);        //Update
	
	void deleteMaster (Long MasterId);                     //Delete
	
	
	List<StatusMasterDto> searchStatusMasters( String statusMasterDescription);     //Search Api


}
