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

	private ArrayList<Employee> listOfFoundJobs = new ArrayList<Employee>();

	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundJobs.add(this.mockEmployee);
		when(this.mockEmployeeDAO.findAll()).thenReturn(this.listOfFoundJobs);
		when(this.mockEmployeeDAO.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDAO.save(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		this.employeeService.setEmployeeDao(this.mockEmployeeDAO);
	}

	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}

	@Test
	public void findEmployeeByID() {
		Employee emp = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
	}

	@Test
	public void storeEmployee() {
		Integer empId = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(empId);
		assertEquals(Entities.EMPLOYEE_ID, empId);
	}

}
