package com.pranhirefy.hire.dto;



import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CandidateEmployeementDto {

	private Integer id;

    @NotBlank(message = "Employer is required")
    private String employer;

    @NotBlank(message = "Employment From is required")
    private String empFrom;

    @NotBlank(message = "Employment To is required")
    private String empTo;

    @NotBlank(message = "Notice Period is required")
    private String noticePeriod;

    @NotBlank(message = "Reason is required")
    private String reason;

    @NotBlank(message = "CTC is required")
    private String ctc;

    @NotBlank(message = "DOJ is required")
    private String doj;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "CreatedBy is required")
    private String createdBy;

    //@NotBlank(message = "ModifiedBy is required")
    private String modifiedBy;

    private String deleted;

    private Date createdDate;
    private Date modifiedDate;

    @NotNull(message = "Candidate ID is required")
    private Integer candidateId;

    // Getters and Setters
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getEmpFrom() {
		return empFrom;
	}

	public void setEmpFrom(String empFrom) {
		this.empFrom = empFrom;
	}

	public String getEmpTo() {
		return empTo;
	}

	public void setEmpTo(String empTo) {
		this.empTo = empTo;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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


}
