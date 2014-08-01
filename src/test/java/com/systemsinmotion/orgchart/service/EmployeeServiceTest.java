package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Employee employee;

	@Autowired
	private Department depart;

	@Before
	public void setup() {
		if (employeeService == null)
			fail("AutoWired failed");
	}

	@Test
	public void findActive() {
		List<Employee> emps = this.employeeService.activeEmployees();
		System.err.println(emps);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);

	}

	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void storeEmployee() {

		Employee emp = this.employeeService.storeEmployee(this.employee, true);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}

	@Test
	public void findByJobTitleName() {
		List<Employee> emps = this.employeeService
				.findByJobTitleName(Entities.JOB_TITLE_NAME);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void findEmployeeByID() {
		Employee emp = this.employeeService
				.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}

	@Test
	public void filterEmployeesNullArgs() {

		List<Employee> emps = employeeService.filterEmployees(" ", " ", "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void filterEmployeesByFirstName() {

		System.err.println(Entities.PREDICATEFIRSTNAMEORLAST);
		List<Employee> emps = employeeService.filterEmployees(
				Entities.FIRST_NAME, " ", "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void filterEmployeesByLastName() {
		List<Employee> emps = employeeService.filterEmployees(" ",
				Entities.LAST_NAME, "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void filterEmployeesDepartmentID() {
		List<Employee> emps = employeeService.filterEmployees(" ", " ",
				Entities.DEPT_ID.toString(), "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void filterEmployeesTitleID() {
		List<Employee> emps = employeeService.filterEmployees(" ", " ", "",
				Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}

	@Test
	public void autoComplete() {

		String emps = employeeService.autoComplete("B");
		assertNotNull(emps);
		assertTrue(emps.length() > 0);
	}

}
