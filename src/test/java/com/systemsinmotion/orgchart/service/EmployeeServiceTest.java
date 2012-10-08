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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
    
    	EmployeeDAO mockEmployeeDAO = mock(EmployeeDAO.class);
    	Employee mockEmployee = mock(Employee.class);
    	
    	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();
	
	@Before
	public void before(){
	   when(mockEmployee.getEmployeeId()).thenReturn(TestObject.EMPLOYEE_ID);
	   listOfFoundEmployees.add(mockEmployee);
	   when(mockEmployeeDAO.findAll()).thenReturn(listOfFoundEmployees);
	   when(mockEmployeeDAO.findById(TestObject.EMPLOYEE_ID)).thenReturn(mockEmployee);
	   when(mockEmployeeDAO.save(mockEmployee)).thenReturn(TestObject.EMPLOYEE_ID);
	   employeeService.setEmployeeDAO(mockEmployeeDAO);  
	}

	@Test
	@Rollback
	public void findEmployees() {
		assertNotNull(employeeService.findAllEmployees());
	}
	
	@Test
	@Rollback
	public void findByID() {
	    assertNotNull(employeeService.findEmployeeByID(TestObject.EMPLOYEE_ID));
	    assertEquals(TestObject.EMPLOYEE_ID, employeeService.findEmployeeByID(TestObject.EMPLOYEE_ID).getEmployeeId());
	}
	
	@Test
	@Rollback
	public void shouldCallSaveMethodinEmployeeDAO() {
	    assertNotNull(employeeService.storeEmployee(mockEmployee));
	    assertEquals(TestObject.EMPLOYEE_ID, employeeService.findEmployeeByID(TestObject.EMPLOYEE_ID).getEmployeeId());
	}	
}
