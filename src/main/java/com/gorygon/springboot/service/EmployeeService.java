package com.gorygon.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.gorygon.springboot.exception.ResourceNotFoundException;
import com.gorygon.springboot.model.Employee;
import com.gorygon.springboot.repository.EmployeeRepository;

public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;


	public Employee getEmployeeById(Long idEmployee) {
		return employeeRepository.getOne(idEmployee);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long idEmployee, Employee employee) {
		
		Employee thisEmployee = employeeRepository.findById(idEmployee)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", idEmployee));

		thisEmployee.setFirstName(employee.getFirstName());
		thisEmployee.setEmail(employee.getEmail());
		thisEmployee.setLastName(employee.getLastName());

		return thisEmployee;
	}


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		System.out.println();
		return employeeRepository.findAll();
	}

	@Override
	public ResponseEntity<?> deleteEmployee(Long idEmployee, Employee employee) {
		// TODO Auto-generated method stub
		Employee thisEmployee = employeeRepository.findById(idEmployee)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", idEmployee));

		employeeRepository.delete(thisEmployee);
		return ResponseEntity.ok().build();
	}

}
