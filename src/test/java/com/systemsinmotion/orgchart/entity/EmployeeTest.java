package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class EmployeeTest {

	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "last name";
	private static final String FIRST_NAME = "first name";
	private Employee emp;
	private Employee mgr;
	private Department dept;

	private Random random = new Random();

	@Before
	public void before() {
		emp = new Employee();
		mgr = new Employee();
		mgr.setId(random.nextInt());
		dept = new Department();
		dept.setId(random.nextInt());
	}

	@Test
	public void instantiated() {
		assertNotNull(emp);
	}

	@Test
	public void setAndGetName() {
		emp.setFirstName(FIRST_NAME);
		String name = emp.getFirstName();
		assertNotNull(name);
		assertEquals(FIRST_NAME, name);
	}

	@Test
	public void setAndGetLastName() {
		emp.setLastName(LAST_NAME);
		String name = emp.getLastName();
		assertNotNull(name);
		assertEquals(LAST_NAME, name);
	}

	@Test
	public void setAndGetMiddleInitial() {
		emp.setMiddleInitial(MIDDLE_INITIAL);
		Character middleInitial = emp.getMiddleInitial();
		assertNotNull(middleInitial);
		assertEquals(MIDDLE_INITIAL, middleInitial);
	}

	@Test
	public void setAndGetManager() {
		emp.setManager(mgr);
		Employee manager = emp.getManager();
		assertNotNull(manager);
		assertEquals(mgr.getId(), manager.getId());
	}

	@Test
	public void setAndGetDepartment() {
		emp.setDepartment(dept);

		assertNotNull(emp.getDepartment());
		assertEquals(dept.getId(), emp.getDepartment().getId());
	}

	@Test
	public void setAndGetEmail() {
		emp.setEmail(Entities.EMAIL);
		String email = emp.getEmail();
		assertNotNull(email);
		assertEquals(Entities.EMAIL, email);

	}

	@Test
	public void setAndGetSkype() {
		emp.setSkypeName(Entities.SKYPE_NAME);
		String skpeName = emp.getSkypeName();
		assertNotNull(skpeName);
		assertEquals(Entities.SKYPE_NAME, skpeName);

	}

	@Test
	public void setAndGetJobTitle() {
		emp.setJobTitle(Entities.jobTitle());
		JobTitle jobtile = emp.getJobTitle();
		assertNotNull(jobtile);
		assertEquals(Entities.jobTitle().getId(), jobtile.getId());

	}

	@Test
	public void ShoudReturnEmployeeNameAsString() {

		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
		String empName = emp.toString();
		assertNotNull(empName);
		assertTrue(empName.equals(FIRST_NAME + "   " + LAST_NAME));

	}

	@Test
	public void setAllTest() {

		emp.setFirstName("Mock");
		emp.setLastName("NAME");
		emp.setEmail("FAKEEMAIL");
		mgr.setAll(emp);

		assertNotNull(mgr);
		assertTrue(emp.getFirstName().equals(mgr.getFirstName()));

	}

}
