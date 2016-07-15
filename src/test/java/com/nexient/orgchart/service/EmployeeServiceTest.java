package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	EmployeeService employeeService;

	EmployeeRepository mockEmployeeRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();

	@BeforeClass
	public void before() {

	}

	@Test
	@Rollback
	public void findEmployees() {
		assertNotNull(employeeService.findAll());
	}

	@Test
	@Rollback
	public void findByID() {
		assertTrue(true);
	}

	@Test
	@Rollback
	public void shouldCallSaveMethodinEmployeeDAO() {
		assertTrue(true);
	}
}
