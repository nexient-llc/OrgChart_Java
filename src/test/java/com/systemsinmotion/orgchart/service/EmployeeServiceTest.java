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
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);
	Employee mockManager = mock(Employee.class);
	
	Department mockDepartment = mock(Department.class);
	JobTitle mockJobTitle = mock(JobTitle.class);
	
	
	private ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
	private ArrayList<Employee> listOfEmployeeByManager = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		mockEmployee.setManager(mockManager);
		mockManager.setIsManager(true);
		this.listOfEmployees.add(this.mockEmployee);
		this.listOfEmployees.add(this.mockManager);
		this.listOfEmployeeByManager.add(this.mockEmployee);
		when(this.mockEmployeeRepository.findAll()).thenReturn(this.listOfEmployees);
		when(this.mockEmployeeRepository.findById(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(this.mockEmployeeRepository.findById(Entities.MANAGER_ID)).thenReturn(mockManager);
		when(this.mockEmployeeRepository.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		//when(this.mockEmployeeRepository.findOne(Entities.MANAGER_ID)).thenReturn(mockManager);
		when(this.mockEmployeeRepository.findByDepartmentId(Entities.DEPT_ID)).thenReturn(listOfEmployees);
		when(this.mockEmployeeRepository.saveAndFlush(this.mockEmployee)).thenReturn(this.mockEmployee);
		//when(this.mockEmployeeRepository.save(this.mockManager)).thenReturn(this.mockManager);
		when(this.mockEmployeeRepository.findByEmail(Entities.EMAIL)).thenReturn(mockEmployee);
		when(this.mockEmployeeRepository.findByManagerId(Entities.MANAGER_ID)).thenReturn(listOfEmployeeByManager);
		this.employeeService.setRepository(this.mockEmployeeRepository);
	}
	
	@Test
	public void findAllEmployees(){
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(2, emps.size());
	}
	
	@Test
	public void findEmployeesByDepartment(){
		List<Employee> emps = this.employeeService.findEmployeesByDepartment(mockDepartment);
		assertNotNull(emps);
		assertEquals(2, emps.size());
	}
	
	@Test
	public void findEmployeeByEmail(){
		Employee emp = this.employeeService.findEmployeeByEmail(Entities.EMAIL);
		assertNotNull(emp);
	}
	
	@Test
	public void findEmployeeByID(){
		Employee emp = this.employeeService.findEmployeeById(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
	}
	
	@Test
	public void findByManagerId(){
		List<Employee> emps = this.employeeService.findEmployeesByManagerId(Entities.MANAGER_ID);
		assertNotNull(emps);
		assertEquals(emps.size(), 1);
	}
	
	@Test
	public void storeEmployee(){
		Employee emp = this.employeeService.storeEmployee(mockEmployee);
		assertNotNull(emp);
	}
	
	@Test
	public void findEmployeeByIDNullTest()
	{
		Employee emp = this.employeeService.findEmployeeById(null);
		assertNull(emp);
	}
}


