package com.estuate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class EmployeeSuccessfull extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}
	
	public EmployeeSuccessfull(String message) {
		this.message = message;
	}

}
