package com.spring.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.webflux.model.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer>{

}
