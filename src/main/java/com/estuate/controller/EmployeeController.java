package com.estuate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estuate.entity.Employee;
import com.estuate.exception.EmployeeSuccessfull;
import com.estuate.exception.ResourceNotFoundException;
import com.estuate.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addemployee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	
	@GetMapping("/employeelist")
	public ResponseEntity<?> getAllEmployee() {
		if(employeeService.getAllEmployee().isEmpty()) {
			return new ResponseEntity<>(new ResourceNotFoundException("No Record Found").getMessage(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.OK); 
		}
	}
	
	@GetMapping("/getemployee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable long id) {
		if(employeeService.getEmployeeById(id).isPresent()) {
			return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResourceNotFoundException("Employee is Not Present for this Id"+id).getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
		if(employeeService.getEmployeeById(id).isPresent()) {
			return new ResponseEntity<>(employeeService.updateEmployee(employee),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResourceNotFoundException("Employee is Not Present for this Id"+id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("deleteemployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
		if(employeeService.getEmployeeById(id).isPresent()) {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<>(new EmployeeSuccessfull("Employee Deleted").getMessage(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResourceNotFoundException("Employee is Not Present for this Id"+id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}