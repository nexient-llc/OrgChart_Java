package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	private IEmployeeDao mockEmployeeDao = mock(IEmployeeDao.class);
	private Employee mockEmployee = mock(Employee.class);
	private Department mockDepartment = mock(Department.class);
	private JobTitle mockJobTitle = mock(JobTitle.class);
	
	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>(0);
	
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		when(this.mockEmployee.getDepartment()).thenReturn(mockDepartment);
		when(this.mockEmployee.getJobTitle()).thenReturn(mockJobTitle);
		when(this.mockEmployee.getEmail()).thenReturn(Entities.EMAIL);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeDao.findAll()).thenReturn(listOfFoundEmployees);
		when(this.mockEmployeeDao.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDao.findByDepartment(this.mockDepartment)).thenReturn(this.listOfFoundEmployees);
		when(this.mockEmployeeDao.findByJobTitle(this.mockJobTitle)).thenReturn(this.listOfFoundEmployees);
		when(this.mockEmployeeDao.findByEmail(Entities.EMAIL)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeDao.save(this.mockEmployee)).thenReturn(Entities.EMPLOYEE_ID);
		this.employeeService.setEmployeeDao(mockEmployeeDao);
	}
	
	@Test
	public void findAllEmployees() {
		List<Employee> employees = this.employeeService.findAllEmployees();
		assertNotNull(employees);
		assertEquals(1, employees.size());
	}
	
	@Test
	public void findEmployeeById() {
		Employee employee = this.employeeService.findEmployeeById(Entities.EMPLOYEE_ID);
		assertNotNull(employee);
		assertEquals(Entities.EMPLOYEE_ID, employee.getId());
	}
	
	@Test
	public void findEmployeesByDepartment() {
		List<Employee> employees = this.employeeService.findEmployeesByDepartment(this.mockDepartment);
		assertNotNull(employees);
		assertEquals(1, employees.size());
	}
	
	@Test
	public void findEmployeesByJobTitle() {
		List<Employee> employees = this.employeeService.findEmployeesByJobTitle(this.mockJobTitle);
		assertNotNull(employees);
		assertEquals(1, employees.size());
	}
	
	@Test
	public void findEmployeeByEmail() {
		Employee employee = this.employeeService.findEmployeeByEmail(Entities.EMAIL);
		assertNotNull(employee);
		assertEquals(Entities.EMAIL, employee.getEmail());
	}
	
	@Test
	public void storeEmployee() {
		Integer id = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(id);
		assertEquals(Entities.EMPLOYEE_ID, id);
	}
	
	@Test
	public void updateEmployee() {
		this.employeeService.updateEmployee(this.mockEmployee);
		Mockito.verify(this.mockEmployeeDao).update(this.mockEmployee);
	}
	
	@Test
	public void removeEmployee() {
		this.employeeService.removeEmployee(this.mockEmployee);
		Mockito.verify(this.mockEmployeeDao).delete(this.mockEmployee);
	}
}
