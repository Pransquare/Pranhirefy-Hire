package com.pranhirefy.hire.entity;



import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "candidate_employement")
public class CandidateEmployment {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @NotBlank(message = "Employer is required")
	    @Column(name = "employer")
	    private String employer;

	    @NotBlank(message = "Employment From is required")
	    @Column(name = "emp_from")
	    private String empFrom;

	    @NotBlank(message = "Employment To is required")
	    @Column(name = "emp_to")
	    private String empTo;

	    @NotBlank(message = "Notice Period is required")
	    @Column(name = "notice_period")
	    private String noticePeriod;

	    @NotBlank(message = "Reason is required")
	    @Column(name = "reason")
	    private String reason;

	    @NotBlank(message = "CTC is required")
	    @Column(name = "ctc")
	    private String ctc;

	    @NotBlank(message = "DOJ is required")
	    @Column(name = "doj")
	    private String doj;

	    @NotBlank(message = "Address is required")
	    @Column(name = "address")
	    private String address;

	    @NotBlank(message = "CreatedBy is required")
	    @Column(name = "created_by")
	    private String createdBy;

	   // @NotBlank(message = "ModifiedBy is required")
	    @Column(name = "modified_by")
	    private String modifiedBy;

	    @Column(name = "deleted")
	    private String deleted = "No";

	    @Temporal(TemporalType.DATE)
	    @Column(name = "created_date")
	    private Date createdDate;

	    @Temporal(TemporalType.DATE)
	    @Column(name = "modified_date")
	    private Date modifiedDate;

	    @NotNull(message = "Candidate reference is required")
	    @ManyToOne
	    @JoinColumn(name = "cid",unique=true)
	    private Candidate candidate;

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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	
}
