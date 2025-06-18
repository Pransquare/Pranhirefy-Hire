package com.pranhirefy.hire.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Status_Master")
public class StatusMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Status_Master_Code", unique = true)
    private Integer statusMasterCode;


    @Column(name = "Created_Date")
    private LocalDate createdDate;

    @Column(name = "Modified_Date")
    private LocalDate modifiedDate;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Modified_By")
    private String modifiedBy;

    @Column(name = "Status_Master_Description")
    private String statusMasterDescription;
    
    @Column(name = "is_deleted", nullable = false)
    private String isDeleted = "No"; // default value

    

    


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
    
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setStatusMasterDescription(String statusMasterDescription) {
        this.statusMasterDescription = statusMasterDescription;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    // --- Constructors ---

    public StatusMasterEntity() {}

    public StatusMasterEntity(long id, Integer statusMasterCode, LocalDate createdDate, LocalDate modifiedDate,
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
