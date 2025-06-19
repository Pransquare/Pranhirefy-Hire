package com.pranhirefy.hire.dto;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CandidateAddressDto {
     
	//@NotNull(message = "Id must not be null")
   // @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Line1 must not be blank")
   // @Column(name = "line1")
    private String line1;

    @NotBlank(message = "City must not be blank")
    //@Column(name = "city")
    private String city;

    @NotBlank(message = "State must not be blank")
   // @Column(name = "state")
    private String state;

    @NotBlank(message = "Country must not be blank")
   // @Column(name = "country")
    private String country;

    @NotNull(message = "Postal code must not be null")
    //@Column(name = "postal_code")
    private Integer postalCode;

    @NotBlank(message = "Type must not be blank")
    //@Column(name = "type")
    private String type;

   // @Column(name = "deleted")
    @NotBlank(message = "deleted must not be blank")
    private String deleted;

    @NotBlank(message = "CreatedBy must not be blank")
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    //@Column(name = "created_date")
    @NotNull(message = "CreatedDate must not be blank")
    private Date createdDate;

   // @Column(name = "modified_date")
    @NotNull(message = "modifieddate must not be blank")
    private Date modifiedDate;

    @NotNull(message = "Candidate ID must not be null")
   // @Column(name = "cid")  // Assuming candidateId maps to cid FK column
    private Integer candidateId;

    
    //private Integer candidateId; // this field will hold only ID
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
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	public CandidateAddressDto(Integer id, String line1, String city, String state, String country, Integer postalCode,
			String type, String deleted, String createdBy, String modifiedBy, Date createdDate, Date modifiedDate,
			Integer candidateId) {
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
		this.candidateId = candidateId;
	}
	public CandidateAddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}

   
    
}
