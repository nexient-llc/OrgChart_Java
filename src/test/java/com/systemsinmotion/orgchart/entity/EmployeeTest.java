package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	private static final String FIRST_NAME = "first name";
	private static final String LAST_NAME = "last name";
	private static final Character MIDDLE_INITIAL = 'M';
	private static final String EMAIL = "email@mail.com";
	private static final String SKYPENAME = "Skype.Name";
	Employee emp;
	Employee mgr;
	Department dept;
	JobTitle jobTitle;

	Random random = new Random();

	@Before
	public void before() {
		emp = new Employee();
		mgr = new Employee();
		mgr.setId(random.nextInt());
		dept = new Department();
		dept.setId(random.nextInt());
		jobTitle = new JobTitle();
	}

	@Test
	public void instantiated() {
		assertNotNull(emp);
	}

	@Test
	public void setAndGetFirstName() {
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
	public void setAndGetEmail() {
		emp.setEmail(EMAIL);
		String email = emp.getEmail();
		assertNotNull(email);
		assertEquals(EMAIL, email);
	}

	@Test
	public void setAndGetSkypeName() {
		emp.setSkypeName(SKYPENAME);
		String skypeName = emp.getSkypeName();
		assertNotNull(skypeName);
		assertEquals(SKYPENAME, skypeName);
	}

	@Test
	public void setAndGetIsManager() {
		assertFalse(emp.getIsManager());
		emp.setIsManager(true);
		assertTrue(emp.getIsManager());
	}

	@Test
	public void setAndGetDepartment() {
		emp.setDepartment(dept);
		assertNotNull(emp.getDepartment());
		assertEquals(dept.getId(), emp.getDepartment().getId());
	}

	@Test
	public void setAndGetManager() {
		emp.setManager(mgr);
		assertNotNull(emp.getManager());
		Employee manager = emp.getManager();
		assertEquals(mgr, manager);
	}

	@Test
	public void setAndGetJobTitle() {
		emp.setJobTitle(jobTitle);
		assertNotNull(emp.getJobTitle());
		JobTitle jtitle = emp.getJobTitle();
		assertEquals(jobTitle, jtitle);
	}

	@Test
	public void testToString() throws Exception {
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
		String expectedName =FIRST_NAME+" "+LAST_NAME;
		
		assertEquals(expectedName, emp.toString());
		
		emp.setMiddleInitial(MIDDLE_INITIAL);
		expectedName =FIRST_NAME+" "+MIDDLE_INITIAL+" "+LAST_NAME;
		
		assertEquals(expectedName, emp.toString());
	}
}
