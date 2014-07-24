package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class EmployeeTest {
	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "lastname";
	private static final String FIRST_NAME = "firstname";
	Employee emp;
	Employee mgr;
	Department dept;
	JobTitle job;

	Random random = new Random();

	@Before
	public void before() {
		emp = new Employee();
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
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
	public void setAndGetSkypeName() {
		emp.setSkypeName(Entities.SKYPE_NAME);
		String skypeName = emp.getSkypeName();
		assertNotNull(skypeName);
		assertEquals(emp.getSkypeName(), Entities.SKYPE_NAME);
	}
	
	@Test
	public void setAndGetEmail() {
		emp.setEmail(Entities.EMAIL);
		String email = emp.getEmail();
		assertNotNull(email);
		assertEquals(emp.getEmail(), Entities.EMAIL);
	}
	
	@Test
	public void setAndGetJobTitle() {
		emp.setJobTitle(this.job);
		JobTitle temp = emp.getJobTitle();
		assertNotNull(temp);
		assertEquals(emp.getJobTitle(), this.job);
	}
	
	@Test
	public void getFullNameTest_NoMiddle() {
		String name = emp.getFullName();
		assertNotNull(name);
		assertEquals(name, Entities.FIRST_NAME + " " + Entities.LAST_NAME);
	}
	
	@Test
	public void getFullNameTest() {
		emp.setMiddleInitial(MIDDLE_INITIAL);
		String name = emp.getFullName();
		assertNotNull(name);
		assertEquals(name, FIRST_NAME + " " + MIDDLE_INITIAL.toString() + ". " + LAST_NAME);
	}
}
