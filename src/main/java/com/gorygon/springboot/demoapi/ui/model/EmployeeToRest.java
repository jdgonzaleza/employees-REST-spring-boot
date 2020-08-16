package com.gorygon.springboot.demoapi.ui.model;

import com.gorygon.springboot.demoapi.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeToRest {
	private Long id;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Email
	@NotNull
	private String email;

	public EmployeeToRest(Employee employee ) {
		id = employee.getId();
		firstName = employee.getFirstName();
		lastName = employee.getLastName();
		email = employee.getEmail();
	}

	public Employee toEntity() {
		return new Employee(id, firstName, lastName, email);
	}
}
