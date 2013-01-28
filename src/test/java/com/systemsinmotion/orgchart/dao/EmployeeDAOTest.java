package com.systemsinmotion.orgchart.dao;

import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeDAOTest {
	
	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private Employee manager;

	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	DepartmentDAO departmentDAO;
	
	@Before
	public void before() throws Exception 
	{
		department = TestObject.department(); 
		departmentDAO.save(department); 
		
		employee = TestObject.employee(); 
		employee.setDept(department); 
		employee.setEmpID(employeeDAO.save(employee));  
	}

	@After
	public void after() 
	{
		employeeDAO.delete(employee);
		departmentDAO.delete(department);

		if (null != manager) {
			employeeDAO.delete(manager);
		}
	}
	
	@Test
	@Rollback
	public void findByAllTest() throws Exception 
	{
		
		List<Employee> emps = employeeDAO.findAll();
		assertThat(emps, describedAs("List of objects", is(not(nullValue()))));
		assertThat(emps, describedAs("List size greater than 0", hasSize(greaterThan(0))));
		
	}
	
	@Test
	@Rollback
	public void findByEmployeeIDTest_noMatchFound() throws Exception 
	{
		
		Employee emp = employeeDAO.findByEmployeeID(NOT_PRESENT_ID); 
		assertThat(emp, describedAs("Null", is(nullValue()))); 
		
	}
	
	@Test
	@Rollback
	public void findByEmployeeIDTest() throws Exception 
	{
		
		Employee emp = employeeDAO.findByEmployeeID(employee.getEmpID());
		assertThat(emp, describedAs("Not Null", is(not(nullValue())))); 
		assertThat(emp, describedAs("Employee Object", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	@Test
	@Rollback
	public void findByFirstNameTest_noMatchFound() throws Exception
	{
		
		List<Employee> emp = employeeDAO.findByFirstName(NOT_PRESENT_VALUE);
		assertThat(emp, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emp, describedAs("List size is 0", hasSize(is(0))));
		assertThat(emp, describedAs("Not null", is(not(nullValue()))));
		
	}
	
	@Test
	@Rollback
	public void findByFirstNameTest() throws Exception
	{
		
		List<Employee> emps = employeeDAO.findByFirstName(employee.getFirstName());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("List size greater than 0", hasSize(greaterThan(0))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee class", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	@Test
	@Rollback
	public void findByLastNameTest_noMatchFound() throws Exception
	{
		
		List<Employee> emp = employeeDAO.findByLastName(NOT_PRESENT_VALUE);
		assertThat(emp, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emp, describedAs("List size is 0", hasSize(is(0))));
		assertThat(emp, describedAs("Not null", is(not(nullValue()))));
		
	}
	
	@Test
	@Rollback
	public void findByLastNameTest() throws Exception
	{
		
		List<Employee> emps = employeeDAO.findByLastName(employee.getLastName());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Size greater than 0", hasSize(greaterThan(0))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee class", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	@Test
	@Rollback
	public void findByNameCombinationTest_noMatchFound() throws Exception
	{
		
		List<Employee> emps = employeeDAO.findByNameCombination(NOT_PRESENT_VALUE, NOT_PRESENT_VALUE);
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Size is 0", hasSize(is(0))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		
	}
	
	@Test
	@Rollback
	public void findByNameCombinationTest() throws Exception
	{
		
		List<Employee> emps = employeeDAO.findByNameCombination(employee.getFirstName(), employee.getLastName());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Size greater than 0", hasSize(greaterThan(0))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee object", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	@Test
	@Rollback
	public void findByEmailTest_noMatchFound() throws Exception
	{
		
		List<Employee> emp = employeeDAO.findByEmail(NOT_PRESENT_VALUE);
		assertThat(emp, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emp, describedAs("Size is 0", hasSize(is(0))));
		assertThat(emp, describedAs("Not null", is(not(nullValue()))));
		
	}
	
	@Test
	@Rollback
	public void findByEmailTest() throws Exception
	{
		
		List<Employee> emps = employeeDAO.findByEmail(employee.getEmail());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Size greater than 0", hasSize(greaterThan(0))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee object", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	@Test
	@Rollback
	public void findByDepartmentTest_noMatchFound() throws Exception 
	{
		
		List<Employee> emps = employeeDAO.findByDepartment(new Department());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		assertThat(emps, describedAs("Size is 0", hasSize(is(0))));
		
	}
	
	@Test
	@Rollback
	public void findByDepartmentTest() throws Exception 
	{
		
		List<Employee> emps = employeeDAO.findByDepartment(employee.getDept());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		assertThat(emps, describedAs("Size greater than 0", hasSize(greaterThan(0))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee class", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}
	
	//Needs to be finished after Job Title classes have been added
//	@Test
//	@Rollback
//	public void findByJobTitleTest_noMatchFound() throws Exception
//	{
//		
//	}
//	
//	@Test
//	@Rollback
//	public void findByJobTitleTest() throws Exception
//	{
//		
//	}
	
	@Test
	@Rollback
	public void findByManagerIDTest_noMatchFound() throws Exception 
	{

		List<Employee> emps = employeeDAO.findByManager(employee);
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		assertThat(emps, describedAs("Size is 0", hasSize(is(0))));
		
	}
	
	@Test
	@Rollback
	public void findByManagerIDTest() throws Exception 
	{
	
		createManager();
		employee.setManager(manager);
		employeeDAO.update(employee);

		List<Employee> emps = employeeDAO.findByManager(employee.getManager());
		assertThat(emps, describedAs("Instance of a list", is(instanceOf(List.class))));
		assertThat(emps, describedAs("Not null", is(not(nullValue()))));
		assertThat(emps, describedAs("Size greater than 0", hasSize(greaterThan(0))));
		
		Employee emp = emps.get(0);
		assertThat(emp, describedAs("Instance of employee object", is(instanceOf(Employee.class))));
		assertThat(emp.getFirstName(), describedAs("first name", is(TestObject.FIRST_NAME)));
		assertThat(emp.getLastName(), describedAs("last name", is(TestObject.LAST_NAME)));
		assertThat(emp.getEmail(), describedAs("email", is(TestObject.EMAIL)));
		
	}

	private void createManager() 
	{
		
		manager = TestObject.manager();
		employeeDAO.save(manager);
		
	}

}
