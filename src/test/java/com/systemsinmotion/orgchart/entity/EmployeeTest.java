package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "last name";
	private static final String FIRST_NAME = "first name";
	private static final String EMAIL = "email@email.com";
	private static final String SKYPE = "skype.skype";
	Employee emp;
	Employee mgr;
	Department dept;
	JobTitle job;

	Random random = new Random();

	@Before
	public void before() {
		emp = new Employee();
		mgr = new Employee();
		mgr.setId(random.nextInt());
		dept = new Department();
		dept.setId(random.nextInt());
		job = new JobTitle();
		job.setId(random.nextInt());
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
	public void setAndGetDepartment(){
		emp.setDepartment(dept);
		assertNotNull(emp.getDepartment());
		assertEquals(dept.getId(),emp.getDepartment().getId());
	}
	
	@Test
	public void setAndGetJobTitle() {
		emp.setJobTitle(job);
		assertNotNull(emp.getJobTitle());
		assertEquals(job.getId(), emp.getJobTitle().getId());
	}
	
	@Test
	public void setAndGetEmail() {
		emp.setEmail(EMAIL);
		String email = emp.getEmail();
		assertNotNull(email);
		assertEquals(EMAIL, email);
	}

	@Test
	public void setAndGetSkype() {
		emp.setSkypeName(SKYPE);
		String skype = emp.getSkypeName();
		assertNotNull(skype);
		assertEquals(SKYPE, skype);
	}
	
	@Test
	public void getFullName() {
		emp.setFirstName(FIRST_NAME);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		emp.setLastName(LAST_NAME);
		String fullName = FIRST_NAME + " " + MIDDLE_INITIAL + " " + LAST_NAME;
		assertNotNull(emp.getFullName());
		assertEquals(fullName, emp.getFullName());
	}
}
