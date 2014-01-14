package com.systemsinmotion.orgchart.service;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	
	EmployeeRepository mockRepo = mock(EmployeeRepository.class);
	Employee mockEmp = mock(Employee.class);
	
	List<Employee> listOfEmployees = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception{
		when(mockEmp.getId()).thenReturn(Entities.EMPLOYEE_ID);
		when(mockEmp.getEmail()).thenReturn(Entities.EMAIL);
		when(mockEmp.getFirstName()).thenReturn(Entities.FIRST_NAME);
		when(mockEmp.getLastName()).thenReturn(Entities.LAST_NAME);
		listOfEmployees.add(mockEmp);
		when(mockRepo.findAll()).thenReturn(listOfEmployees);
		when(mockRepo.findById(Entities.EMPLOYEE_ID)).thenReturn(mockEmp);
		when(mockRepo.findByEmail(Entities.EMAIL)).thenReturn(mockEmp);
		when(mockRepo.findByDepartmentId(Entities.DEPT_ID)).thenReturn(listOfEmployees);
		when(mockRepo.findByManagerId(Entities.MANAGER_ID)).thenReturn(listOfEmployees);
		when(mockRepo.save(mockEmp)).thenReturn(mockEmp);
		when(mockRepo.findByFirstNameAndLastName(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(mockEmp);
		employeeService.setRepository(mockRepo);
	}
	
	@Test
	public void storeEmployee() throws Exception{
		Employee emp = employeeService.storeEmployee(this.mockEmp);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findEmployeeById() throws Exception{
		Employee emp = employeeService.findEmployeeById(this.mockEmp.getId());
		assertNotNull(emp);
		assertEquals(this.mockEmp.getId(), emp.getId());
	}
	
	@Test
	public void findEmployeeByEmail() throws Exception{
		Employee emp = employeeService.findEmployeeByEmail(this.mockEmp.getEmail());
		assertNotNull(emp);
		assertEquals(this.mockEmp.getEmail(), emp.getEmail());
	}
	
	@Test
	public void findEmployeesByManagerId() throws Exception{
		List<Employee> emps = employeeService.findEmployeesByManagerId(Entities.MANAGER_ID);
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findEmployeesByDepartmentId() throws Exception{
		List<Employee> emps = employeeService.findEmployeesByDepartmentId(Entities.DEPT_ID);
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findAllEmployees() throws Exception{
		List<Employee> emps = employeeService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}
	
	@Test
	public void findEmployeeByFirstAndLast() throws Exception{
		Employee emp = employeeService.findEmployeeByFirstAndLastName(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.FIRST_NAME, emp.getFirstName());
		assertEquals(Entities.LAST_NAME, emp.getLastName());
	}
}
