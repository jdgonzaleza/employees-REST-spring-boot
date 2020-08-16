package com.gorygon.springboot.demoapi.util;

import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.ui.model.EmployeeToRest;

public class RestControllerTestFixture {

	public static EmployeeToRest EmployeeToDTO(Employee employee)
	{
		return new EmployeeToRest(employee);
	}

	public static Employee DTOToEmployee(EmployeeToRest employeeToRest)
	{
		return employeeToRest.toEntity();
	}
}
