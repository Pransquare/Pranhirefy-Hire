package com.pranhirefy.hire.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidate_education")
public class CandidateEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String degree;
    private String institute;
    private String university;
    private String completedOn;
    private String country;
    private String state;
    private String gradeMarks;
    private String mode;
    private String createdBy;
    private String modifiedBy;
    private String deleted = "No";

    @Temporal(TemporalType.DATE)
    private Date createdDate;
    

    @Temporal(TemporalType.DATE)
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGradeMarks() {
		return gradeMarks;
	}

	public void setGradeMarks(String gradeMarks) {
		this.gradeMarks = gradeMarks;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public CandidateEducation(Integer id, String degree, String institute, String university, String completedOn,
			String country, String state, String gradeMarks, String mode, String createdBy, String modifiedBy,
			String deleted, Date createdDate, Date modifiedDate, Candidate candidate) {
		super();
		this.id = id;
		this.degree = degree;
		this.institute = institute;
		this.university = university;
		this.completedOn = completedOn;
		this.country = country;
		this.state = state;
		this.gradeMarks = gradeMarks;
		this.mode = mode;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.candidate = candidate;
	}

	public CandidateEducation() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
    
    

   
}
