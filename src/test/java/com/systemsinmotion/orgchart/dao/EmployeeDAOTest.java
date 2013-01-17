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
	public void findByAll() throws Exception 
	{
		List<Employee> emps = employeeDAO.findAll();
		assertThat(emps, describedAs("List of employee objects", is(not(nullValue()))));
		assertThat(emps, hasSize(greaterThan(0)));
		
	}

	@Test
	@Rollback
	public void findByEmployeeIDTest_noMatchFound() throws Exception 
	{
		
		Employee emp = employeeDAO.findByEmployeeID(NOT_PRESENT_ID); 
		assertThat(emp, describedAs("null", is(nullValue()))); 
		
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
	public void findByFirstNameTest() throws Exception
	{
		List<Employee> emps = employeeDAO.findByFirstName(employee.getFirstName());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, hasSize(greaterThan(0)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, contains(instanceOf(Employee.class)));
		
		Employee emp = emps.get(0);
		assertThat(emp.getFirstName(), is(TestObject.FIRST_NAME));
		assertThat(emp.getLastName(), is(TestObject.LAST_NAME));
		assertThat(emp.getEmail(), is(TestObject.EMAIL));
		
	}
	
	@Test
	@Rollback
	public void findByFirstNameTest_noMatchFound() throws Exception
	{
		List<Employee> emp = employeeDAO.findByFirstName(NOT_PRESENT_VALUE);
		assertThat(emp, is(instanceOf(List.class)));
		assertThat(emp, hasSize(is(0)));
		assertThat(emp, is(not(nullValue())));
	}
	
	@Test
	@Rollback
	public void findByLastNameTest() throws Exception
	{
		List<Employee> emps = employeeDAO.findByLastName(employee.getLastName());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, hasSize(greaterThan(0)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, contains(instanceOf(Employee.class)));
		
		Employee emp = emps.get(0);
		assertThat(emp.getFirstName(), is(TestObject.FIRST_NAME));
		assertThat(emp.getLastName(), is(TestObject.LAST_NAME));
		assertThat(emp.getEmail(), is(TestObject.EMAIL));
		
	}
	
	@Test
	@Rollback
	public void findByLastNameTest_noMatchFound() throws Exception
	{
		List<Employee> emp = employeeDAO.findByLastName(NOT_PRESENT_VALUE);
		assertThat(emp, is(instanceOf(List.class)));
		assertThat(emp, hasSize(is(0)));
		assertThat(emp, is(not(nullValue())));
	}
	
	@Test
	@Rollback
	public void findByEmailTest() throws Exception
	{
		List<Employee> emps = employeeDAO.findByEmail(employee.getEmail());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, hasSize(greaterThan(0)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, contains(instanceOf(Employee.class)));
		
		Employee emp = emps.get(0);
		assertThat(emp.getFirstName(), is(TestObject.FIRST_NAME));
		assertThat(emp.getLastName(), is(TestObject.LAST_NAME));
		assertThat(emp.getEmail(), is(TestObject.EMAIL));
	}
	
	@Test
	@Rollback
	public void findByEmailTest_noMatchFound() throws Exception
	{
		List<Employee> emp = employeeDAO.findByEmail(NOT_PRESENT_VALUE);
		assertThat(emp, is(instanceOf(List.class)));
		assertThat(emp, hasSize(is(0)));
		assertThat(emp, is(not(nullValue())));
	}
	
	@Test
	@Rollback
	public void findByDepartmentTest() throws Exception 
	{
		List<Employee> emps = employeeDAO.findByDepartment(employee.getDept());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, hasSize(greaterThan(0)));
		
		Employee emp = emps.get(0);
		assertThat(emp.getFirstName(), is(TestObject.FIRST_NAME));
		assertThat(emp.getLastName(), is(TestObject.LAST_NAME));
		assertThat(emp.getEmail(), is(TestObject.EMAIL));
	}
	
	@Test
	@Rollback
	public void findByDepartmentTest_noMatchFound() throws Exception 
	{
		List<Employee> emps = employeeDAO.findByDepartment(new Department());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, hasSize(is(0)));
		
	}
	
	@Test
	@Rollback
	public void findByManagerIDTest() throws Exception 
	{
	
		createManager();
		employee.setManager(manager);
		employeeDAO.update(employee);

		List<Employee> emps = employeeDAO.findByManager(employee.getManager());
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, hasSize(greaterThan(0)));
		
		Employee emp = emps.get(0);
		assertThat(emp.getFirstName(), is(TestObject.FIRST_NAME));
		assertThat(emp.getLastName(), is(TestObject.LAST_NAME));
		assertThat(emp.getEmail(), is(TestObject.EMAIL));
		
	}
	
	@Test
	@Rollback
	public void findByManagerIDTest_noMatchFound() throws Exception 
	{

		List<Employee> emps = employeeDAO.findByManager(employee);
		assertThat(emps, is(instanceOf(List.class)));
		assertThat(emps, is(not(nullValue())));
		assertThat(emps, hasSize(is(0)));
		
	}

	private void createManager() {
		manager = TestObject.manager();
		employeeDAO.save(manager);
	}
	
}
