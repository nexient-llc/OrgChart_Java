package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
	private DepartmentRepository repository;

	@Mock
	private DepartmentService mockservice;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void findActiveDepartments() {
		List<Department> depts = this.departmentService.activeDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void findDepartmentByID() {
		Department dept = this.departmentService
				.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		Department dept = this.departmentService
				.storeDepartment(this.mockDepartment);
		assertNotNull(dept);
		assertEquals(
				"Expected " + Entities.DEPT_ID + " but got " + dept.getId(),
				Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void removeDepartment() {

		Department depart = departmentService.removeDepartment(mockDepartment);
		assertNotNull(depart);
		assertTrue(depart.isActive() == false);

	}

	@Test
	// no way to actually test this
	public void setRepoTest() throws Exception {

		departmentService.setRepository(repository);

	}

	@Test
	public void update() {
		Department depart = departmentService.update(mockDepartment);
		assertNotNull(depart);
	}
}
