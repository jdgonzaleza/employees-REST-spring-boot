package com.gorygon.springboot.demoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable, MutableObject<Employee> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;
	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Override
	public Employee mergeForUpdate(Employee dbObject) {
		if (id == null)
			id = dbObject.getId();
		if (firstName == null)
			firstName = dbObject.getFirstName();
		if (lastName == null)
			lastName = dbObject.getLastName();
		if (email == null)
			email = dbObject.getEmail();
		return this;
	}
}
