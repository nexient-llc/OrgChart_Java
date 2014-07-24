package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	private static final boolean IS_MANAGER = true;
	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "last name";
	private static final String FIRST_NAME = "first name";
	private static final String EMAIL = "someone@blah.com";
	private static final String SKYPE_NAME = "skype.name";
	
	JobTitle jobTitle;
	Employee emp;
	Employee mgr;
	Department dept;

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
	public void setAndGetEmail(){
		emp.setEmail(EMAIL);
		assertNotNull(emp.getEmail());
		assertEquals(EMAIL,emp.getEmail());
	}
	
	@Test
	public void setAndGetJobTitle(){
		emp.setJobTitle(jobTitle);
		assertNotNull(emp.getJobTitle());
		assertEquals(jobTitle.getName(),emp.getJobTitle().getName());
	}
	
	@Test
	public void setAndGetSkypeName(){
		emp.setSkypeName(SKYPE_NAME);
		assertNotNull(emp.getSkypeName());
		assertEquals(SKYPE_NAME,emp.getSkypeName());
	}
	
	@Test
	public void setAndGetIsManager(){
		emp.setIsManager(IS_MANAGER);
		assertNotNull(emp.getIsManager());
		assertEquals(IS_MANAGER, emp.getIsManager());
	}
}
