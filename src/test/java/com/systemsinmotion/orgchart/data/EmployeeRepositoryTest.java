package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.*;

import java.util.List;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeRepositoryTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private JobTitle jobTitle;
	private Employee employee;
	private Employee manager;

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
	JobTitleRepository jobRepo;

//	@After
//	public void after() {
//		this.empRepo.delete(this.employee);
//		this.deptRepo.delete(this.department);
//
//		if (null != this.manager) {
//			this.empRepo.delete(this.manager);
//		}
//	}

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.jobTitle = Entities.jobTitle();
		
		this.deptRepo.saveAndFlush(this.department);
		this.jobRepo.saveAndFlush(this.jobTitle);
		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employee.setJobTitle(this.jobTitle);
		this.employee.setId(this.empRepo.save(this.employee).getId());
	}

	@Test
	public void testInstantiation() {
		assertNotNull(deptRepo);
	}
	
	private void createManager() {
		this.manager = Entities.manager();
		this.empRepo.save(this.manager);
	}

	
	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.empRepo.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	
	@Test
	public void findByDepartment() throws Exception {
		List<Employee> emps = this.empRepo.findAllByDepartmentIdAndIsActiveIsTrue(this.employee.getDepartment().getId());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}


	
	@Test
	public void findByEmail() throws Exception {
		Employee emp = this.empRepo.findByEmailIgnoreCase(this.employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByEmail_null() throws Exception {
		Employee emp = this.empRepo.findByEmailIgnoreCase(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByEmailTest_XXX() throws Exception {
		Employee emp = this.empRepo.findByEmailIgnoreCase(NOT_PRESENT_VALUE);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	
	@Test
	public void findById() throws Exception {
		Employee emp = this.empRepo.findById(this.employee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	
	@Test
	public void findById_null() throws Exception {
		Employee emp = this.empRepo.findById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	
	@Test
	public void findById_XXX() throws Exception {
		Employee emp = this.empRepo.findById(NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	
	@Test
	public void findByManagerId() throws Exception {
		createManager();

		this.employee.setManager(this.manager);
		this.empRepo.save(this.employee);

		List<Employee> emps = this.empRepo.findByManager(this.employee.getManager());
		assertNotNull("Expecting a non-null Employee but was null", emps);
		assertTrue("Expecting at least one employee found for manager but none was found", emps.size() > 0);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	
	@Test
	public void findByManagerId_empty() throws Exception {
		Employee emp = Entities.employee();
		emp.setDepartment(this.department);
		this.empRepo.saveAndFlush(emp);
		List<Employee> emps = this.empRepo.findByManager(emp);
		assertTrue(emps.isEmpty());
	}

	
	@Test
	public void findByFirstNameAndLastNameAndDepartmentAndJobTitle() throws Exception {
		List<Employee> emp = this.empRepo.findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(employee.getFirstName(), employee.getLastName(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertTrue(emp.size()==1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}
	
	
	@Test
	public void removeEmployeeTest() throws Exception {
		Employee emp = Entities.employee();
		emp.setIsActive(true);
		empRepo.removeEmployee(emp.getId());
		
		assertTrue(emp.getIsActive());
	}
	
	
	@Test
	public void findAllByFirstNameOrLastNameTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue("zzz", "zzz");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_first() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName(), "xxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_last() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue("xxxxx", this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_upperCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName().toUpperCase(), this.employee.getLastName().toUpperCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_lowerCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName().toLowerCase(), this.employee.getLastName().toLowerCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndIsActiveIsTrue(this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByDepartmentIdTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndIsActiveIsTrue(-1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByJobTitleIdAndIsActiveIsTrue(this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByJobTitleIdTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByJobTitleIdAndIsActiveIsTrue(-1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByDepartmentIdAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByDepartmentIdAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(this.employee.getDepartment().getId(), -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameTest_notfound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrue("xxxxxxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameTest_empty() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrue("");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_AtLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName(), "xxxxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_upperCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName().toUpperCase(), this.employee.getLastName().toUpperCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_lowerCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(this.employee.getFirstName().toLowerCase(), this.employee.getLastName().toLowerCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndDepartmentIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(this.employee.getFirstName(), -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getLastName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndDepartmentIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue("xxxx", this.employee.getLastName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue("xxxxxxx", this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getLastName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue("xxxxxxx", this.employee.getLastName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndDeptIdAndJobIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndDeptIdAndJobIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(this.employee.getFirstName(), -1, -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndDeptIdAndJobIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(this.employee.getFirstName(), this.employee.getLastName(), 
				this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndDeptIdAndJobIdTest_atLeastOneFieldNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue("xxxxxx", this.employee.getLastName(), 
				this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByIsActiveIsTrueTest() throws Exception {
		List<Employee> emps = empRepo.findAllByIsActiveIsTrue();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
}
