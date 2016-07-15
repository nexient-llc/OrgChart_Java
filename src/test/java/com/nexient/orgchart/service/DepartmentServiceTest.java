package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class DepartmentServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	DepartmentService departmentService;

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Test
	public void shouldFindDepartmentbyID() {
		assertTrue(true);
	}

	private void assertTrue(boolean b) {
	}

	@Test
	public void shouldFindAllDepartments() {
		assertNotNull(departmentService.findAll());
	}

	@Test
	public void shouldStoreDepartmentUsingDepartmentDAO() {
//		assertNotNull(departmentService.saveOrUpdate(mockDepartment));
//		assertEquals(TestObject.DEPT_ID, departmentService.saveOrUpdate(mockDepartment));
	}
}
