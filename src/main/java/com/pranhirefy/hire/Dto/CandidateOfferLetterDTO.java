package com.pranhirefy.hire.Dto;


import java.util.Date;

public class CandidateOfferLetterDTO {
    private Integer id;
    private String fileName;
    private String filePath;
    private String createdBy;
    private String modifiedBy;
    private String deleted;
    private Date createdDate;
    private Date modifiedDate; 
    private Integer candidateId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	public CandidateOfferLetterDTO(Integer id, String fileName, String filePath, String createdBy, String modifiedBy,
			String deleted, Date createdDate, Date modifiedDate, Integer candidateId) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.candidateId = candidateId;
	}
	public CandidateOfferLetterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

    
}
