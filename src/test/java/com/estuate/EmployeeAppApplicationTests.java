package com.estuate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.estuate.controller.EmployeeController;

@SpringBootTest
class EmployeeAppApplicationTests {

	@Autowired
	private EmployeeController empController;
	
	@Test
	void contextLoads() {
		assertThat(empController).isNotNull();
	}

}