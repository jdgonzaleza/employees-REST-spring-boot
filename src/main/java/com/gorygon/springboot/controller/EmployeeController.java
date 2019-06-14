package com.gorygon.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorygon.springboot.model.Employee;
import com.gorygon.springboot.repository.EmployeeRepository;
import com.gorygon.springboot.service.IEmployeeService;

@RestController
//@RequestMapping("/api")
public class EmployeeController {

//	@Autowired
//	IEmployeeService employeeService;
	
	@Autowired
	EmployeeRepository repository;
//	
//	@PostMapping("/employees")
//	public Employee createEmployee( @Valid @RequestBody Employee employee) {
//		return employeeService.createEmployee(employee);
//	}
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees(){
		return repository.findAll();
	}
	
//	@GetMapping("/employees/{idEmployee}")
//	public Employee getEmployeeById(@PathVariable(value = "idEmployee") Long idEmployee) {
//		return null;
//	}
//	
//	@PutMapping("/employees/{idEmployee}")
//	public Employee updateEmployee(@PathVariable(value = "idEmployee") Long idEmployee,
//			Employee employeee) {
//		return employeeService.updateEmployee(idEmployee, employeee);
//	}
	
//	@DeleteMapping("/employees/{idEmployee}")
//	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "idEmployee") Long idEmployee,
//			Employee employee) {
//		return employeeService.deleteEmployee(idEmployee, employee);
//	}
	
}
