package com.estuate.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(GlobalExceptionHandler.class)
public class ExceptionTest {
	
	public String message = "Exception Handled";

	ResourceNotFoundException msg = new ResourceNotFoundException(message);

	@Test
	public void testGetMessage() {

		System.out.println("ResourceNotFound Message is printing ");
		msg.getMessage();

	}

}
