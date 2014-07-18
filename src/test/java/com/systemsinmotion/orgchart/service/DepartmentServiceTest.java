package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	@Qualifier(value="mockDepartment")
	private Department mockDepartment;

	@Before
	public void resetIsActive() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);		
		dept.setIsActive(true);
	}
	
	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void findDepartmentByID() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
	}
	
	@Test
	public void findAllActiveDepartments() {
		List<Department> depts = this.departmentService.findAllActiveDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}
	
	@Test
	public void removeDepartment() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertTrue(dept.getIsActive());
		this.departmentService.removeDepartment(dept);
		assertFalse(dept.getIsActive());
	}

	@Test
	public void removeDepartmentById() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertTrue(dept.getIsActive());
		this.departmentService.removeDepartmentById(Entities.DEPT_ID);
		assertFalse(dept.getIsActive());
	}
	
	@Test
	public void setAndGetRepository() {
		DepartmentRepository repo = this.departmentService.getRepository();
		assertNotNull(repo);
		this.departmentService.setRepository(null);
		assertNull(this.departmentService.getRepository());
		this.departmentService.setRepository(repo);
	}

}
