package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

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
import com.systemsinmotion.orgchart.entity.JobTitle;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	
	private ArrayList<Employee> listOfFoundEmps = new ArrayList<Employee>();
	
	EmployeeDAO mockEmpDAO = mock(EmployeeDAO.class);
	Employee mockEmp = mock(Employee.class);
	
	Department mockDept = mock(Department.class);
	JobTitle mockJT = mock(JobTitle.class);
	
	@Before
	public void before() throws Exception 
	{
		
		listOfFoundEmps.add(mockEmp);
		when(mockEmp.getEmpID()).thenReturn(TestObject.EMPLOYEE_ID);
		when(mockEmpDAO.save(mockEmp)).thenReturn(TestObject.EMPLOYEE_ID);
		when(mockEmpDAO.findAll()).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByEmployeeID(TestObject.EMPLOYEE_ID)).thenReturn(mockEmp);
		when(mockEmpDAO.findByFirstName(TestObject.FIRST_NAME)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByLastName(TestObject.LAST_NAME)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByNameCombination(TestObject.FIRST_NAME, TestObject.LAST_NAME)).thenReturn(listOfFoundEmps);
		
		when(mockEmpDAO.findByEmail(TestObject.EMAIL)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByDepartment(mockDept)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByJobTitle(mockJT)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByManager(mockEmp)).thenReturn(listOfFoundEmps);
		employeeService.setEmployeeDAO(mockEmpDAO);
		
	}
	
	@Test
	@Rollback
	public void shouldSaveAndReturnNewEmployeeID()
	{
		
		Integer newEmp = employeeService.createEmployeeRecord(mockEmp);
		assertThat(newEmp, describedAs("Not null", is(not(nullValue()))));
		assertThat(newEmp, describedAs("5", is(TestObject.EMPLOYEE_ID)));
		
	}
	
	@Test
	@Rollback
	public void shouldReturnAllEmployeeRecords()
	{
		
		List<Employee> empsList = employeeService.findAllEmployees();
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));

	}
	
	@Test
	@Rollback
	public void shouldReturnEmployeeByID()
	{
		
		Employee emp = employeeService.findEmployeeByID(TestObject.EMPLOYEE_ID);
		assertThat(emp, describedAs("Not null", is(not(nullValue()))));
		assertThat(emp, describedAs("Instance of employee class", is(instanceOf(Employee.class))));
		
	}
	
	@Test
	@Rollback
	public void shouldReturnEmployeesByFirstName()
	{
		String fName = TestObject.FIRST_NAME;
		String lName = "0";
		
		List<Employee> empsList = employeeService.findByEmployeeName(fName , lName);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	@Test
	@Rollback
	public void shouldReturnEmployeesByLastName()
	{
		String fName = "0";
		String lName = TestObject.LAST_NAME;
		
		List<Employee> empsList = employeeService.findByEmployeeName(fName , lName);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	@Test
	@Rollback
	public void shouldReturnEmployeesByNameCombination()
	{
		String fName = TestObject.FIRST_NAME;
		String lName = TestObject.LAST_NAME;
		
		List<Employee> empsList = employeeService.findByEmployeeName(fName , lName);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	public void shouldReturnEmployeesByEmail()
	{
		
		List<Employee> empsList = employeeService.findEmployeeByEmail(TestObject.EMAIL);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	public void shouldReturnEmployeesByDepartment()
	{
		
		List<Employee> empsList = employeeService.findEmployeesByDepartment(mockDept);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	public void shouldReturnEmployeesByJobTitle()
	{
		
		List<Employee> empsList = employeeService.findEmployeesByJobTitle(mockJT);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
	
	public void shouldReturnEmployeesByManager()
	{
		
		List<Employee> empsList = employeeService.findEmployeesByManager(mockEmp);
		assertThat(empsList, describedAs("Not null", is(not(nullValue()))));
		assertThat(empsList, describedAs("Instance of a list object", is(instanceOf(List.class))));
		assertThat(empsList, describedAs("Size is greater than 0", hasSize(is(greaterThan(0)))));
		
	}
}
