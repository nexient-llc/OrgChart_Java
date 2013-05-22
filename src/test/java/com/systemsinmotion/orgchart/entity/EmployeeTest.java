package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {
	
	// static data
	public static final String nullName = null;
	public static final Integer nullId = null;
	
	// test! test! test!
	@Test
	public void set_get_id() {
		Employee emp = new Employee();
		emp.setId(nullId);
		assertNull(emp.getId());
	}
	
	@Test
	public void set_get_first_name() {
		Employee emp = new Employee();
		emp.setFirstName(nullName);
		assertNull(emp.getFirstName());
	}
	
	@Test
	public void set_get_last_name() {
		Employee emp = new Employee();
		emp.setLastName(nullName);
		assertNull(emp.getLastName());
	}
	
	@Test
	public void set_get_middle_initial() {
		Employee emp = new Employee();
		emp.setMiddleInitial(nullName);
		assertNull(emp.getMiddleInitial());
	}
	
	@Test
	public void set_get_email() {
		Employee emp = new Employee();
		emp.setEmail(nullName);
		assertNull(emp.getEmail());
	}
	
	@Test
	public void set_get_skype_name() {
		Employee emp = new Employee();
		emp.setSkypeName(nullName);
		assertNull(emp.getSkypeName());
	}
	
	@Test
	public void set_get_job_title_id() {
		Employee emp = new Employee();
		emp.setJobTitle(null);
		assertNull(emp.getJobTitle());
	}
	
	@Test
	public void set_get_department_id() {
		Employee emp = new Employee();
		emp.setDepartment(null);
		assertNull(emp.getDepartment());
	}
	
}
