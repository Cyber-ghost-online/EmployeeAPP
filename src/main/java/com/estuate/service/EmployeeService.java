package com.estuate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import com.estuate.entity.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Optional<Employee> getEmployeeById(long id);
	public ResponseEntity<Employee> updateEmployee(Employee employee);
	public ResponseEntity<Employee> deleteEmployee(long id);
}
