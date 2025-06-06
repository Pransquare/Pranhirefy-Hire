package com.pranhirefy.hire.exception;

//--- CandidateAddressNotFoundException.java ---


public class CandidateAddressNotFoundException extends RuntimeException {
 public CandidateAddressNotFoundException(String resourceName, String fieldName, Object fieldValue) {
     super(resourceName + " not found with " + fieldName + " = " + fieldValue);
 }
}
