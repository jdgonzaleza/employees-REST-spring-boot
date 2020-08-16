package com.gorygon.springboot.demoapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gorygon.springboot.demoapi.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(Long id);
	public Employee createEmployee(Employee employee);
	public Employee updateEmployee(Long id, Employee employee);
	public ResponseEntity<?> deleteEmployee(Long id);
}
