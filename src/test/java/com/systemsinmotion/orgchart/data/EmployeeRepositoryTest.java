package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeRepositoryTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private Employee manager;

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	@After
	public void after() {
		this.employeeRepo.delete(this.employee);
		this.departmentRepo.delete(this.department);

		if (null != this.manager) {
			this.employeeRepo.delete(this.manager);
		}
	}

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.departmentRepo.save(this.department);

		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employeeRepo.save(this.employee);
	}

	@Test
	public void testInstantiation() {
		assertNotNull(departmentRepo);
	}

	private void createManager() {
		this.manager = Entities.manager();
		this.employeeRepo.save(this.manager);
	}

	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.employeeRepo.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByDepartment() throws Exception {
		List<Employee> emps = this.employeeRepo.findByDepartment(this.employee
				.getDepartment());
		assertNotNull("Expecting a non-null list of Employees but was null",
				emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByDepartment_null() throws Exception {
		List<Employee> emps = this.employeeRepo.findByDepartment(null);
		assertTrue(emps.size() == 0);
	}

	@Test
	public void findByEmail() throws Exception {
		Employee emp = this.employeeRepo.findByEmail(this.employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void fintdByEmail_null() throws Exception {
		Employee emp = this.employeeRepo.findByEmail(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByEmailTest_XXX() throws Exception {
		Employee emp = this.employeeRepo.findByEmail(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById() throws Exception {
		Employee emp = this.employeeRepo.findById(this.employee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findById_null() throws Exception {
		Employee emp = this.employeeRepo.findById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById_XXX() throws Exception {
		Employee emp = this.employeeRepo.findById(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByManagerId() throws Exception {
		createManager();

		this.employee.setManager(this.manager);
		this.employeeRepo.save(this.employee);

		List<Employee> emps = this.employeeRepo.findByManagerId(this.employee
				.getManager().getId());
		assertNotNull("Expecting a non-null Employee but was null", emps);
		assertTrue(
				"Expecting at least one employee found for manager but none was found",
				emps.size() > 0);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByManagerId_null() throws Exception {
		List<Employee> emps = this.employeeRepo.findByManagerId(null);
		assertNotNull(emps);
	}

	@Test
	public void findByManager_null() throws Exception {
		List<Employee> emps = this.employeeRepo.findByManager(null);
		assertNotNull(emps);
	}

	@Test
	public void findByIsManager() throws Exception {
		this.employee.setIsManager(true);
		this.employeeRepo.save(this.employee);

		List<Employee> emps = this.employeeRepo.findByIsManager(true);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);

	}
}