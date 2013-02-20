package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.DepartmentDAO;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	DepartmentDAO mockDepartmentDAO = mock(DepartmentDAO.class);
	Department mockDepartment = mock(Department.class);
	Integer departmentID;
	private ArrayList<Department> departmentList = new ArrayList<Department>();
	
	@Before
	public void before() {
		
		// Mock up department
		when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
		when(mockDepartment.getName()).thenReturn(TestObject.DEPARTMENT_NAME);
		departmentList.add(mockDepartment);
		// when up Department DAO
		when(mockDepartmentDAO.save(mockDepartment)).thenReturn(
				TestObject.DEPT_ID);
		when(mockDepartmentDAO.findAll()).thenReturn(departmentList);
		when(mockDepartmentDAO.findByName(TestObject.DEPARTMENT_NAME)).thenReturn(departmentList);
		when(mockDepartmentDAO.findByParentDepartment(mockDepartment)).thenReturn(departmentList);
		departmentService.setDepartmentDAO(mockDepartmentDAO);
		
	}

	@Test
	@Rollback
	public void testfindDepartmentByID() {

		assertEquals(TestObject.DEPT_ID, mockDepartment.getDepartmentId());

	}

	@Test
	@Rollback
	public void testFindAllDepartments() {
		assertTrue(departmentService.findAllDepartments().size() > 0);

	}

	@Test
	@Rollback
	public void testStoreDepartment() {

		assertEquals(TestObject.DEPT_ID,
				departmentService.storeDepartment(mockDepartment));

	}
	
	@Test
	@Rollback
	public void testFindDeptByName()
	{
		assertTrue(departmentService.findDepartmentByName(TestObject.DEPARTMENT_NAME).size() > 0);
	}

}
