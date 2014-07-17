package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Employee mockEmployee;
	
	@Autowired
	private SimpleEmployee mockSimpleEmployee;

	@Before
	public void resetIsActive() {
		mockEmployee.setIsActive(true);
		mockSimpleEmployee.setIsActive(true);
	}

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
	public void findAllActiveEmployees() {
		List<Employee> emps = this.employeeService.findAllActiveEmployees();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@Test
	public void removeEmployee() {
		assertTrue(mockEmployee.getIsActive());
		this.employeeService.removeEmployee(mockEmployee);
		assertFalse(mockEmployee.getIsActive());
	}

	@Test
	public void removeEmployeeById() {
		assertTrue(mockEmployee.getIsActive());
		this.employeeService.removeEmployeeById(Entities.EMPLOYEE_ID);
		assertFalse(mockEmployee.getIsActive());
	}
	
	@Test
	public void setAndGetRepository() {
		EmployeeRepository repo = this.employeeService.getRepository();
		assertNotNull(repo);
		this.employeeService.setRepository(null);
		assertNull(this.employeeService.getRepository());
		this.employeeService.setRepository(repo);
	}

	@Test 
	public void findEmployeesByJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByJobTitle(mockEmployee.getJobTitle());
		assertNotNull(emp);
		assertEquals(mockEmployee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test 
	public void findEmployeesByDepartment() {
		List<Employee> emp = this.employeeService.findEmployeesByDepartment(mockEmployee.getDepartment());
		assertNotNull(emp);
		assertEquals(mockEmployee.getDepartment(), emp.get(0).getDepartment());
	}

	@Test 
	public void findEmployeesByFirstName() {
		List<Employee> emp = this.employeeService.findEmployeesByFirstName(Entities.FIRST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.FIRST_NAME, emp.get(0).getFirstName());
	}

	@Test 
	public void findEmployeesByLastName() {
		List<Employee> emp = this.employeeService.findEmployeesByLastName(Entities.LAST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.LAST_NAME, emp.get(0).getLastName());
	}
	
	@Test
	public void findEmployeesByNameOnlyFilter_firstNameAndlastName() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByNameOnlyFilter_firstNameFirstOnly() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(Entities.FIRST_NAME, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByNameOnlyFilter_lastNameFirstOnly() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(Entities.LAST_NAME, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByNameOnlyFilter_firstNameLastOnly() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(null, Entities.FIRST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByNameOnlyFilter_lastNameLastOnly() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(null, Entities.LAST_NAME);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByNameOnlyFilter_allNulls() {
		List<SimpleEmployee> emp = this.employeeService.findEmployeesByNameOnlyFilter(null, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());
	}

	@Test
	public void findEmployeesByFilter_allNulls() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, null, null, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());		
	}

	@Test
	public void findEmployeesByFilter_firstName() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, null, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());		
	}
	
	@Test
	public void findEmployeesByFilter_lastName() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, null, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());		
	}

	@Test
	public void findEmployeesByFilter_DepartmentId() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, null, Entities.DEPT_ID, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());		
	}

	@Test
	public void findEmployeesByFilter_JobTitleId() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());		
	}
	
	@Test
	public void findEmployeesByFilter_firstAndLastName() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_firstNameAndDepartment() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_firstNameAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_firstNameAndDepartmentAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_lastNameAndDepartment() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_lastNameAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_lastNameAndDepartmentAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_DepartmentAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(null, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}
	
	@Test
	public void findEmployeesByFilter_firstAndLastNameAndDepartment() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_firstAndLastNameAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

	@Test
	public void findEmployeesByFilter_firstAndLastNameAndDepartmentAndJobTitle() {
		List<Employee> emp = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.get(0).getId());				
	}

}

