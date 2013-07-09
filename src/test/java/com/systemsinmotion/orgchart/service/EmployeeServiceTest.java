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
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest 
{
	
	@Autowired
	EmployeeService employeeService;
	
	EmployeeDao mockEmployeeDao= mock(EmployeeDao.class);
	Employee mockEmployee= mock(Employee.class);
	
	private ArrayList<Employee> foundEmployees=new ArrayList<Employee>();
	
	@Before
	public void before()
	{	
		when (this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.foundEmployees.add(mockEmployee);
		when(this.mockEmployeeDao.queryAll()).thenReturn(this.foundEmployees);
		when(this.mockEmployeeDao.queryById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDao.create(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		this.employeeService.setEmployeeDao(mockEmployeeDao);
		
	}

	@Test
	public void findAllEmployeesTest() 
	{
		List<Employee> emps = this.employeeService.findAll();
		assertNotNull(emps);
		assertEquals(1, emps.size());		
	}
	
	@Test
	public void findEmployeeByIdTest()
	{
		Employee emp=this.employeeService.findEmployeeById(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(emp.getId(), Entities.EMPLOYEE_ID);							
	}
		
	@Test
	public void storeEmployee() 	
	{		
		Integer localEmpId=this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(localEmpId);
		assertEquals(localEmpId, Entities.EMPLOYEE_ID);		
	}
	
	

}
