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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
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
	
//	@Autowired
//	DepartmentService departmentService;
//
//	DepartmentRepository mockDepartmentRepo = mock(DepartmentRepository.class);
	Department mockDepartment = mock(Department.class);
	
	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();
	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();
	
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getFirstName()).thenReturn(Entities.FIRST_NAME);
		when(this.mockEmployee.getLastName()).thenReturn(Entities.LAST_NAME);
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		when(this.mockEmployee.getEmail()).thenReturn(Entities.EMAIL);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeRepository.findAll()).thenReturn(this.listOfFoundEmployees);
		when(this.mockEmployeeRepository.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.save(this.mockEmployee)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.findByFirstNameAndLastName(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.findByFirstName(Entities.FIRST_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.findByLastName(Entities.LAST_NAME)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.findByEmail(Entities.EMAIL)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.findByDepartment(this.mockDepartment)).thenReturn(this.listOfFoundEmployees);
		this.employeeService.setRepository(this.mockEmployeeRepository);
		
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
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
		Integer employeeId = this.employeeService.storeEmployee(this.mockEmployee).getId();
		assertNotNull(employeeId);
		assertEquals(Entities.EMPLOYEE_ID, employeeId);
	}
	
	@Test
	public void findEmployeeByName(){
		Employee employee = this.employeeService.findEmployeeByName(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(employee);
		assertEquals(Entities.FIRST_NAME, employee.getFirstName());
		assertEquals(Entities.LAST_NAME, employee.getLastName());
	}
	
	@Test
	public void findEmployeeByFirstName(){
		Employee employee = this.employeeService.findEmployeeByFirstName(Entities.FIRST_NAME);
		assertNotNull(employee);
		assertEquals(Entities.FIRST_NAME, employee.getFirstName());
	}
	
	@Test
	public void findEmployeeByLastName(){
		Employee employee = this.employeeService.findEmployeeByLastName(Entities.LAST_NAME);
		assertNotNull(employee);
		assertEquals(Entities.LAST_NAME, employee.getLastName());
	}
	
	@Test
	public void findEmployeeByDepartment() {
		List<Employee> emps = this.employeeService.findByDepartment(this.mockDepartment);
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(Entities.FIRST_NAME, emp.getFirstName());
		assertEquals(Entities.LAST_NAME, emp.getLastName());
	}
	
	@Test
	public void findEmployeeByEmail() {
		Employee emp = this.employeeService.findByEmail(Entities.EMAIL);
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(Entities.FIRST_NAME, emp.getFirstName());
		assertEquals(Entities.LAST_NAME, emp.getLastName());
		assertEquals(Entities.EMAIL, emp.getEmail());
	}
	
	
}
