//package com.pranhirefy.hire.dto;
//
//import java.time.LocalDate;
//
//public class StatusMasterDto {
//	
//	private long id;
//
//	private Integer code;
//
//	private LocalDate createdDate;
//
//	private LocalDate modifiedDate;
//
//	private String createdBy;
//
//	private String modifiedBy;
//
//	private String description;
//	
//	
//	
//
//	//Getters and Setters
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Integer getCode() {
//		return code;
//	}
//
//	public void setCode(Integer code) {
//		this.code = code;
//	}
//
//	public LocalDate getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(LocalDate createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public LocalDate getModifiedDate() {
//		return modifiedDate;
//	}
//
//	public void setModifiedDate(LocalDate modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	
//	
//	//Empty Constructor
//	public StatusMasterDto()
//	{
//		
//	}
//	
//	
//	
//	
//	
//
//	//Using Fields Constructors
//	public StatusMasterDto(long id, Integer code, LocalDate createdDate, LocalDate modifiedDate, String createdBy,
//			String modifiedBy, String description) {
//		super();
//		this.id = id;
//		this.code = code;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
//		this.createdBy = createdBy;
//		this.modifiedBy = modifiedBy;
//		this.description = description;
//	}
//	
//	
//
//}


package com.pranhirefy.hire.dto;

import java.time.LocalDate;

public class StatusMasterDto {

    private long id;

    private Integer statusMasterCode;

    private LocalDate createdDate;

    private LocalDate modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private String statusMasterDescription;
    
    private String isDeleted = "No"; // Default to "No"
    
    

    // --- Getters and Setters ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStatusMasterCode() {
        return statusMasterCode;
    }

    public void setStatusMasterCode(Integer statusMasterCode) {
        this.statusMasterCode = statusMasterCode;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStatusMasterDescription() {
        return statusMasterDescription;
    }

    public void setStatusMasterDescription(String statusMasterDescription) {
        this.statusMasterDescription = statusMasterDescription;
        
    }
    
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    // --- Empty Constructor ---
    public StatusMasterDto() {
    }

    // --- Constructor Using Fields ---
    public StatusMasterDto(long id, Integer statusMasterCode, LocalDate createdDate, LocalDate modifiedDate,
                           String createdBy, String modifiedBy, String statusMasterDescription,String isDeleted) {
        this.id = id;
        this.statusMasterCode = statusMasterCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.statusMasterDescription = statusMasterDescription;
        this.isDeleted = isDeleted;
    }
}
