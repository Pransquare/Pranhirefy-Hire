package com.pranhirefy.hire.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  
    
    
    @Column(name = "aadhaar_no")
    @JsonProperty("aadhaarNo")
    private String aadhaarNo;
    
    @Column(name = "alternate_mobile_no")
    @JsonProperty("alternateMobileNo")
    private String alternateMobileNo;
    
    
    @Column(name = "budget")
    @JsonProperty("budget")
    private String budget;
    
    @Column(name = "created_by")
    @JsonProperty("createdBy")
    private String createdBy;
    
    @Column(name = "email_address")
    @JsonProperty("emailAddress")
    private String emailAddress;
    
    @Column(name = "excepted_ctc")
    @JsonProperty("exceptedCtc")
    private String exceptedCtc;
    
    @Column(name = "first_name")
    @JsonProperty("firstName")
    private String firstName;
    
    @Column(name = "granted_ctc")
    @JsonProperty("grantedCtc")
    private String grantedCtc;
    
    @Column(name = "last_name")
    @JsonProperty("lastName")
    private String lastName;
    
    @Column(name = "modified_by")
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    
    @Column(name = "pan_id")
    @JsonProperty("panId")
    private String panId;

    //@Column(name = "primary_mobile_no")
    @Column(name = "primary-mobile_no")
    @JsonProperty("primaryMobileNo")
    private String primaryMobileNo;
     
    
    @Column(name = "primary_skill")
    @JsonProperty("primarySkill")
    private String primarySkill;
    
    
    @Column(name = "requirement_desc")
    @JsonProperty("requirementDesc")
    private String requirementDesc;
    
    @Column(name = "requirement_id")
    @JsonProperty("requirementId")
    private String requirementId;
    
    @Column(name = "status")
    @JsonProperty("status")
    private String status;
    
    @Column(name = "workflow_status")
    @JsonProperty("workflowStatus")
    private String workflowStatus;
    
    
    
    @Column(name = "budget_comment")
    @JsonProperty("budgetComment")
    private String budgetComment;
    
    
    @Column(name = "dob")
    @JsonProperty("dob")
    private Date dob;
    
    @Column(name = "gender")
    @JsonProperty("gender")
    private String gender;
    
    
    @Column(name = "management_comment")
    @JsonProperty("managementComment")
    private String managementComment;
    
    @Column(name = "document_name")
    @JsonProperty("documentName")
    private String documentName;
    
    @Column(name = "document_type")
    @JsonProperty("documentType")
    private String documentType;
    
    @Column(name = "employment_type")
    @JsonProperty("employmentType")
    private String employmentType;
    
    @Column(name = "nationality")
    @JsonProperty("nationality")
    private String nationality;
    
    @Column(name = "work_location")
    @JsonProperty("workLocation")
    private String workLocation;
    
    @Column(name = "ctc_type")
    @JsonProperty("ctcType")
    private String ctcType;
    
    @Column(name = "document_number")
    @JsonProperty("documentNumber")
    private String documentNumber;
    
    @Column(name = "middle_name")
    @JsonProperty("middleName")
    private String middleName;
    
    @Column(name = "expiry")
    @JsonProperty("expiry")
    private String expiry;
    
    @Column(name = "passport_number")
    @JsonProperty("passportNumber")
    private String passportNumber;
    
    @Column(name = "visa")
    @JsonProperty("visa")
    private String visa;
    
    @Column(name = "visa_country")
    @JsonProperty("visaCountry")
    private String visaCountry;
    
    @Column(name = "visa_expiry")
    @JsonProperty("visaExpiry")
    private String visaExpiry;
    
    @Column(name = "vendor_name")
    @JsonProperty("vendorName")
    private String vendorName;
    
    @Column(name = "emergency_mobile_no")
    @JsonProperty("emergencyMobileNo")
    private String emergencyMobileNo;
    
    @Column(name = "doj")
    @JsonProperty("doj")
    private Date doj;
    
    @Column(name = "priority")
    @JsonProperty("priority")
    private String priority;
   
    
    @Column(name = "deleted")
    @JsonProperty("deleted")
    private String deleted = "No";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    @JsonProperty("createdDate")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    @JsonProperty("modifiedDate")
    private Date modifiedDate;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateOfferLetter> offerLetters = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateEmployment> employments = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateEducation> educations = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateAddress> addresses = new ArrayList<>();
    
    // Getters and Setters 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getExceptedCtc() {
		return exceptedCtc;
	}

	public void setExceptedCtc(String exceptedCtc) {
		this.exceptedCtc = exceptedCtc;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGrantedCtc() {
		return grantedCtc;
	}

	public void setGrantedCtc(String grantedCtc) {
		this.grantedCtc = grantedCtc;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public String getPrimaryMobileNo() {
		return primaryMobileNo;
	}

	public void setPrimaryMobileNo(String primaryMobileNo) {
		this.primaryMobileNo = primaryMobileNo;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public String getRequirementDesc() {
		return requirementDesc;
	}

	public void setRequirementDesc(String requirementDesc) {
		this.requirementDesc = requirementDesc;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
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

	public String getBudgetComment() {
		return budgetComment;
	}

	public void setBudgetComment(String budgetComment) {
		this.budgetComment = budgetComment;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getManagementComment() {
		return managementComment;
	}

	public void setManagementComment(String managementComment) {
		this.managementComment = managementComment;
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmergencyMobileNo() {
		return emergencyMobileNo;
	}

	public void setEmergencyMobileNo(String emergencyMobileNo) {
		this.emergencyMobileNo = emergencyMobileNo;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public List<CandidateOfferLetter> getOfferLetters() {
		return offerLetters;
	}

	public void setOfferLetters(List<CandidateOfferLetter> offerLetters) {
		this.offerLetters = offerLetters;
	}

	public List<CandidateEmployment> getEmployments() {
		return employments;
	}

	public void setEmployments(List<CandidateEmployment> employments) {
		this.employments = employments;
	}

	public List<CandidateEducation> getEducations() {
		return educations;
	}

	public void setEducations(List<CandidateEducation> educations) {
		this.educations = educations;
	}

	public List<CandidateAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CandidateAddress> addresses) {
		this.addresses = addresses;
	}

	public Candidate(Integer id, String aadhaarNo, String alternateMobileNo, String budget, String createdBy,
			String emailAddress, String exceptedCtc, String firstName, String grantedCtc, String lastName,
			String modifiedBy, String panId, String primaryMobileNo, String primarySkill, String requirementDesc,
			String requirementId, String status, String workflowStatus, String budgetComment, Date dob, String gender,
			String managementComment, String documentName, String documentType, String employmentType,
			String nationality, String workLocation, String ctcType, String documentNumber, String middleName,
			String expiry, String passportNumber, String visa, String visaCountry, String visaExpiry, String vendorName,
			String emergencyMobileNo, Date doj, String priority, String deleted, Date createdDate, Date modifiedDate,
			List<CandidateOfferLetter> offerLetters, List<CandidateEmployment> employments,
			List<CandidateEducation> educations, List<CandidateAddress> addresses) {
		super();
		this.id = id;
		this.aadhaarNo = aadhaarNo;
		this.alternateMobileNo = alternateMobileNo;
		this.budget = budget;
		this.createdBy = createdBy;
		this.emailAddress = emailAddress;
		this.exceptedCtc = exceptedCtc;
		this.firstName = firstName;
		this.grantedCtc = grantedCtc;
		this.lastName = lastName;
		this.modifiedBy = modifiedBy;
		this.panId = panId;
		this.primaryMobileNo = primaryMobileNo;
		this.primarySkill = primarySkill;
		this.requirementDesc = requirementDesc;
		this.requirementId = requirementId;
		this.status = status;
		this.workflowStatus = workflowStatus;
		this.budgetComment = budgetComment;
		this.dob = dob;
		this.gender = gender;
		this.managementComment = managementComment;
		this.documentName = documentName;
		this.documentType = documentType;
		this.employmentType = employmentType;
		this.nationality = nationality;
		this.workLocation = workLocation;
		this.ctcType = ctcType;
		this.documentNumber = documentNumber;
		this.middleName = middleName;
		this.expiry = expiry;
		this.passportNumber = passportNumber;
		this.visa = visa;
		this.visaCountry = visaCountry;
		this.visaExpiry = visaExpiry;
		this.vendorName = vendorName;
		this.emergencyMobileNo = emergencyMobileNo;
		this.doj = doj;
		this.priority = priority;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.offerLetters = offerLetters;
		this.employments = employments;
		this.educations = educations;
		this.addresses = addresses;
	}

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

   
}
