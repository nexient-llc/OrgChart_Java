package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.SimpleEmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
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

	@Autowired
	private EmployeeRepository mockRepository;
	
	@Autowired
	private SimpleEmployeeRepository mockSimpleEmployeeRepository;
	
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
		verify(mockRepository, atLeastOnce()).findAll();
	}
	
	@Test 
	public void findEmployeeByID() {
		Employee emp = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
		verify(mockRepository, atLeastOnce()).findById(Entities.EMPLOYEE_ID);
	}

	@Test 
	public void findEmployeeByID_null() {
		Employee emp = this.employeeService.findEmployeeByID(null);
		assertNull(emp);
		verify(mockRepository, atLeastOnce()).findById(Entities.EMPLOYEE_ID);
	}

	@Test 
	public void findEmployeeByID_xxx() {
		Employee emp = this.employeeService.findEmployeeByID(Entities.NOT_PRESENT_ID);
		assertNull(emp);
		verify(mockRepository, atLeastOnce()).findById(Entities.EMPLOYEE_ID);
	}

	@Test
	public void storeEmployee(){
		Employee emp = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
		verify(mockRepository, atLeastOnce()).save(mockEmployee);
	}

	@Test
	public void storeEmployee_isActiveNull(){
		mockEmployee.setIsActive(null);
		Employee emp = this.employeeService.storeEmployee(this.mockEmployee);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
		verify(mockRepository, atLeastOnce()).save(mockEmployee);
	}

	@Test(expected = IllegalArgumentException.class)
	public void storeEmployee_null(){
		this.employeeService.storeEmployee(null);
	}

	@Test
	public void findAllActiveEmployees() {
		List<Employee> emps = this.employeeService.findAllActiveEmployees();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
		verify(mockRepository, atLeastOnce()).findByIsActiveIsTrue();
	}
	
	@Test
	public void removeEmployee() {
		// arrange
		mockEmployee.setIsActive(true);
		
		// act
		this.employeeService.removeEmployee(mockEmployee);
		
		// assert
		verify(mockRepository, atLeastOnce()).save(mockEmployee);
		assertFalse(mockEmployee.getIsActive());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeEmployee_null() {
		this.employeeService.removeEmployee(null);
	}

	@Test
	public void removeEmployeeById() {
		mockEmployee.setIsActive(true);
		this.employeeService.removeEmployeeById(Entities.EMPLOYEE_ID);
		verify(mockRepository, atLeastOnce()).findById(Entities.EMPLOYEE_ID);
		verify(mockRepository, atLeastOnce()).save(mockEmployee);
		assertFalse(mockEmployee.getIsActive());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeEmployeeById_null() {
		this.employeeService.removeEmployeeById(null);
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
		List<Employee> emps = this.employeeService.findEmployeesByJobTitle(mockEmployee.getJobTitle());
		assertNotNull(emps);
		assertEquals(mockEmployee.getJobTitle(), emps.get(0).getJobTitle());
		verify(mockRepository, atLeastOnce()).findByJobTitle(mockEmployee.getJobTitle());
	}

	@Test 
	public void findEmployeesByDepartment() {
		List<Employee> emps = this.employeeService.findEmployeesByDepartment(mockEmployee.getDepartment());
		assertNotNull(emps);
		assertEquals(mockEmployee.getDepartment(), emps.get(0).getDepartment());
		verify(mockRepository, atLeastOnce()).findByDepartment(mockEmployee.getDepartment());
	}

	@Test
	public void findEmployeesByFirstName() {
		List<Employee> emps = this.employeeService.findEmployeesByFirstName(Entities.FIRST_NAME);
		assertNotNull(emps);
		assertEquals(Entities.FIRST_NAME, emps.get(0).getFirstName());
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCase(Entities.FIRST_NAME);
	}

	@Test 
	public void findEmployeesByLastName() {
		List<Employee> emps = this.employeeService.findEmployeesByLastName(Entities.LAST_NAME);
		assertNotNull(emps);
		assertEquals(Entities.LAST_NAME, emps.get(0).getLastName());
		verify(mockRepository, atLeastOnce()).findByLastNameContainingIgnoreCase(Entities.LAST_NAME);
	}
	
	@Test
	public void findEmployeesByNameOnlyFilter_firstNameAndlastName() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME);
	}

	@Test
	public void findEmployeesByNameOnlyFilter_firstNameFirstOnly() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(Entities.FIRST_NAME, null);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME);
	}

	@Test
	public void findEmployeesByNameOnlyFilter_lastNameFirstOnly() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(Entities.LAST_NAME, null);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME);
	}

	@Test
	public void findEmployeesByNameOnlyFilter_firstNameLastOnly() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(null, Entities.FIRST_NAME);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME);
	}

	@Test
	public void findEmployeesByNameOnlyFilter_lastNameLastOnly() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(null, Entities.LAST_NAME);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
//		verify(mockSimpleEmployeeRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME);
	}

	@Test
	public void findEmployeesByNameOnlyFilter_allNulls() {
		List<SimpleEmployee> emps = this.employeeService.findEmployeesByNameOnlyFilter(null, null);
		assertNotNull(emps);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emps.get(0).getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findAll();
	}

	@Test
	public void findEmployeesByFilter_allNulls() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, null, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByIsActiveIsTrue();
	}

	@Test
	public void findEmployeesByFilter_firstName() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME);
	}
	
	@Test
	public void findEmployeesByFilter_lastName() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME);
	}

	@Test
	public void findEmployeesByFilter_DepartmentId() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, null, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByDepartmentIdAndIsActiveIsTrue(Entities.DEPT_ID);
	}

	@Test
	public void findEmployeesByFilter_JobTitleId() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByJobTitleIdAndIsActiveIsTrue(Entities.JOB_TITLE_ID);
	}
	
	@Test
	public void findEmployeesByFilter_firstAndLastName() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME,Entities.LAST_NAME);
	}

	@Test
	public void findEmployeesByFilter_firstNameAndDepartment() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndDepartmentIdAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID);
	}

	@Test
	public void findEmployeesByFilter_firstNameAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.JOB_TITLE_ID);
	}

	@Test
	public void findEmployeesByFilter_firstNameAndDepartmentAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());				
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID);
	}

	@Test
	public void findEmployeesByFilter_lastNameAndDepartment() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());				
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndDepartmentIdAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID);
	}

	@Test
	public void findEmployeesByFilter_lastNameAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());				
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.JOB_TITLE_ID);
	}

	@Test
	public void findEmployeesByFilter_lastNameAndDepartmentAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID);
	}

	@Test
	public void findEmployeesByFilter_DepartmentAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(null, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());				
		verify(mockRepository, atLeastOnce()).findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.DEPT_ID, Entities.JOB_TITLE_ID);
	}
	
	@Test
	public void findEmployeesByFilter_firstAndLastNameAndDepartment() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID);
	}

	@Test
	public void findEmployeesByFilter_firstAndLastNameAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());								
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.JOB_TITLE_ID);
	}

	@Test
	public void findEmployeesByFilter_firstAndLastNameAndDepartmentAndJobTitle() {
		List<Employee> emps = this.employeeService.findEmployeesByFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());								
		verify(mockRepository, atLeastOnce()).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
	}

	
	@Test
	public void findSimpleEmployeeById() {
		SimpleEmployee emp = this.employeeService.findSimpleEmployeeById(Entities.SIMPLE_EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.SIMPLE_EMPLOYEE_ID, emp.getId());
		verify(mockSimpleEmployeeRepository, atLeastOnce()).findById(Entities.SIMPLE_EMPLOYEE_ID);
	}

	@Test
	public void findSimpleEmployeeById_null() {
		Employee emp = this.employeeService.findEmployeeByID(null);
		assertNull(emp);
	}

	@Test
	public void findSimpleEmployeeById_xxx() {
		Employee emp = this.employeeService.findEmployeeByID(Entities.NOT_PRESENT_ID);
		assertNull(emp);
	}
	
	@Test
	public void findEmployeesByCriteriaFilter_allNulls() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, null, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstName() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, null, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_lastName() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, Entities.LAST_NAME, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstAndLastName() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_departmentId() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, null, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstNameAndDepartmentId() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_lastNameAndDepartmentId() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstAndLastNameAndDepartmentId() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, null);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_jobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstNameAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, null, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_lastNameAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstAndLastNameAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_departmentIdAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstNameAndDepartmentIdAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_lastNameAndDepartmentIdAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(null, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}

	@Test
	public void findEmployeesByCriteriaFilter_firstAndLastNameAndDepartmentIdAndJobTitle() {
		List<Employee> emps = employeeService.findEmployeesByCriteriaFilter(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}
	
	@Test
	public void findEmployeeBySkype() {
		List<Employee> emps = employeeService.findEmployeeBySkype(Entities.SKYPE_NAME);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}
	
	@Test
	public void findEmployeeByEmail() {
		List<Employee> emps = employeeService.findEmployeeByEmail(Entities.EMAIL);
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
	}
	
	@Test
	public void findAllActiveEmployees_page() {
		Page<Employee> emps = employeeService.findAllActiveEmployees(new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.ASC, "name").ignoreCase())));
		assertNotNull(emps);
		assertEquals(Entities.EMPLOYEE_ID, emps.getContent().get(0).getId());
	}
}

