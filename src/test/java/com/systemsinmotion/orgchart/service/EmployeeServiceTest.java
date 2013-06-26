package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	Employee mockEmployee = mock(Employee.class);
	IEmployeeDao mockEmployeeDao = mock(IEmployeeDao.class);
	
	ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception{
		this.listOfEmployees.add(this.mockEmployee);
		this.employeeService.setEmployeeDao(this.mockEmployeeDao);
		when(this.mockEmployeeDao.save(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		when(this.mockEmployeeDao.findAll()).thenReturn(listOfEmployees);
	}
	

	@Test
	public void storeEmployee(){
		Integer empId = this.employeeService.storeEmployee(mockEmployee);
		assertNotNull(empId);
		assertEquals(empId, Entities.EMPLOYEE_ID);
	}
	
	@Test
	public void updateEmployee(){
		
	}
	
	@Test
	public void removeEmployee(){
		
	}
	
	public void findAllEmployees(){
		
	}
}
