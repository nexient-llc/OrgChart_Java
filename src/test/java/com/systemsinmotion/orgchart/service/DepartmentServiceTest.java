package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bestbuy.supportspace.videolibrary.config.TestConfig;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class DepartmentServiceTest {

//	@Autowired
//	DepartmentService departmentService;

//	DepartmentDao mockDepartmentDAO = mock(DepartmentDao.class);
//	Department mockDepartment = mock(Department.class);

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Test
	public void bs() {
		assertTrue(true);
	}
	
//	@Before
//	public void before() throws Exception {
//		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
//		this.listOfFoundDepts.add(this.mockDepartment);
//		when(this.mockDepartmentDAO.findAll()).thenReturn(this.listOfFoundDepts);
//		when(this.mockDepartmentDAO.findById(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
//		when(this.mockDepartmentDAO.save(this.mockDepartment)).thenReturn(Entities.DEPT_ID);
//		this.departmentService.setDepartmentDAO(this.mockDepartmentDAO);
//	}
//
//	@Test
//	public void findAllDepartments() {
//		List<Department> depts = this.departmentService.findAllDepartments();
//		assertNotNull(depts);
//		assertEquals(1, depts.size());
//	}
//
//	@Test
//	public void findDepartmentByID() {
//		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
//		assertNotNull(dept);
//		assertEquals(Entities.DEPT_ID, dept.getId());
//	}
//
//	@Test
//	public void storeDepartment() {
//		Integer deptId = this.departmentService.storeDepartment(this.mockDepartment);
//		assertNotNull(deptId);
//		assertEquals(Entities.DEPT_ID, deptId);
//	}

}
