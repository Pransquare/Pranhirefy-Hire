package com.pranhirefy.hire.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "candidate_address")
public class CandidateAddress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;

    @NotBlank(message = "Line1 must not be blank")
    @Column(name = "line1")
    private String line1;

    @NotBlank(message = "City must not be blank")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Country must not be blank")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Postal code must not be null")
    @Column(name = "postal_code")
    private Integer postalCode;

    @NotBlank(message = "Type must not be blank")
    @Column(name = "type")
    private String type; // e.g., 'Current' or 'Permanent'
     
    @Column(name = "deleted")
    private String deleted = "No";

    @NotBlank(message = "CreatedBy must not be blank")
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    //@NotBlank(message = "modifiedBy must not be blank")
    private String modifiedBy;

    @Column(name = "created_date")
    @NotNull(message = "createdDate must not be blank")
    private Date createdDate;

    @Column(name = "modified_date")
    @NotNull(message = "modifiedDate must not be blank")
    private Date modifiedDate;

    @NotNull(message = "Candidate reference must not be null")
    @ManyToOne
    @JoinColumn(name = "cid", unique = true)
    private Candidate candidate;

    // Getters and Setters...


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLine1() {
		return line1;
	}


	public void setLine1(String line1) {
		this.line1 = line1;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Integer getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDeleted() {
		return deleted;
	}


	public void setDeleted(String deleted) {
		this.deleted = deleted;
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


	public CandidateAddress(Integer id, String line1, String city, String state, String country, Integer postalCode,
			String type, String deleted, String createdBy, String modifiedBy, Date createdDate, Date modifiedDate,
			Candidate candidate) {
		super();
		this.id = id;
		this.line1 = line1;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.type = type;
		this.deleted = deleted;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.candidate = candidate;
	}


	public CandidateAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
}
