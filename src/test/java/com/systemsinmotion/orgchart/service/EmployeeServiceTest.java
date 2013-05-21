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
import com.systemsinmotion.orgchart.dao.IEmployeeDAO;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	IEmployeeDAO mockEmployeeDAO = mock(IEmployeeDAO.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();

	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeDAO.findAll()).thenReturn(this.listOfFoundEmployees);
		when(this.mockEmployeeDAO.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDAO.save(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		this.employeeService.setEmployeeDAO(this.mockEmployeeDAO);
	}

	@Test
	public void findAllDepartments() {
		List<Employee> employees = this.employeeService.findAllEmployees();
		assertNotNull(employees);
		assertEquals(1, employees.size());
	}

	@Test
	public void findDepartmentByID() {
		Employee employee = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(employee);
		assertEquals(Entities.EMPLOYEE_ID, employee.getId());
	}

	@Test
	public void storeDepartment() {
		Integer employeeId = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(employeeId);
		assertEquals(Entities.EMPLOYEE_ID, employeeId);
	}

}
