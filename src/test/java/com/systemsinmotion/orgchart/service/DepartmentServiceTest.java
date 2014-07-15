package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private Department mockDepartment;

	@Test
	public void findAllDepartments() {
		List<Department> allDepts = this.departmentService.findAllDepartments();
		assertNotNull(allDepts);
		assertTrue(allDepts.size() > 0);
	}
	
	@Test
	public void findAllActiveDepartments() {
		List<Department> activeDepts = this.departmentService.findAllActiveDepartments();
		assertNotNull(activeDepts);
		assertTrue(activeDepts.size() > 0);
		
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
	public void storeNewDepartment() {
		Department dept = this.departmentService.storeNewDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
	}

	@Test	
	public void removeDepartmentSetsIsActiveToFalse() {
		List<Department> activeDepts = this.departmentService.findAllActiveDepartments();
		assertNotNull(activeDepts);

		Department department = activeDepts.get(0);
		assertTrue(department.getIsActive());
		
		this.departmentService.removeDepartment(department);
		
		assertFalse(department.getIsActive());
	}
	
	
}
