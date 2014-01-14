package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("src/main/webapp")
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	EmployeeRepository employeeRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);
	Department department = Entities.department();
	JobTitle jobTitle = Entities.jobTitle();
	
	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();
	 
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.employeeRepo.findAll()).thenReturn(this.listOfFoundEmployees);
		when(this.employeeRepo.findOne(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.employeeRepo.save(this.mockEmployee)).thenReturn(this.mockEmployee);
		when(this.employeeRepo.findByFirstNameAndLastName(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployee.getFirstName()).thenReturn(Entities.FIRST_NAME);
		when(this.mockEmployee.getLastName()).thenReturn(Entities.LAST_NAME);
		when(this.mockEmployee.getEmail()).thenReturn(Entities.EMAIL);
		when(this.employeeRepo.findByEmail(Entities.EMAIL)).thenReturn(this.mockEmployee);
		when(this.employeeRepo.findByDepartment(department)).thenReturn(listOfFoundEmployees);
		when(this.employeeRepo.findByJobTitle(jobTitle)).thenReturn(listOfFoundEmployees);
		this.employeeService.setRepository(this.employeeRepo);
	}
	
	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findEmployeeById() {
		Employee emp = this.employeeService.findByEmployeeId(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findByEmployeeName() {
		Employee emp = this.employeeService.findByFirstNameAndLastName(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull("No matching employee found!", emp);
		assertEquals(Entities.FIRST_NAME, emp.getFirstName());
		assertEquals(Entities.LAST_NAME, emp.getLastName());
	}
	
	@Test
	public void findByJobTitle() {
		List<Employee> emps = this.employeeService.findByJobTitle(jobTitle);
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findByEmail() {
		Employee emp = this.employeeService.findByEmail(Entities.EMAIL);
		assertNotNull("No matching email found!", emp);
		assertEquals(Entities.EMAIL, emp.getEmail());
	}
	
	@Test
	public void findByDepartment() {
		List<Employee> emps = this.employeeService.findByDepartment(department);
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
