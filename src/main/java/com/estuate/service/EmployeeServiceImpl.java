package com.estuate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estuate.entity.Employee;
import com.estuate.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> deleteEmployee(long id) {
		employeeRepository.deleteById(id);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

}