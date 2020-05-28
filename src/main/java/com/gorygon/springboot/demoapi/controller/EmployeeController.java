package com.gorygon.springboot.demoapi.controller;

import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@PostMapping("")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping("")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("{idEmployee}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "idEmployee") Long idEmployee) {
		return new ResponseEntity<>(employeeService.getEmployeeById(idEmployee), HttpStatus.OK);
	}

	@PatchMapping("{idEmployee}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "idEmployee") Long idEmployee, Employee employeee) {
		return new ResponseEntity<>(employeeService.updateEmployee(idEmployee, employeee), HttpStatus.OK);
	}

	@DeleteMapping("{idEmployee}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "idEmployee") Long idEmployee, Employee employee) {
		return employeeService.deleteEmployee(idEmployee, employee);
	}

}
