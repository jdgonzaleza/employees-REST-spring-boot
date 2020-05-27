package com.gorygon.springboot.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gorygon.springboot.demoapi.exception.ResourceNotFoundException;
import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;


	@Override
	public Employee getEmployeeById(Long idEmployee) {
		return employeeRepository.findById(idEmployee)
						.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", idEmployee));
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
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
		return employeeRepository.findAll();
	}

	@Override
	public ResponseEntity<?> deleteEmployee(Long idEmployee, Employee employee) {
		Employee thisEmployee = employeeRepository.findById(idEmployee)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", idEmployee));

		employeeRepository.delete(thisEmployee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
