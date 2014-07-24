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
		List<Employee> emps = this.empRepo.findAllByDepartmentId(this.employee.getDepartment().getId());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}


	
	@Test
	public void findByEmail() throws Exception {
		Employee emp = this.empRepo.findByEmail(this.employee.getEmail());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByEmail_null() throws Exception {
		Employee emp = this.empRepo.findByEmail(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findByEmailTest_XXX() throws Exception {
		Employee emp = this.empRepo.findByEmail(NOT_PRESENT_VALUE);
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
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(this.employee.getFirstName(), this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("zzz", "zzz");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_first() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(this.employee.getFirstName(), "xxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_last() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("xxxxx", this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_upperCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(this.employee.getFirstName().toUpperCase(), this.employee.getLastName().toUpperCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameOrLastNameTest_lowerCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(this.employee.getFirstName().toLowerCase(), this.employee.getLastName().toLowerCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentId(this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByDepartmentIdTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentId(-1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByJobTitleId(this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByJobTitleIdTest_notFound() throws Exception {
		List<Employee> emps = empRepo.findAllByJobTitleId(-1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByDepartmentIdAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndJobTitleId(this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByDepartmentIdAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByDepartmentIdAndJobTitleId(this.employee.getDepartment().getId(), -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCase(this.employee.getFirstName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameTest_notfound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCase("xxxxxxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameTest_empty() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCase("");
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(this.employee.getFirstName(), this.employee.getLastName());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_AtLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(this.employee.getFirstName(), "xxxxxxxxxx");
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_upperCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(this.employee.getFirstName().toUpperCase(), this.employee.getLastName().toUpperCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameTest_lowerCase() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(this.employee.getFirstName().toLowerCase(), this.employee.getLastName().toLowerCase());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentId(this.employee.getFirstName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndDepartmentIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentId(this.employee.getFirstName(), -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndDepartmentIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(this.employee.getFirstName(), this.employee.getLastName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndDepartmentIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId("xxxx", this.employee.getLastName(), this.employee.getDepartment().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndJobTitleId(this.employee.getFirstName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndJobTitleId("xxxxxxx", this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndJobTitleIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(this.employee.getFirstName(), this.employee.getLastName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndJobTitleIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId("xxxxxxx", this.employee.getLastName(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndDeptIdAndJobIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(this.employee.getFirstName(), this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndDeptIdAndJobIdTest_atLeastOneNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(this.employee.getFirstName(), -1, -1);
		assertNotNull(emps);
		assertTrue(emps.size() == 0);
	}
	
	
	@Test
	public void findAllByFirstNameAndLastNameAndDeptIdAndJobIdTest() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(this.employee.getFirstName(), this.employee.getLastName(), 
				this.employee.getDepartment().getId(), this.employee.getJobTitle().getId());
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	@Test
	public void findAllByFirstNameAndLastNameAndDeptIdAndJobIdTest_atLeastOneFieldNotFound() throws Exception {
		List<Employee> emps = empRepo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId("xxxxxx", this.employee.getLastName(), 
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
