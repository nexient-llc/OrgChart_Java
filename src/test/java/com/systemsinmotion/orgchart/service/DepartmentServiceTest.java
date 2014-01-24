package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bestbuy.supportspace.videolibrary.config.TestConfig;
import com.systemsinmotion.orgchart.entity.Department;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/test-context.xml")
@ContextConfiguration(classes = JPAConfig.class)
@WebAppConfiguration("/src/main/webapp")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	DepartmentRepository mockDepartmentRepo = mock(DepartmentRepository.class);
	Department mockDepartment = mock(Department.class);

	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();

	@Test
	public void bs() {
		assertTrue(true);
	}
	
	@Before
	public void before() throws Exception {
		when(this.mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		this.listOfFoundDepts.add(this.mockDepartment);
		when(this.mockDepartmentRepo.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockDepartmentRepo.findById(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(this.mockDepartmentRepo.save(this.mockDepartment)).thenReturn(Entities.department());
		this.departmentService.setRepository(this.mockDepartmentRepo);
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
        Department department = this.departmentService.storeDepartment(this.mockDepartment);
        assertNotNull(department);
        assertEquals(Entities.department().getId(), department.getId());

    }

}
