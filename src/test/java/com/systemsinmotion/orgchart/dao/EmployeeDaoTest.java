package com.systemsinmotion.orgchart.dao;

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
import org.springframework.dao.DataIntegrityViolationException;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeDaoTest {

	public static final String SOME_NEW_EMAIL = "new@email.com";
	
	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private Employee manager;
	private JobTitle jobTitle;

	@Autowired
	IEmployeeDao employeeDao;
	
	@Autowired
	IDepartmentDao departmentDao;
	
	@Autowired
	IJobTitleDao jobTitleDao;

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.departmentDao.save(this.department);
		
		this.jobTitle = Entities.jobTitle();
		this.jobTitleDao.save(this.jobTitle);

		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employee.setJobTitle(this.jobTitle);
		this.employee.setId(this.employeeDao.save(this.employee));
	}

	@Test
	public void created() throws Exception {
		assertNotNull(this.employee);
		assertNotNull(this.employee.getId());
		assertNotNull(this.department);
		assertNotNull(this.department.getId());
		assertNotNull(this.jobTitle);
		assertNotNull(this.jobTitle.getId());
	}
	
	public void createManager() {
		this.manager = Entities.manager();
		this.employeeDao.save(this.manager);
	}
	
	@Test
	public void createdManager(){
		createManager();
		
		assertNotNull(this.manager);
		assertNotNull(this.manager.getId());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void duplicateEmail() throws Exception{
		Employee empl = Entities.employee();
		empl.setEmail(this.employee.getEmail());
		this.employeeDao.save(empl);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void duplicateSkype() throws Exception{
		Employee empl = Entities.employee();
		empl.setSkypeName(this.employee.getSkypeName());
		this.employeeDao.save(empl);
	}
	
	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.employeeDao.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByDepartment() throws Exception {
		List<Employee> emps = this.employeeDao.findByDepartment(this.employee.getDepartment());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByDepartment_null() throws Exception {
		List<Employee> emps = this.employeeDao.findByDepartment(null);
		assertTrue(emps.size() == 0);
	}
	
	@Test
	public void findByJobTitleId() throws Exception {
		List<Employee> emps = this.employeeDao.findByJobTitle(this.employee.getJobTitle());
		assertNotNull(emps);
		assertEquals(1, emps.size()); //should be only one since only one employee record
	}
	
	@Test
	public void findByJobTitle_null() throws Exception {
		//TODO write find by job title with id of null
	}

	@Test
	public void findByEmail() throws Exception {
		Employee emp = this.employeeDao.findByEmail(this.employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByEmail_null() throws Exception {
		Employee emp = this.employeeDao.findByEmail(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByEmailTest_XXX() throws Exception {
		Employee emp = this.employeeDao.findByEmail(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById() throws Exception {
		Employee emp = this.employeeDao.findById(this.employee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findById_Empty() throws Exception {
		Employee empl = this.employeeDao.findById(NOT_PRESENT_ID);
		assertNull(empl);
	}
	
	@Test
	public void findById_null() throws Exception {
		Employee emp = this.employeeDao.findById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById_XXX() throws Exception {
		Employee emp = this.employeeDao.findById(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByManagerId_empty() throws Exception {
		List<Employee> emps = this.employeeDao.findByManager(Entities.employee());
		assertTrue(emps.size() == 0);
	}

	@Test
	public void findByManagerId_null() throws Exception {
		List<Employee> emps = this.employeeDao.findByManager(null);
		assertTrue(emps.size() == 0);
	}
	
	@Test
	public void update() throws Exception {
		Employee empl = this.employeeDao.findByEmail(this.employee.getEmail());
		empl.setEmail(SOME_NEW_EMAIL);
		this.employeeDao.update(empl);
		
		empl = null;
		empl = this.employeeDao.findByEmail(SOME_NEW_EMAIL);
		assertNotNull(empl);
		assertEquals(SOME_NEW_EMAIL, empl.getEmail());
	}
}