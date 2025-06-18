package com.pranhirefy.hire.exception;

import java.util.Map;

public class StatusMasterValidationException extends RuntimeException {
    private final Map<String, String> fieldErrors;

    public StatusMasterValidationException(String message, Map<String, String> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}





