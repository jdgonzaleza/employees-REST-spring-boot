package com.gorygon.springboot.demoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable, GoryObject<Employee> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
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
