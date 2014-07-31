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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	@Autowired
	@Qualifier(value="mockParentDepartment")
	private Department mockParentDepartment;

	@Before
	public void resetIsActive() {
		mockDepartment.setIsActive(true);
		mockParentDepartment.setId(Entities.PARENT_DEPT_ID);
		mockDepartment.setParentDepartment(mockParentDepartment);
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
	public void findDepartmentByName() {
		List<Department> depts = this.departmentService.findDepartmentByName(Entities.DEPARTMENT_NAME);
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void storeDepartment() {
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment_parentDepartmentIsNotNullButIdIsNull() {
		this.mockDepartment.getParentDepartment().setId(null);
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
		assertNull(dept.getParentDepartment());
	}

	@Test
	public void storeDepartment_parentDepartmentIsNull() {
		this.mockDepartment.setParentDepartment(null);
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
		assertNull(dept.getParentDepartment());
	}

	@Test
	public void storeDepartment_IsActiveIsNull() {
		mockDepartment.setIsActive(null);
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals("Expected " + Entities.DEPT_ID + " but got " + dept.getId(),Entities.DEPT_ID, dept.getId());
		assertTrue(dept.getIsActive());
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

	@Test
	public void findAllActiveDepartments_page() {
		Page<Department> depts = departmentService.findAllActiveDepartments(new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.ASC, "name").ignoreCase())));
		assertNotNull(depts);
		assertEquals(Entities.DEPT_ID, depts.getContent().get(0).getId());
	}
}
