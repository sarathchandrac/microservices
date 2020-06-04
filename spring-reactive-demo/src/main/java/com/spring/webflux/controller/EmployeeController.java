package com.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webflux.model.Employee;
import com.spring.webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/emp")
	public Flux<Employee> getEmployees(@RequestBody Employee employee) {
		return employeeRepository.findAll();//.save(employee);
	}

	@GetMapping("/emp/{id}")
	public Mono<Employee> getEmployee(@PathVariable("id") int id) {
		return employeeRepository.findById(id);//.save(employee);
	}

	
	@PostMapping("/emp")
	public Mono<Employee> saveEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
}
