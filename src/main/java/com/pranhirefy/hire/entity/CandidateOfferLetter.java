package com.pranhirefy.hire.entity;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "candidate_offer_letter")
public class CandidateOfferLetter {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;
    private String filePath;
    private String createdBy;
    private String modifiedBy;
    private String deleted = "No";

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Candidate candidate;

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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public CandidateOfferLetter(Integer id, String fileName, String filePath, String createdBy, String modifiedBy,
			String deleted, Date createdDate, Date modifiedDate, Candidate candidate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.candidate = candidate;
	}

	public CandidateOfferLetter() {
		super();
		// TODO Auto-generated constructor stub
	}
       
  
    
    
}
