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

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeDaoTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private Employee manager;

//	@Autowired
//	IEmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;

//	@After
//	public void after() {
//		this.employeeDao.delete(this.employee);
//		this.departmentDao.delete(this.department);
//
//		if (null != this.manager) {
//			this.employeeDao.delete(this.manager);
//		}
//	}

//	@Before
//	public void before() throws Exception {
//		this.department = Entities.department();
//		this.departmentDao.save(this.department);
//
//		this.employee = Entities.employee();
//		this.employee.setDepartment(this.department);
//		this.employee.setId(this.employeeDao.save(this.employee));
//	}

	@Test
	public void testInstantiation() {
		assertNotNull(departmentDao);
	}
	
//	private void createManager() {
//		this.manager = Entities.manager();
//		this.employeeDao.save(this.manager);
//	}

//	@Test
//	public void findAll() throws Exception {
//		List<Employee> emps = this.employeeDao.findAll();
//		assertNotNull(emps);
//		assertTrue(0 < emps.size());
//	}

//	@Test
//	public void findByDepartment() throws Exception {
//		List<Employee> emps = this.employeeDao.findByDepartment(this.employee.getDepartment());
//		assertNotNull("Expecting a non-null list of Employees but was null", emps);
//		Employee emp = emps.get(0);
//		assertEquals(this.employee.getFirstName(), emp.getFirstName());
//		assertEquals(this.employee.getLastName(), emp.getLastName());
//		assertEquals(this.employee.getEmail(), emp.getEmail());
//	}

//	@Test
//	public void findByDepartment_null() throws Exception {
//		List<Employee> emps = this.employeeDao.findByDepartment(null);
//		assertNull("Expecting a null list of Employees but was non-null", emps);
//	}

//	@Test
//	public void findByEmail() throws Exception {
//		Employee emp = this.employeeDao.findByEmail(this.employee.getEmail());
//		assertNotNull("Expecting a non-null Employee but was null", emp);
//		assertEquals(this.employee.getFirstName(), emp.getFirstName());
//		assertEquals(this.employee.getLastName(), emp.getLastName());
//		assertEquals(this.employee.getEmail(), emp.getEmail());
//	}

//	@Test
//	public void findByEmail_null() throws Exception {
//		Employee emp = this.employeeDao.findByEmail(null);
//		assertNull("Expecting a null Employee but was non-null", emp);
//	}

//	@Test
//	public void findByEmailTest_XXX() throws Exception {
//		Employee emp = this.employeeDao.findByEmail(NOT_PRESENT_VALUE);
//		assertNull("Expecting a null Employee but was non-null", emp);
//	}

//	@Test
//	public void findById() throws Exception {
//		Employee emp = this.employeeDao.findById(this.employee.getId());
//		assertNotNull("Expecting a non-null Employee but was null", emp);
//		assertEquals(this.employee.getFirstName(), emp.getFirstName());
//		assertEquals(this.employee.getLastName(), emp.getLastName());
//		assertEquals(this.employee.getEmail(), emp.getEmail());
//	}

//	@Test
//	public void findById_null() throws Exception {
//		Employee emp = this.employeeDao.findById(null);
//		assertNull("Expecting a null Employee but was non-null", emp);
//	}

//	@Test
//	public void findById_XXX() throws Exception {
//		Employee emp = this.employeeDao.findById(NOT_PRESENT_ID);
//		assertNull("Expecting a null Employee but was non-null", emp);
//	}

//	@Test
//	public void findByManagerId() throws Exception {
//		createManager();
//
//		this.employee.setManager(this.manager);
//		this.employeeDao.update(this.employee);
//
//		List<Employee> emps = this.employeeDao.findByManager(this.employee.getManager());
//		assertNotNull("Expecting a non-null Employee but was null", emps);
//		assertTrue("Expecting at least one employee found for manager but none was found", emps.size() > 0);
//		Employee emp = emps.get(0);
//		assertEquals(this.employee.getFirstName(), emp.getFirstName());
//		assertEquals(this.employee.getLastName(), emp.getLastName());
//		assertEquals(this.employee.getEmail(), emp.getEmail());
//	}

//	@Test
//	public void findByManagerId_empty() throws Exception {
//		List<Employee> emps = this.employeeDao.findByManager(Entities.employee());
//		assertNull(emps);
//	}

//	@Test
//	public void findByManagerId_null() throws Exception {
//		List<Employee> emps = this.employeeDao.findByManager(null);
//		assertNull(emps);
//	}
}