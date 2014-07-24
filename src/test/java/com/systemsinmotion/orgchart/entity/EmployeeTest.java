package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		emp.setId(random.nextInt());
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
	public void getFullName_complete() {
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		
		assertEquals(emp.getFullName(), FIRST_NAME + " " + MIDDLE_INITIAL + " " + LAST_NAME);
	}

	@Test
	public void getFullName_allNull() {
		emp.setFirstName(null);
		emp.setLastName(null);
		emp.setMiddleInitial(null);
		
		assertEquals(emp.getFullName(), "");
	}

	@Test
	public void getFullName_NoMidInitial() {
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
		emp.setMiddleInitial(null);
		
		assertEquals(emp.getFullName(), FIRST_NAME + " " + LAST_NAME);
	}

	@Test
	public void getFullName_NoMidInitialNoLastName() {
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(null);
		emp.setMiddleInitial(null);
		
		assertEquals(emp.getFullName(), FIRST_NAME);
	}

	@Test
	public void getFullName_NoMidInitialNoFirstName() {
		emp.setFirstName(null);
		emp.setLastName(LAST_NAME);
		emp.setMiddleInitial(null);
		
		assertEquals(emp.getFullName(), LAST_NAME);
	}

	@Test
	public void getFullName_NoFirstNameNoLastName() {
		emp.setFirstName(null);
		emp.setLastName(null);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		
		assertEquals(emp.getFullName(), MIDDLE_INITIAL.toString());
	}

	@Test
	public void getFullName_NoFirstName() {
		emp.setFirstName(null);
		emp.setLastName(LAST_NAME);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		
		assertEquals(emp.getFullName(), MIDDLE_INITIAL.toString() + " " + LAST_NAME);
	}
	
	@Test
	public void getFullName_NoLastName() {
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(null);
		emp.setMiddleInitial(MIDDLE_INITIAL);
		
		assertEquals(emp.getFullName(), FIRST_NAME + " " + MIDDLE_INITIAL);
	}

	@Test
	public void equals_sameObj() {
		assertTrue(emp.equals(emp));
	}
	
	@Test
	public void equals_sameId() {
		Employee newEmp = new Employee();
		assertNotNull(newEmp);
		
		newEmp.setId(emp.getId());
		assertTrue(emp.equals(newEmp));
	}
	
	@Test
	public void equals_differentId() {
		Employee newEmp = new Employee();
		assertNotNull(newEmp);
		
		newEmp.setId(emp.getId() + 1);
		assertFalse(emp.equals(newEmp));
	}
	
	@Test
	public void equals_bothIdNull() {
		Employee newEmp = new Employee();
		assertNotNull(newEmp);
		emp.setId(null);
		newEmp.setId(null);
		assertTrue(emp.equals(newEmp));
	}

	@Test
	public void equals_FirstIdNull() {
		Employee newEmp = new Employee();
		assertNotNull(newEmp);
		emp.setId(null);
		newEmp.setId(random.nextInt());
		assertFalse(emp.equals(newEmp));
	}

	@Test
	public void equals_SecondIdNull() {
		Employee newEmp = new Employee();
		assertNotNull(newEmp);
		newEmp.setId(null);
		assertFalse(emp.equals(newEmp));
	}

	@Test
	public void equals_notAnEmployee() {
		Department newDept = new Department();
		assertNotNull(newDept);
		assertFalse(emp.equals(newDept));
	}

}
