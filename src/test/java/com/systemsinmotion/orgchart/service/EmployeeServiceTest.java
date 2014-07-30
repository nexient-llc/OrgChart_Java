package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Employee mockEmployee;
	
	@Mock
	private Department mockDepartment;
	
	@Mock
	private Employee mockEmp;
	
	@Mock
	private EmployeeService mockEmpServ;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.employeeService.findAllActiveEmployees();
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
		this.employeeService.removeEmployee(Entities.ID);
		
	}
	
	@Test
	public void getEmployeeSuggestions_fullName() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions(Entities.FIRST_NAME + " " + Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void getEmployeeSuggestions_first() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions(Entities.FIRST_NAME);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void getEmployeeSuggestions_last() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions(Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void getEmployeeSuggestions_noName() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions("");
		assertTrue(emps ==  null);
	}
	
	@Test
	public void getEmployeeSuggestions_nameNotValid() {
		List<Employee> emps = this.employeeService.getEmployeeSuggestions(Entities.FIRST_NAME + " " + Entities.LAST_NAME + " asdfasdf" );
		assertTrue(emps == null);
	}
	
	@Test
	public void findAEmployees_AllCriteriaOneName() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_OneNameAndJob() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME, "", Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_OneNameAndDept() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME, Entities.DEPT_ID.toString(), "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findAEmployeesAllCriteria() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME + " " + Entities.LAST_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_NameAndJob() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME + " " + Entities.LAST_NAME, "", Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_NameAndDept() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME + " " + Entities.LAST_NAME, Entities.DEPT_ID.toString(), "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_Name() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME + " " + Entities.LAST_NAME, "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_OneName() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria(Entities.FIRST_NAME, "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_Dept() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria("", Entities.DEPT_ID.toString(), "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_Job() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria("", "", Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_DeptAndJob() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria("", Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void findEmployees_None() {
		List<Employee> emps = this.employeeService.findAllEmployeesByCriteria("", "", "");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
}
