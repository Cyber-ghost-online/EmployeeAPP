package com.estuate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estuate.entity.Employee;
import com.estuate.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeServiceImpl.class)
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeServiceImpl empService;
	private Employee employee;
	List<Employee> EmployeeList;
	
	@InjectMocks
	private EmployeeServiceImpl empServiceImpl;
	@MockBean
	private EmployeeRepository empRepository;
	
	@Test
	public void testgetAllEmployee() throws Exception {
		empRepository.save(employee);
		when(empRepository.findAll()).thenReturn(EmployeeList);
		List<Employee> empList = empService.getAllEmployee();
		assertEquals(empList, EmployeeList);
		verify(empRepository, times(1)).save(employee);
		verify(empRepository, times(1)).findAll();
	}
	
	@Test
	public void testaddEmployee() throws Exception {
		when(empRepository.save(any())).thenReturn(employee);
		empService.addEmployee(employee);
		verify(empRepository,times(1)).save(any());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		Employee emp = new Employee(15,"Palani","Macharla","palani@gmail.com",20263);
		emp.setLastName("Kumar");
		when(empRepository.save(emp)).thenReturn(emp);
		assertEquals(empRepository.save(emp).getLastName(), "Kumar");
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		Employee emp = new Employee(15L,"Palani","Macharla","palani@gmail.com",20263);
		empRepository.save(emp);
		empRepository.deleteById(15L);
		empRepository.findById(emp.getEmpId());
		assertEquals(empService.deleteEmployee(15L), new ResponseEntity<Employee>(HttpStatus.OK));
	}
	
	@Test
	public void getEmployeeById() throws Exception{
		long empId = 15L;
		empService.getEmployeeById(empId);
	}
}