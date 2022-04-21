package com.estuate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estuate.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}
