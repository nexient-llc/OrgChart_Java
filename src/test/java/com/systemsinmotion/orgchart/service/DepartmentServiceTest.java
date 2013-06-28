package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	DepartmentDao mockDepartmentDAO = mock(DepartmentDao.class);
	
	Department mockDepartment = mock(Department.class);


	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Before
	public void before() throws Exception {
		this.departmentService.setDepartmentDAO(this.mockDepartmentDAO);
	}

	@Test
	public void canFindAllDepartments() {
		departmentService.findAllDepartments();
		verify(mockDepartmentDAO).findAll();
	}

	@Test
	public void canFindDepartmentByID() {
		departmentService.findDepartmentByID(mockDepartment.getId());
		verify(mockDepartmentDAO).findById(mockDepartment.getId());
	}

	@Test
	public void canStoreDepartment() {
		departmentService.storeDepartment(mockDepartment);
		verify(mockDepartmentDAO).save(mockDepartment);
	}

	@Test
	public void canUpdateDepartment() {
		departmentService.updateDepartment(mockDepartment);
		verify(mockDepartmentDAO).update(mockDepartment);
	}

	@Test
	public void canRemoveDepartment() {
		departmentService.removeDepartment(mockDepartment);
		verify(mockDepartmentDAO).delete(mockDepartment);
	}
}
