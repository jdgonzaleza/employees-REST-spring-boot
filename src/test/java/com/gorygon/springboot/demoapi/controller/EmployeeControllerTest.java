package com.gorygon.springboot.demoapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorygon.springboot.demoapi.controller.EmployeeController;
import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.service.IEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IEmployeeService service;
	private List<Employee> employeeList;
	private Employee employee;
	private String serviceUri;

	@Autowired
	private ObjectMapper mapper;
	private String jsonEmployeeList;
	private String jsonEmployee;

	@Before
	public void setup() throws JsonProcessingException {
		employee = new Employee(Long.getLong("10"), "Lio", "Messi", "lio@messi.com");
		employeeList = Arrays.asList( new Employee(Long.getLong("1"),"Juan", "Gonzalez", "juan@gonzalez.com"),
						new Employee(Long.getLong("2"), "Diego", "Arteta", "Diego@arteta.com"));
		serviceUri = "/api/employees/";
		jsonEmployeeList = mapper.writeValueAsString(employeeList);
		jsonEmployee = mapper.writeValueAsString(jsonEmployee);
	}

	@Test
	public void getAllEmployees() throws Exception {
		Mockito.when(service.getAllEmployees()).thenReturn(employeeList);

		mockMvc.perform(get(serviceUri))
						.andExpect(content().json(jsonEmployeeList))
						.andExpect(status().isOk());

		Mockito.verify(service).getAllEmployees();
	}

	@Test
	public void getEmployeeById() throws Exception {
		Mockito.when(service.getEmployeeById(Long.getLong("10"))).thenReturn(employeeList.get(0));

		mockMvc.perform(get(serviceUri + "10/"))
						.andExpect(content().json(jsonEmployee))
						.andExpect(status().isOk());

		Mockito.verify(service).getEmployeeById(Long.getLong("10"));
	}

	@Test
	public void createEmployee() throws Exception {
		Employee toCreate = new Employee(Long.getLong("11"), "Didier", "Drogba", "didi@drogba.com");
		String toCreatejson = mapper.writeValueAsString(toCreate);

		mockMvc.perform(post(serviceUri)
						.content(toCreatejson)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk());

		Mockito.verify(service).createEmployee(toCreate);
	}
}
