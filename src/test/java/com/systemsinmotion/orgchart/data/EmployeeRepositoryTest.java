package com.systemsinmotion.orgchart.data;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
	// private Department department = new Department();
	// private JobTitle jobTitle = new JobTitle();
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

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.jobTitle = Entities.jobTitle();
		this.jobRepo.saveAndFlush(this.jobTitle);
		this.deptRepo.saveAndFlush(this.department);
		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employee.setId(this.empRepo.save(this.employee).getId());
		this.employee.setJobTitle(this.jobTitle);
	}

	@Test
	public void testInstantiation() {
		assertNotNull(deptRepo);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateEmployee_email() throws Exception {
		Employee emp = Entities.employee();
		emp.setDepartment(department);
		emp.setJobTitle(jobTitle);
		emp.setEmail(employee.getEmail());
		this.empRepo.save(emp);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateEmployee_skype() throws Exception {
		Employee emp = Entities.employee();
		emp.setDepartment(department);
		emp.setJobTitle(jobTitle);
		emp.setSkypeName(employee.getSkypeName());
		this.empRepo.save(emp);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void duplicateEmployee_null() throws Exception {
		Employee emp = null;
		this.empRepo.save(emp);
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
		List<Employee> emps = this.empRepo.findByDepartment(this.employee.getDepartment());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
	}

	@Test
	public void findByDepartment_null() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartment(null);
		// assertNull("Expecting a null list of Employees but was non-null", emps);
		assertTrue(emps.isEmpty());
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
	public void findByEmail_XXX() throws Exception {
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
		// assertNull(emps);
		assertTrue(emps.isEmpty());
	}

	// @Test
	// public void findByManagerId_null() throws Exception {
	// List<Employee> emps = this.empRepo.findByManager(null);
	// assertNull(emps);
	// }

	@Test
	public void findByFirstNameAndLastNameAndDepartmentAndJobTitle() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(employee.getFirstName(), employee.getLastName(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertNotNull("Expecting a non-null Employee List but was null", emps);
		assertTrue(emps.size()==1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCase_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCase(employee.getFirstName().toLowerCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
	}

	@Test
	public void findByFirstNameContainingIgnoreCase_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCase(employee.getFirstName().toUpperCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
	}

	@Test
	public void findByLastNameContainingIgnoreCase_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByLastNameContainingIgnoreCase(employee.getLastName().toLowerCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}

	@Test
	public void findByLastNameContainingIgnoreCase_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByLastNameContainingIgnoreCase(employee.getLastName().toUpperCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}

//	@Test
//	public void findByLastNameContainingIgnoreCase_null() throws Exception {
//		List<Employee> emps = this.empRepo.findByLastNameContainingIgnoreCase(null);
//		assertTrue(emps.isEmpty());
//	}

	@Test
	public void findByLastNameContainingIgnoreCase_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByLastNameContainingIgnoreCase(NOT_PRESENT_VALUE);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByIsActiveIsTrue() throws Exception {
		System.out.println(this.empRepo.toString());
		List<Employee> emps = this.empRepo.findByIsActiveIsTrue();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}
	
	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstNameLower() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getFirstName().toLowerCase(),employee.getFirstName().toLowerCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstNameUpper() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getFirstName().toUpperCase(),employee.getFirstName().toUpperCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
	}
	
	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_lastNameLower() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getLastName().toLowerCase(),employee.getLastName().toLowerCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}
	
	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_lastNameUpper() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getLastName().toUpperCase(), employee.getLastName().toUpperCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getFirstName().toLowerCase(),employee.getLastName().toLowerCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee.getFirstName().toUpperCase(),employee.getLastName().toUpperCase());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
	}

//	@Test
//	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_null() throws Exception {
//		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(null,null);
//		assertTrue(emps.isEmpty());
//	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(NOT_PRESENT_VALUE,NOT_PRESENT_VALUE);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByDepartmentIdAndIsActiveIsTrue() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndIsActiveIsTrue(this.employee.getDepartment().getId());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getIsActive(), emp.getIsActive());
	}

	@Test
	public void findByDepartmentIdAndIsActiveIsTrue_null() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndIsActiveIsTrue(null);
		assertTrue(emps.isEmpty());	
	}
	
	@Test
	public void findByDepartmentIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndIsActiveIsTrue(NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentIdAndActive_firstName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(employee.getFirstName().toUpperCase(), employee.getDepartment().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentIdAndActive_lastName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(employee.getLastName().toUpperCase(), employee.getDepartment().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentIdAndActive_null() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(null,null);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentIdAndActive_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(NOT_PRESENT_VALUE,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(employee.getFirstName().toLowerCase(),employee.getLastName().toLowerCase(),employee.getDepartment().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(employee.getFirstName().toUpperCase(),employee.getLastName().toUpperCase(),employee.getDepartment().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

//	@Test
//	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue_null() throws Exception {
//		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(null,null,null);
//		assertTrue(emps.isEmpty());
//	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(NOT_PRESENT_VALUE,NOT_PRESENT_VALUE,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByJobTitleIdAndIsActiveIsTrue() throws Exception {
		List<Employee> emps = this.empRepo.findByJobTitleIdAndIsActiveIsTrue(this.employee.getJobTitle().getId());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getIsActive(), emp.getIsActive());
	}

	@Test
	public void findByJobTitleIdAndIsActiveIsTrue_null() throws Exception {
		List<Employee> emps = this.empRepo.findByJobTitleIdAndIsActiveIsTrue(null);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByJobTitleIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByJobTitleIdAndIsActiveIsTrue(NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndJobTitleAndActive_firstName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(employee.getFirstName().toUpperCase(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndJobTitleAndActive_lastName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(employee.getLastName().toUpperCase(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndJobTitleAndActive_null() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(null,null);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndJobTitleAndActive_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(NOT_PRESENT_VALUE,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(employee.getFirstName().toLowerCase(),employee.getLastName().toLowerCase(),employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(employee.getFirstName().toUpperCase(),employee.getLastName().toUpperCase(),employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

//	@Test
//	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_null() throws Exception {
//		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(null,null,null);
//		assertTrue(emps.isEmpty());
//	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(NOT_PRESENT_VALUE,NOT_PRESENT_VALUE,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_firstName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(employee.getFirstName().toUpperCase(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_lastName() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(employee.getLastName().toUpperCase(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_null() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(null,null,null);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(NOT_PRESENT_VALUE,NOT_PRESENT_ID,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_lowerCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(employee.getFirstName().toLowerCase(),employee.getLastName().toLowerCase(),employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_upperCase() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(employee.getFirstName().toUpperCase(),employee.getLastName().toUpperCase(),employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emps.size() == 1);
		assertEquals(this.employee.getFirstName(), emps.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emps.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emps.get(0).getJobTitle());
		assertEquals(this.employee.getDepartment(), emps.get(0).getDepartment());
	}

//	@Test
//	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_null() throws Exception {
//		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(null,null,null,null);
//		assertTrue(emps.isEmpty());
//	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(NOT_PRESENT_VALUE,NOT_PRESENT_VALUE,NOT_PRESENT_ID,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(employee.getDepartment()
				.getId(), this.employee.getJobTitle().getId());
		assertNotNull("Expecting a non-null list of Employees but was null", emps);
		Employee emp = emps.get(0);
		assertEquals(this.employee.getFirstName(), emp.getFirstName());
		assertEquals(this.employee.getLastName(), emp.getLastName());
		assertEquals(this.employee.getEmail(), emp.getEmail());
		assertEquals(this.employee.getIsActive(), emp.getIsActive());
	}

	@Test
	public void findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue_null() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(null,null);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue_xxx() throws Exception {
		List<Employee> emps = this.empRepo.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(NOT_PRESENT_ID,NOT_PRESENT_ID);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findActiveByFirstNameOrLastName_firstNameOnly() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastName(partialFirstName);

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getFirstName().contains(partialFirstName));
	}

	@Test
	public void findActiveByFirstNameOrLastName_firstNameUpperCase() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4).toUpperCase();
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastName(partialFirstName);

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getFirstName().toUpperCase().contains(partialFirstName));
	}

	@Test
	public void findActiveByFirstNameOrLastName_lastNameOnly() {
		String partialLastName = this.employee.getLastName().substring(1, 4);
		assertEquals(3, partialLastName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastName(partialLastName);

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getLastName().contains(partialLastName));
	}


	@Test
	public void findActiveByFirstNameOrLastName_inactive() {
		this.employee.setIsActive(false);
		this.empRepo.save(employee);

		String partialLastName = this.employee.getLastName().substring(1, 4);
		assertEquals(3, partialLastName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastName(partialLastName);

		assertNotNull(employees);
		assertTrue(employees.isEmpty());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentId_matchingDepartment() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndDepartmentId(partialFirstName, department.getId());

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getFirstName().contains(partialFirstName));
		assertEquals(department.getId(), employees.get(0).getDepartment().getId());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentId_mismatchingDepartment() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndDepartmentId(partialFirstName, Entities.NOT_PRESENT_ID);

		assertNotNull(employees);
		assertTrue(employees.isEmpty());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndJobTitleId_matchingJobTitle() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndJobTitleId(partialFirstName, jobTitle.getId());

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getFirstName().contains(partialFirstName));
		assertEquals(jobTitle.getId(), employees.get(0).getJobTitle().getId());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndJobTitleId_mismatchingJobTitle() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndJobTitleId(partialFirstName, Entities.NOT_PRESENT_ID);

		assertNotNull(employees);
		assertTrue(employees.isEmpty());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId_matchingJobTitle() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(partialFirstName, department.getId(), jobTitle.getId());

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertTrue(employees.get(0).getFirstName().contains(partialFirstName));
		assertEquals(jobTitle.getId(), employees.get(0).getJobTitle().getId());
		assertEquals(department.getId(), employees.get(0).getDepartment().getId());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId_mismatchingJobTitle() {
		String partialFirstName = this.employee.getFirstName().substring(1, 4);
		assertEquals(3, partialFirstName.length());

		List<Employee> employees = this.empRepo.findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(partialFirstName, Entities.NOT_PRESENT_ID, Entities.NOT_PRESENT_ID);

		assertNotNull(employees);
		assertTrue(employees.isEmpty());
	}
	
	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId_unknownInputs_nulls() {
		List<Employee> employees = this.empRepo.findActiveByUnknownInputs(null, null, null, null);

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
	}

	@Test
	public void findActiveByFirstNameOrLastNameAndDepartmentIdAndJobTitleId_unknownInputs_firstNameOnly() {
		List<Employee> employees = this.empRepo.findActiveByUnknownInputs(Entities.FIRST_NAME, null, null, null);
		System.out.println("Test: " + employees);
		System.out.println("Emp: " + employee);

		assertNotNull(employees);
		assertFalse(employees.isEmpty());
		assertEquals(employees.get(0).getId(), employee.getId());
	}

}
