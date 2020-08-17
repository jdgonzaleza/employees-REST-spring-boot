package com.gorygon.springboot.demoapi.ui.model;

import com.gorygon.springboot.demoapi.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeToRest {

	private Long id;

	@NotNull(message = "First name cannot be nul")
	@Size(min = 2, message = "First name must not be less than two characters")
	private String firstName;

	@NotNull(message = "Last name cannot be nul")
	@Size(min = 2, message = "Last name must not be less than two characters")
	private String lastName;

	@NotNull(message = "Email cannot be nul")
	@Email
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
