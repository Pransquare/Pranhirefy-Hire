package com.pranhirefy.hire.Dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CandidateDto {
   
    private Integer id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Middle name is required")
    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email address is required")
    private String emailAddress;

    @NotBlank(message = "Primary mobile number is required")
    private String primaryMobileNo;

    @NotBlank(message = "Alternate mobile number is required")
    private String alternateMobileNo;

    @NotBlank(message = "Emergency mobile number is required")
    private String emergencyMobileNo;

    @NotBlank(message = "PAN ID is required")
    private String panId;

    @NotBlank(message = "Aadhaar number is required")
    private String aadhaarNo;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Visa is required")
    private String visa;

    @NotBlank(message = "Visa country is required")
    private String visaCountry;

    @NotBlank(message = "Visa expiry is required")
    private String visaExpiry;

    @NotBlank(message = "Passport number is required")
    private String passportNumber;

    @NotBlank(message = "Document name is required")
    private String documentName;

    @NotBlank(message = "Document type is required")
    private String documentType;

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @NotBlank(message = "Budget is required")
    private String budget;

    @NotBlank(message = "Budget comment is required")
    private String budgetComment;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Workflow status is required")
    private String workflowStatus;

    @NotBlank(message = "Priority is required")
    private String priority;

    @NotBlank(message = "Primary skill is required")
    private String primarySkill;

    @NotBlank(message = "Requirement ID is required")
    private String requirementId;

    @NotBlank(message = "Requirement description is required")
    private String requirementDesc;

    @NotBlank(message = "Employment type is required")
    private String employmentType;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "Work location is required")
    private String workLocation;

    @NotBlank(message = "CTC type is required")
    private String ctcType;

    @NotBlank(message = "Created by is required")
    private String createdBy;

   // @NotBlank(message = "Modified by is required")
    private String modifiedBy;

    @NotBlank(message = "Deleted flag is required")
    private String deleted;

    @NotBlank(message = "Expected CTC is required")
    private String exceptedCtc;

    @NotBlank(message = "Granted CTC is required")
    private String grantedCtc;

    @NotBlank(message = "Management comment is required")
    private String managementComment;

    @NotBlank(message = "Vendor name is required")
    private String vendorName;

    @NotBlank(message = "Expiry is required")
    private String expiry;

    @NotNull(message = "Date of birth is required")
    private Date dob;

    @NotNull(message = "Date of joining is required")
    private Date doj;

    @NotNull(message = "Created date is required")
    private Date createdDate;

    @NotNull(message = "Modified date is required")
    private Date modifiedDate;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getMiddleName() {
	return middleName;
}
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
public String getPrimaryMobileNo() {
	return primaryMobileNo;
}
public void setPrimaryMobileNo(String primaryMobileNo) {
	this.primaryMobileNo = primaryMobileNo;
}
public String getAlternateMobileNo() {
	return alternateMobileNo;
}
public void setAlternateMobileNo(String alternateMobileNo) {
	this.alternateMobileNo = alternateMobileNo;
}
public String getEmergencyMobileNo() {
	return emergencyMobileNo;
}
public void setEmergencyMobileNo(String emergencyMobileNo) {
	this.emergencyMobileNo = emergencyMobileNo;
}
public String getPanId() {
	return panId;
}
public void setPanId(String panId) {
	this.panId = panId;
}
public String getAadhaarNo() {
	return aadhaarNo;
}
public void setAadhaarNo(String aadhaarNo) {
	this.aadhaarNo = aadhaarNo;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getVisa() {
	return visa;
}
public void setVisa(String visa) {
	this.visa = visa;
}
public String getVisaCountry() {
	return visaCountry;
}
public void setVisaCountry(String visaCountry) {
	this.visaCountry = visaCountry;
}
public String getVisaExpiry() {
	return visaExpiry;
}
public void setVisaExpiry(String visaExpiry) {
	this.visaExpiry = visaExpiry;
}
public String getPassportNumber() {
	return passportNumber;
}
public void setPassportNumber(String passportNumber) {
	this.passportNumber = passportNumber;
}
public String getDocumentName() {
	return documentName;
}
public void setDocumentName(String documentName) {
	this.documentName = documentName;
}
public String getDocumentType() {
	return documentType;
}
public void setDocumentType(String documentType) {
	this.documentType = documentType;
}
public String getDocumentNumber() {
	return documentNumber;
}
public void setDocumentNumber(String documentNumber) {
	this.documentNumber = documentNumber;
}
public String getBudget() {
	return budget;
}
public void setBudget(String budget) {
	this.budget = budget;
}
public String getBudgetComment() {
	return budgetComment;
}
public void setBudgetComment(String budgetComment) {
	this.budgetComment = budgetComment;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getWorkflowStatus() {
	return workflowStatus;
}
public void setWorkflowStatus(String workflowStatus) {
	this.workflowStatus = workflowStatus;
}
public String getPriority() {
	return priority;
}
public void setPriority(String priority) {
	this.priority = priority;
}
public String getPrimarySkill() {
	return primarySkill;
}
public void setPrimarySkill(String primarySkill) {
	this.primarySkill = primarySkill;
}
public String getRequirementId() {
	return requirementId;
}
public void setRequirementId(String requirementId) {
	this.requirementId = requirementId;
}
public String getRequirementDesc() {
	return requirementDesc;
}
public void setRequirementDesc(String requirementDesc) {
	this.requirementDesc = requirementDesc;
}
public String getEmploymentType() {
	return employmentType;
}
public void setEmploymentType(String employmentType) {
	this.employmentType = employmentType;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getWorkLocation() {
	return workLocation;
}
public void setWorkLocation(String workLocation) {
	this.workLocation = workLocation;
}
public String getCtcType() {
	return ctcType;
}
public void setCtcType(String ctcType) {
	this.ctcType = ctcType;
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
public String getExceptedCtc() {
	return exceptedCtc;
}
public void setExceptedCtc(String exceptedCtc) {
	this.exceptedCtc = exceptedCtc;
}
public String getGrantedCtc() {
	return grantedCtc;
}
public void setGrantedCtc(String grantedCtc) {
	this.grantedCtc = grantedCtc;
}
public String getManagementComment() {
	return managementComment;
}
public void setManagementComment(String managementComment) {
	this.managementComment = managementComment;
}
public String getVendorName() {
	return vendorName;
}
public void setVendorName(String vendorName) {
	this.vendorName = vendorName;
}
public String getExpiry() {
	return expiry;
}
public void setExpiry(String expiry) {
	this.expiry = expiry;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public Date getDoj() {
	return doj;
}
public void setDoj(Date doj) {
	this.doj = doj;
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
public CandidateDto(Integer id, String firstName, String middleName, String lastName, String emailAddress,
		String primaryMobileNo, String alternateMobileNo, String emergencyMobileNo, String panId, String aadhaarNo,
		String gender, String visa, String visaCountry, String visaExpiry, String passportNumber, String documentName,
		String documentType, String documentNumber, String budget, String budgetComment, String status,
		String workflowStatus, String priority, String primarySkill, String requirementId, String requirementDesc,
		String employmentType, String nationality, String workLocation, String ctcType, String createdBy,
		String modifiedBy, String deleted, String exceptedCtc, String grantedCtc, String managementComment,
		String vendorName, String expiry, Date dob, Date doj, Date createdDate, Date modifiedDate) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.emailAddress = emailAddress;
	this.primaryMobileNo = primaryMobileNo;
	this.alternateMobileNo = alternateMobileNo;
	this.emergencyMobileNo = emergencyMobileNo;
	this.panId = panId;
	this.aadhaarNo = aadhaarNo;
	this.gender = gender;
	this.visa = visa;
	this.visaCountry = visaCountry;
	this.visaExpiry = visaExpiry;
	this.passportNumber = passportNumber;
	this.documentName = documentName;
	this.documentType = documentType;
	this.documentNumber = documentNumber;
	this.budget = budget;
	this.budgetComment = budgetComment;
	this.status = status;
	this.workflowStatus = workflowStatus;
	this.priority = priority;
	this.primarySkill = primarySkill;
	this.requirementId = requirementId;
	this.requirementDesc = requirementDesc;
	this.employmentType = employmentType;
	this.nationality = nationality;
	this.workLocation = workLocation;
	this.ctcType = ctcType;
	this.createdBy = createdBy;
	this.modifiedBy = modifiedBy;
	this.deleted = deleted;
	this.exceptedCtc = exceptedCtc;
	this.grantedCtc = grantedCtc;
	this.managementComment = managementComment;
	this.vendorName = vendorName;
	this.expiry = expiry;
	this.dob = dob;
	this.doj = doj;
	this.createdDate = createdDate;
	this.modifiedDate = modifiedDate;
}
public CandidateDto() {
	super();
	// TODO Auto-generated constructor stub
}







}