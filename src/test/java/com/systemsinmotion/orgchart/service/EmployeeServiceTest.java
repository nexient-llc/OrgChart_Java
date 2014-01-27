package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	
	EmployeeRepository mockEmployeeRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundEmps = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmps.add(this.mockEmployee);
		when(this.mockEmployeeRepo.findAll()).thenReturn(this.listOfFoundEmps);
		when(this.mockEmployeeRepo.findOne(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		/*when(this.mockEmployeeRepo.findEmployeeByFirstName(Entities.FIRST_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepo.findEmployeeByLastName(Entities.LAST_NAME)).thenReturn(this.mockEmployee);*/
		when(this.mockEmployeeRepo.findEmployeeByEmail(Entities.EMAIL)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepo.findEmployeeBySkypeName(Entities.SKYPE_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepo.findEmployeesByIsActiveTrue()).thenReturn(this.listOfFoundEmps);
		when(this.mockEmployeeRepo.save(this.mockEmployee)).thenReturn(this.mockEmployee);
		this.employeeService.setRepository(this.mockEmployeeRepo);
	}
	
	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findEmployeeById() {
		Employee emp = this.employeeService.findEmployeeById(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	/*
	@Test
	public void findEmployeeByFirstName() {
		Employee emp = this.employeeService.findEmployeeByFirstName(Entities.FIRST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findEmployeeByLastName() {
		Employee emp = this.employeeService.findEmployeeByLastName(Entities.LAST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	*/
	@Test
	public void findEmployeeByEmail() {
		Employee emp = this.employeeService.findEmployeeByEmail(Entities.EMAIL);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findEmployeeBySkypeName() {
		Employee emp = this.employeeService.findEmployeeBySkypeName(Entities.SKYPE_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findEmployeesByIsActiveTrue() {
		List<Employee> emps = this.employeeService.findEmployeesByIsActiveTrue();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void storeEmployee() {
		Integer empId = this.employeeService.storeEmployee(this.mockEmployee).getId();
		assertNotNull(empId);
		assertEquals(Entities.EMPLOYEE_ID, empId);
	}
}