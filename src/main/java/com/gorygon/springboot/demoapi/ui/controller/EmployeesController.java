package com.gorygon.springboot.demoapi.ui.controller;

import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.service.EmployeeService;
import com.gorygon.springboot.demoapi.ui.model.EmployeeToRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees/")
public class EmployeesController extends AbstractRestController<EmployeeToRest, Employee> {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("")
	public Employee createEmployee(@Valid @RequestBody EmployeeToRest employee) {
		return employeeService.createEmployee(toEntity(employee));
	}

	@GetMapping("")
	public ResponseEntity<List<EmployeeToRest>> getAllEmployees() {
		List<EmployeeToRest> employeeToRestList = employeeService.getAllEmployees()
						.stream().map( employee -> toRest(employee)).collect(Collectors.toList());

		return new ResponseEntity<>(employeeToRestList, HttpStatus.OK);
	}

	@GetMapping("{idEmployee}")
	public ResponseEntity<EmployeeToRest> getEmployeeById(@PathVariable(value = "idEmployee") Long idEmployee) {
		EmployeeToRest employeeToRest = toRest(employeeService.getEmployeeById(idEmployee));

		return new ResponseEntity<>(employeeToRest, HttpStatus.OK);
	}

	@PatchMapping("{idEmployee}")
	public ResponseEntity<EmployeeToRest> updateEmployee(@PathVariable(value = "idEmployee") Long idEmployee, @RequestBody EmployeeToRest employee) {
		EmployeeToRest updatedEmployee = toRest(employeeService.updateEmployee(idEmployee, toEntity(employee)));

		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("{idEmployee}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "idEmployee") Long idEmployee) {
		employeeService.deleteEmployee(idEmployee);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@Override
	protected Employee toEntity(EmployeeToRest toRest) {
		return toRest.toEntity();
	}

	@Override
	protected EmployeeToRest toRest(Employee employee) {
		return new EmployeeToRest(employee);
	}
}
