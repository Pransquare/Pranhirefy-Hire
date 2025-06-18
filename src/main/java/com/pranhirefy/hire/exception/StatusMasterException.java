package com.pranhirefy.hire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class StatusMasterException extends RuntimeException 
{
	
	public StatusMasterException(String message)
	{
		super(message);
	}

}
