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
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private Department mockDepartment;
	
	@Autowired
	private DepartmentRepository mockRepo;

	@Test
	public void init(){
		assertNotNull(this.departmentService);
	}
	
	@Test
	public void findAllDepartments() {
		List<Department> departments = this.departmentService.findAllDepartments();
		assertNotNull(departments);
		assertTrue(departments.size() > 0);
	}

	@Test
	public void findAllActiveDepartments(){
		List<Department> departments = this.departmentService.findAllActiveDepartments();
		assertNotNull(departments);
		assertTrue(departments.isEmpty());
	}
	
	@Test
	public void findDepartmentByID() {
		Department department = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(department);
		assertEquals(Entities.DEPT_ID, department.getId());
	}

	@Test
	public void setAndGetRepository(){
		this.departmentService.setRepository(mockRepo);
		assertEquals(mockRepo, this.departmentService.getRepository());
	}
	
	@Test
	public void storeAndRemoveDepartment() {
		// store test
		Department department = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(department);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + department.getId(),Entities.DEPT_ID, department.getId());
		
		// remove test
		this.departmentService.removeDepartment(department);
		System.out.println(department);
		assertNull(this.departmentService.findDepartmentByID(department.getId()));
	}
	
}
