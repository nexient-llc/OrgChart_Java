package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;
	
	DepartmentRepository mockDepartmentRepo = mock(DepartmentRepository.class);
	Department mockDepartment = mock(Department.class);
	
	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();
	
	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
		when(this.mockDepartmentRepo.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentRepo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		//when(this.mockDepartmentRepo.findDepartmentByName(Entities.DEPARTMENT_NAME)).thenReturn(this.mockDepartment);
		when(this.mockDepartmentRepo.findDepartmentsByParentDepartmentId(Entities.DEPT_ID)).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentRepo.findDepartmentsByIsActiveTrue()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentRepo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		this.departmentService.setRepository(this.mockDepartmentRepo);
	}
	
	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertEquals(1, depts.size());
	}
	
	@Test
	public void findDepartmentById() {
		Department dept = this.departmentService.findDepartmentById(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}
	
	@Test
	public void findDepartmentById_null() {
		Department dept = this.departmentService.findDepartmentById(null);
		assertNull(dept);
	}
	
	@Test
	public void findDepartmentsByParentDepartmentId() {
		List<Department> depts = this.departmentService.findDepartmentsByParentDepartmentId(Entities.DEPT_ID);
		assertNotNull(depts);
		assertEquals(1, depts.size());
	}
	
	@Test
	public void findDepartmentsByParentDepartmentId_null() {
		List<Department> depts = this.departmentService.findDepartmentsByParentDepartmentId(null);
		assertNull(depts);
	}
	
	@Test
	public void findDepartmentsByIsActiveTrue() {
		List<Department> depts = this.departmentService.findDepartmentsByIsActiveTrue();
		assertNotNull(depts);
		assertEquals(1, depts.size());
	}
	
	@Test
	public void setInactiveDepartment() {
		Integer deptId = this.departmentService.setInactiveDepartment(this.mockDepartment).getId();
		assertNotNull(deptId);
		assertEquals(Entities.DEPT_ID, deptId);
	}
	
	@Test
	public void storeDepartment() {
		Integer deptId = this.departmentService.storeDepartment(this.mockDepartment).getId();
		assertNotNull(deptId);
		assertEquals(Entities.DEPT_ID, deptId);
	}
	
	
/*	@Test
	public void removeDepartment() {
		Integer deptId = this.departmentService.storeDepartment(this.mockDepartment).getId();
		assertNotNull(deptId);
		this.departmentService.removeDepartment(this.mockDepartment);
		assertNull(this.departmentService.findDepartmentById(this.mockDepartment.getId()));
	}*/
	
}
