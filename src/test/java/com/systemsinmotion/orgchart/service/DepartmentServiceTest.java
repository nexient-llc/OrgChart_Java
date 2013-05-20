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

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	IDepartmentDao mockDepartmentDAO = mock(IDepartmentDao.class);
	Department mockDepartment = mock(Department.class);

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
		when(this.mockDepartmentDAO.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentDAO.findById(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(this.mockDepartmentDAO.save(this.mockDepartment)).thenReturn(Entities.DEPT_ID);
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
		assertNotNull(this.departmentService.findDepartmentByID(Entities.DEPT_ID));
		assertEquals(Entities.DEPT_ID, this.departmentService.findDepartmentByID(Entities.DEPT_ID)
				.getId());
	}

	@Test
	@Rollback
	public void shouldStoreDepartmentUsingDepartmentDAO() {
		assertNotNull(this.departmentService.storeDepartment(this.mockDepartment));
		assertEquals(Entities.DEPT_ID, this.departmentService.storeDepartment(this.mockDepartment));
	}

}
