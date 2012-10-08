package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")

public class EmployeeDAOTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private Employee manager;

	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	DepartmentDAO departmentDAO;

	@Before
	public void before() throws Exception {
		department = TestObject.department();
		departmentDAO.save(department);

		employee = TestObject.employee();
		employee.setDepartment(department);
		employee.setEmployeeId(employeeDAO.save(employee));
	}

	@After
	public void after() {
		employeeDAO.delete(employee);
		departmentDAO.delete(department);

		if (null != manager) {
			employeeDAO.delete(manager);
		}
	}

	@Test
	@Rollback
	public void findByIdTest_null() throws Exception {
		Employee emp = employeeDAO.findById(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByAll() throws Exception {
		List<Employee> emps = employeeDAO.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByIdTest() throws Exception {
		Employee emp = employeeDAO.findById(employee.getEmployeeId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(TestObject.FIRST_NAME, emp.getFirstName());
		assertEquals(TestObject.LAST_NAME, emp.getLastName());
		assertEquals(TestObject.EMAIL, emp.getEmail());
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByEmailTest_null() throws Exception {
		Employee emp = employeeDAO.findByEmail(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByEmailTest() throws Exception {
		Employee emp = employeeDAO.findByEmail(employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(TestObject.FIRST_NAME, emp.getFirstName());
		assertEquals(TestObject.LAST_NAME, emp.getLastName());
		assertEquals(TestObject.EMAIL, emp.getEmail());
	}

	/*
	 * @Test
	 *
	 * @Rollback public void findByDepartmentTest_empty() throws Exception {
	 * List<Employee> emps = employeeDAO.findByDepartment(new Department());
	 * assertEquals(0, emps.size()); }
	 */
	
//	@Ignore
	@Test
	@Rollback
	public void findByDepartmentTest() throws Exception {
		List<Employee> emps = employeeDAO.findByDepartment(employee
				.getDepartment());
		assertNotNull("Expecting a non-null Employee but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(TestObject.FIRST_NAME, emp.getFirstName());
		assertEquals(TestObject.LAST_NAME, emp.getLastName());
		assertEquals(TestObject.EMAIL, emp.getEmail());
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByManagerIdTest_empty() throws Exception {
		List<Employee> emps = employeeDAO.findByManager(TestObject.employee());
		assertEquals(0, emps.size());
	}
	
//	@Ignore
	@Test
	@Rollback
	public void findByManagerIdTest() throws Exception {
		createManager();

		employee.setManager(manager);
		employeeDAO.update(employee);

		List<Employee> emps = employeeDAO.findByManager(employee.getManager());
		assertNotNull("Expecting a non-null Employee but was null", emps);
		assertTrue(
				"Expecting at least one employee found for manager but none was found",
				emps.size() > 0);
		Employee emp = emps.get(0);
		assertEquals(TestObject.FIRST_NAME, emp.getFirstName());
		assertEquals(TestObject.LAST_NAME, emp.getLastName());
		assertEquals(TestObject.EMAIL, emp.getEmail());
	}

	private void createManager() {
		manager = TestObject.manager();
		employeeDAO.save(manager);
	}
}
