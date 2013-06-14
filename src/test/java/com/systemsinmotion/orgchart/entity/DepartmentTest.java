package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.systemsinmotion.orgchart.Entities;

public class DepartmentTest {

	Department department;
	
	

/*	@After
	public void after() {
		this.hibernateTemplate.delete(department);
	}

	@Before
	public void before() throws Exception {
		this.parent = Entities.department();
		this.parent.setId(this.departmentDao.save(this.parent));
		this.department = Entities.department(this.parent);
		this.department.setId(this.departmentDao.save(this.department));
	}
*/
	@Test
	public void objectExists() {
		
		fail("Not yet implemented");
		assertTrue("Not yet implemented", true);
	}

}
