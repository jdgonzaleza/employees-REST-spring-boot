package com.gorygon.springboot.demoapi.ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorygon.springboot.demoapi.model.Employee;
import com.gorygon.springboot.demoapi.service.EmployeeService;
import com.gorygon.springboot.demoapi.ui.model.EmployeeToRest;
import com.gorygon.springboot.demoapi.util.RestControllerTestFixture;
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
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeesController.class)
public class EmployeesControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeService service;
	private List<Employee> employeeList;
	private List<EmployeeToRest> employeeListToRest;
	private Employee employee;
	private EmployeeToRest employeeToRest;
	private String serviceUri;

	@Autowired
	private ObjectMapper mapper;
	private String jsonEmployeeList;
	private String jsonEmployee;


	@Before
	public void setup() throws JsonProcessingException {
		employee = new Employee(10L, "Lio", "Messi", "lio@messi.com");

		employeeToRest = RestControllerTestFixture.EmployeeToDTO(employee);

		employeeList = Arrays.asList( new Employee(1L,"Juan", "Gonzalez", "juan@gonzalez.com"),
						new Employee(2L, "Diego", "Arteta", "Diego@arteta.com"));

		employeeListToRest = employeeList.stream()
						.map( employee -> RestControllerTestFixture.EmployeeToDTO(employee)).collect(Collectors.toList());

		serviceUri = "/api/employees/";
		jsonEmployeeList = mapper.writeValueAsString(employeeListToRest);
		jsonEmployee = mapper.writeValueAsString(employeeToRest);
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
		Mockito.when(service.getEmployeeById(10L)).thenReturn(employee);

		mockMvc.perform(get(serviceUri + "{idEmployee}", 10L))
						.andExpect(content().json(jsonEmployee))
						.andExpect(status().isOk());

		Mockito.verify(service).getEmployeeById(10L);
	}

	@Test
	public void createEmployee() throws Exception {
		Employee toCreate = new Employee(11L, "Didier", "Drogba", "didi@drogba.com");
		Mockito.when(service.createEmployee(toCreate)).thenReturn(toCreate);
		String toCreatejson = mapper.writeValueAsString(toCreate);

		mockMvc.perform(post(serviceUri)
						.content(toCreatejson)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk())
						.andExpect(content().json(toCreatejson));

		Mockito.verify(service).createEmployee(toCreate);
	}

	@Test
	public void updateEmployee() throws Exception {

		employee.setFirstName("lio@afa.com");
		Employee toUpdate = new Employee(null, null, null, "lio@afa.com");

		Mockito.when(service.updateEmployee(10L, toUpdate)).thenReturn(employee);

		String toUpdateString =  mapper.writeValueAsString(toUpdate);
		String expectedToJson = mapper.writeValueAsString(employee);

		mockMvc.perform(patch(serviceUri + "{idEmployee}", 10L)
							.content(toUpdateString)
							.contentType(MediaType.APPLICATION_JSON_VALUE))
							.andExpect(content().json(expectedToJson))
							.andExpect(status().isOk());

		Mockito.verify(service).updateEmployee(10L, toUpdate);
	}

	@Test
	public void deleteEmployee() throws Exception {
		mockMvc.perform(delete(serviceUri + "{idEmployee}", 10L))
						.andExpect(status().isNoContent());
		Mockito.verify(service).deleteEmployee(10L);
	}
}
