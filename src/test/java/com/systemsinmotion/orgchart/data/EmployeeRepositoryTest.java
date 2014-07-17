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
		List<Employee> emp = this.empRepo.findByFirstNameAndLastNameAndDepartmentIdAndJobTitleId(employee
				.getFirstName(), employee.getLastName(), employee.getDepartment().getId(), employee.getJobTitle()
				.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCase() throws Exception {
		List<Employee> emp = this.empRepo.findByFirstNameContainingIgnoreCase(employee.getFirstName().toLowerCase());
		List<Employee> emp2 = this.empRepo.findByFirstNameContainingIgnoreCase(employee.getFirstName().toUpperCase());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getFirstName(), emp2.get(0).getFirstName());
	}

	@Test
	public void findByLastNameContainingIgnoreCase() throws Exception {
		List<Employee> emp = this.empRepo.findByLastNameContainingIgnoreCase(employee.getLastName().toLowerCase());
		List<Employee> emp2 = this.empRepo.findByLastNameContainingIgnoreCase(employee.getLastName().toUpperCase());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getLastName(), emp2.get(0).getLastName());
	}

	@Test
	public void findByIsActiveIsTrue() throws Exception {
		System.out.println(this.empRepo.toString());
		List<Employee> emp = this.empRepo.findByIsActiveIsTrue();
		assertNotNull(emp);
		assertTrue(0 < emp.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
						employee.getLastName().toLowerCase(), employee.getLastName().toLowerCase());
		List<Employee> emp2 = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
						employee.getLastName().toUpperCase(), employee.getLastName().toUpperCase());
		List<Employee> emp3 = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
						employee.getFirstName().toLowerCase(), employee.getFirstName().toLowerCase());
		List<Employee> emp4 = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(
						employee.getFirstName().toUpperCase(), employee.getFirstName().toUpperCase());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertTrue(emp3.size() == 1);
		assertTrue(emp4.size() == 1);
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getLastName(), emp2.get(0).getLastName());
		assertEquals(this.employee.getFirstName(), emp3.get(0).getFirstName());
		assertEquals(this.employee.getFirstName(), emp4.get(0).getFirstName());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue() throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee
						.getFirstName().toLowerCase(), employee.getLastName().toLowerCase());
		List<Employee> emp2 = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(employee
						.getFirstName().toUpperCase(), employee.getLastName().toUpperCase());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getFirstName(), emp2.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getLastName(), emp2.get(0).getLastName());
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
	public void findByUpperCaseNameAndDepartmentIdAndActive() throws Exception {
		List<Employee> emp = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(employee.getFirstName()
				.toUpperCase(), employee.getDepartment().getId());
		List<Employee> emp2 = this.empRepo.findByUpperCaseNameAndDepartmentIdAndActive(employee.getLastName()
				.toUpperCase(), employee.getDepartment().getId());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp2.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getDepartment(), emp2.get(0).getDepartment());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(
						employee.getFirstName().toLowerCase(), employee.getLastName().toLowerCase(), employee
								.getDepartment().getId());
		List<Employee> emp2 = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(
						employee.getFirstName().toUpperCase(), employee.getLastName().toUpperCase(), employee
								.getDepartment().getId());
		assertTrue(emp.size() == 1);
		assertTrue(emp2.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getFirstName(), emp2.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getLastName(), emp2.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getDepartment(), emp2.get(0).getDepartment());
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
	public void findByUpperCaseNameAndJobTitleAndActive_firstName() throws Exception {
		List<Employee> emp = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(
				employee.getFirstName().toUpperCase(), employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndJobTitleAndActive_lastName() throws Exception {
		List<Employee> emp = this.empRepo.findByUpperCaseNameAndJobTitleAndActive(employee.getLastName().toUpperCase(),
				employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_lowerCase()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(
						employee.getFirstName().toLowerCase(), employee.getLastName().toLowerCase(), employee
								.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue_upperCase()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(
						employee.getFirstName().toUpperCase(), employee.getLastName().toUpperCase(), employee
								.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_firstName() throws Exception {
		List<Employee> emp = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(employee.getFirstName()
				.toUpperCase(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByUpperCaseNameAndDepartmentAndJobTitleAndActive_lastName() throws Exception {
		List<Employee> emp = this.empRepo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(employee.getLastName()
				.toUpperCase(), employee.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_lowerCase()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(
						employee.getFirstName().toLowerCase(), employee.getLastName().toLowerCase(), employee
								.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue_upperCase()
			throws Exception {
		List<Employee> emp = this.empRepo
				.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(
						employee.getFirstName().toUpperCase(), employee.getLastName().toUpperCase(), employee
								.getDepartment().getId(), employee.getJobTitle().getId());
		assertTrue(emp.size() == 1);
		assertEquals(this.employee.getFirstName(), emp.get(0).getFirstName());
		assertEquals(this.employee.getLastName(), emp.get(0).getLastName());
		assertEquals(this.employee.getJobTitle(), emp.get(0).getJobTitle());
		assertEquals(this.employee.getDepartment(), emp.get(0).getDepartment());
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
}
