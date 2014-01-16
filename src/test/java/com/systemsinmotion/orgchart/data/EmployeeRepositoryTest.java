package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	DepartmentRepository departmentRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.department = this.departmentRepo.saveAndFlush(department);
		
		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employee = this.employeeRepo.saveAndFlush(employee);
	}
	
    @Test
    public void testInstantiation() {
    	assertNotNull(departmentRepo);
    }
    
    private void createManager() {
        this.manager = Entities.manager();
        this.employeeRepo.saveAndFlush(this.manager);
    }
	
	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.employeeRepo.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}
	
	@Test
	public void findEmployeesByDepartment() throws Exception {
		List<Employee> emps = this.employeeRepo.findEmployeesByDepartment(this.employee.getDepartment());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getSkypeName(), emp.getSkypeName());
	}
	
	@Test
	public void findEmployeesByDepartment_null() throws Exception {
		List<Employee> emps = this.employeeRepo.findEmployeesByDepartment(null);
		// System.out.println(emps)
		assertTrue(emps.isEmpty()); // Returns an empty list which is non-null
	}
	
	@Test
	public void findEmployeeByEmail() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeByEmail(this.employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findEmployeeByEmail_null() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeByEmail(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
	@Test
	public void findEmployeeByEmailTest_XXX() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeByEmail(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
	@Test
	public void findEmployeeBySkypeName() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeBySkypeName(this.employee.getSkypeName());
		// System.out.println(this.employee.getSkypeName() + " compare " + emp.getSkypeName());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getSkypeName(), emp.getSkypeName());
	}
	
	@Test
	public void findEmployeeBySkypeName_null() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeBySkypeName(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
	@Test
	public void findEmployeeBySkypeName_XXX() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeBySkypeName(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findEmployeeById() throws Exception {
		Employee emp = this.employeeRepo.findOne(this.employee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getSkypeName(), emp.getSkypeName());
	}

	@Test
	public void findEmployeeById_null() throws Exception {
		Employee emp = this.employeeRepo.findEmployeeById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findEmployeeById_XXX() throws Exception {
		Employee emp = this.employeeRepo.findOne(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}
	
	@Test
	public void findEmployeeByManagerId() throws Exception {
		createManager();
		
		this.employee.setManager(this.manager);
		this.employeeRepo.save(this.employee);
		
		List<Employee> emps = this.employeeRepo.findEmployeesByManagerId(this.employee.getManager().getId());
		assertNotNull("Expecting a non-null Employee but was null", emps);
		assertTrue("Expecting at least one employee found for manager but none was found", emps.size() > 0);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getSkypeName(), emp.getSkypeName());
	}
	
	@Test
	public void findEmployeeByManagerId_null() throws Exception {
		List<Employee> emps = this.employeeRepo.findEmployeesByManagerId(null);
		
		// Assuming at least one person does not have a manager
		assertTrue(emps.size() > 0); 
	}
}
