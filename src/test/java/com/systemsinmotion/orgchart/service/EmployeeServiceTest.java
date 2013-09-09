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
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	EmployeeDao mockEmployeeDAO = mock(EmployeeDao.class);
	Employee mockEmployee = mock(Employee.class);
	
	private ArrayList<Employee> listOfFoundEmpls = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception{
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmpls.add(this.mockEmployee);
		when(this.mockEmployeeDAO.findAll()).thenReturn(this.listOfFoundEmpls);
		when(this.mockEmployeeDAO.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDAO.save(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		this.employeeService.setEmployeeDAO(this.mockEmployeeDAO);
	}
	
	@Test
	public void findAllEmployees(){
		List<Employee> empls = this.employeeService.findAllEmployees();
		assertNotNull(empls);
		assertEquals(1, empls.size());
	}
	
	@Test
	public void findByEmployeeByID(){
		Employee empl = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empl);
		assertEquals(Entities.EMPLOYEE_ID, empl.getId());
	}
	
	@Test
	public void storeEmployee(){
		Integer emplId = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(emplId);
		assertEquals(Entities.EMPLOYEE_ID, emplId);
	}
}
