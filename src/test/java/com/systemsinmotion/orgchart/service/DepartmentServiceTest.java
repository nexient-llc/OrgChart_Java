package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
@ContextConfiguration(locations = { "/test-context.xml" })
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	DepartmentDAO mockDepartmentDAO = mock(DepartmentDAO.class);
	Department mockDepartment = mock(Department.class);

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
		when(this.mockDepartmentDAO.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentDAO.findById(TestObject.DEPT_ID)).thenReturn(this.mockDepartment);
		when(this.mockDepartmentDAO.save(this.mockDepartment)).thenReturn(TestObject.DEPT_ID);
		this.departmentService.setDepartmentDAO(this.mockDepartmentDAO);
	}

	@Test
	@Rollback
	public void shouldFindAllDepartments() {
		assertNotNull(this.departmentService.findAllDepartments());
	}

	@Test
	@Rollback
	public void shouldFindDepartmentbyID() {
		assertNotNull(this.departmentService.findDepartmentByID(TestObject.DEPT_ID));
		assertEquals(TestObject.DEPT_ID, this.departmentService.findDepartmentByID(TestObject.DEPT_ID)
				.getDepartmentId());
	}

	@Test
	@Rollback
	public void shouldStoreDepartmentUsingDepartmentDAO() {
		assertNotNull(this.departmentService.storeDepartment(this.mockDepartment));
		assertEquals(TestObject.DEPT_ID, this.departmentService.storeDepartment(this.mockDepartment));
	}

}
