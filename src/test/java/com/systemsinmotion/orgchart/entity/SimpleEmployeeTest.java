package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SimpleEmployeeTest {
	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "last name";
	private static final String FIRST_NAME = "first name";
	SimpleEmployee emp;

	Random random = new Random();

	@Before
	public void before() {
		emp = new SimpleEmployee();
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

}
