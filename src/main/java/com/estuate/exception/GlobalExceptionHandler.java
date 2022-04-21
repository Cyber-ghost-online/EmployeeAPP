package com.estuate.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
