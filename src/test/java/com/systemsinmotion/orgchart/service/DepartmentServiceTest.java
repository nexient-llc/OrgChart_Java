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

	DepartmentRepository mockDepartmentDAO = mock(DepartmentRepository.class);
	Department mockDepartment = mock(Department.class);

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
		when(this.mockDepartmentDAO.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentDAO.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(this.mockDepartmentDAO.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		this.departmentService.setRepository(this.mockDepartmentDAO);
	}

	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertEquals(1, depts.size());
	}

	@Test
	public void findDepartmentByID() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		Department deptId = this.departmentService.storeDepartment(this.mockDepartment);
		assertNotNull(deptId);
		assertEquals(Entities.DEPT_ID, deptId.getId());
	}

}
