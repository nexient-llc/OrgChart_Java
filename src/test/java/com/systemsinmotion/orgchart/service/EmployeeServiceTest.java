package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Employee mockEmployee;
	
	
	
	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test 
	public void findEmployeeByID() {
		Employee emp = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void storeEmployee(){
		Employee emp = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}
	
	@Test
	public void findByEmail() {
		Employee emp = this.employeeService.findByEmail(Entities.EMAIL);
		assertNotNull(emp);
	}
	
	@Test
	public void removeEmployee() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		Employee emp = emps.get(0);
		emp.setIsActive(true);
		
		this.employeeService.removeEmployee(emp.getId());
		
		emp = employeeService.findEmployeeByID(emp.getId());
		
		assertTrue(emp.getIsActive() == false);
		
	}
	
	@Test
	public void getEmployeeSuggestions() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions(Entities.FIRST_NAME + " " + Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findAllEmployeesByCriteria() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME + " " + Entities.LAST_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
}
