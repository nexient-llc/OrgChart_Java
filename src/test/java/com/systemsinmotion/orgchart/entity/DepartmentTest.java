package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class DepartmentTest {

	private static final String NEW_NAME = "new name";
	
	private Department dept;
	
	private Department child;

	@Before
	public void before() {
		dept = Entities.department();
	}

	@Test
	public void instantiated() {
		assertNotNull(dept);
	}

	@Test
	public void setAndGetName() {
		dept.setName(NEW_NAME);
		String name = dept.getName();
		assertNotNull(name);
		assertEquals(NEW_NAME, name);
	}

	@Test
	public void setAndGetParentDepartment() {
		child = Entities.department(dept);
		Department parent = child.getParentDepartment();
		assertNotNull(parent);
		assertEquals(dept.getId(), parent.getId());
	}

}
