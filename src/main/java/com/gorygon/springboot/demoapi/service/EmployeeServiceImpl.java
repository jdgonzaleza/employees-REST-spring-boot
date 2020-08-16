package com.gorygon.springboot.demoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeById(Long idEmployee) {
		return employeeRepository.findById(idEmployee)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " +
										idEmployee + " not found."));
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long idEmployee, Employee employee) {
		
		Employee dbEmployee = employeeRepository.findById(idEmployee)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " +
								idEmployee + " not found."));
		employee.mergeForUpdate(dbEmployee);
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public ResponseEntity<?> deleteEmployee(Long idEmployee) {
		Employee thisEmployee = employeeRepository.findById(idEmployee)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " +
								idEmployee + " not found."));
		employeeRepository.delete(thisEmployee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
