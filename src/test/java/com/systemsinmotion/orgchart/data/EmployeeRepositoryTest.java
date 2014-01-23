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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

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
	private JobTitle jobTitle;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	JobTitleRepository jobTitleRepository;

	// @After
	// public void after() {
	// this.employeeDao.delete(this.employee);
	// this.departmentDao.delete(this.department);
	//
	// if (null != this.manager) {
	// this.employeeDao.delete(this.manager);
	// }
	// }

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.departmentRepository.saveAndFlush(this.department);
		this.jobTitle = Entities.jobTitle();
		this.jobTitleRepository.saveAndFlush(this.jobTitle);
		

		this.employee = Entities.employee();
		this.employee.setJobTitle(this.jobTitle);
		this.employee.setDepartment(this.department);
		this.employee = this.employeeRepository.saveAndFlush(employee);
	}

	@Test
	public void testInstantiation() {
		assertNotNull(departmentRepository);
	}

	private void createManager() {
		this.manager = Entities.manager();
		this.employeeRepository.save(this.manager);
	}

	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.employeeRepository.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByDepartment() throws Exception {
		List<Employee> emps = this.employeeRepository
				.findByDepartment(this.employee.getDepartment());
		assertNotNull("Expecting a non-null list of Employees but was null",
				emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByDepartmentId() throws Exception {
		List<Employee> emps = this.employeeRepository
				.findByDepartmentId(this.employee.getDepartment().getId());
		assertNotNull("Expecting a non-null list of Employees but was null",
				emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByDepartment_null() throws Exception {
		List<Employee> emps = this.employeeRepository.findByDepartmentId(null);
		assert (emps.isEmpty());
	}

	@Test
	public void findByEmail() throws Exception {
		Employee emp = this.employeeRepository.findByEmail(this.employee
				.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByEmail_null() throws Exception {
		Employee emp = this.employeeRepository.findByEmail(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByEmailTest_XXX() throws Exception {
		Employee emp = this.employeeRepository.findByEmail(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById() throws Exception {
		Employee emp = this.employeeRepository.findById(this.employee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findById_null() throws Exception {
		Employee emp = this.employeeRepository.findById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById_XXX() throws Exception {
		Employee emp = this.employeeRepository.findById(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByManagerId() throws Exception {
		createManager();

		this.employee.setManager(this.manager);
		this.employeeRepository.saveAndFlush(employee);

		List<Employee> emps = this.employeeRepository
				.findByManagerId(this.employee.getManager().getId());
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
	public void findByManagerId_empty() throws Exception {
		List<Employee> emps = this.employeeRepository.findByManagerId(Entities
				.employee().getId());
		assert (emps.isEmpty());
	}

	@Test
	public void findByManagerId_null() throws Exception {
		List<Employee> emps = this.employeeRepository.findByManagerId(null);
		assert (emps.isEmpty());
	}

	@Test
	public void findByDeptandJob() throws Exception {
		List<Employee> emps = this.employeeRepository
				.findByJobTitleAndDepartment(this.jobTitle,
						this.department);
		assert (!emps.isEmpty());
	}

	@Test
	public void findByDeptAndName() throws Exception {

		Department nullDept = new Department();
		nullDept.setId(64398732);
		List<Employee> emps = this.employeeRepository.findByDepartmentAndFirstNameOrLastName(nullDept, Entities.FIRST_NAME, Entities.LAST_NAME);
		assertTrue("1", emps.isEmpty());
		emps = this.employeeRepository.findByDepartmentAndFirstNameOrLastName(nullDept, "blah", "blah");
		assertTrue("2", emps.isEmpty());
		emps = this.employeeRepository.findByDepartmentAndFirstNameOrLastName(nullDept, "blah", Entities.LAST_NAME);
		assertTrue("3", emps.isEmpty());
		emps = this.employeeRepository.findByDepartmentAndFirstNameOrLastName(this.department, "blah", Entities.LAST_NAME);
		assertTrue("4", !emps.isEmpty());
		emps = this.employeeRepository.findByDepartmentAndFirstNameOrLastName(this.department, "blah", "LAST NAME");
		assertTrue("5", !emps.isEmpty());
	}
	
	@Test
	public void findByFirstNameIn() throws Exception {
		this.employeeRepository.findByFirstNameIn("John");
	}

}