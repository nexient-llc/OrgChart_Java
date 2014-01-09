package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentDaoTest {

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private Department department;
	private Department parent;

	@Autowired
	DepartmentDao departmentDao;

	@After
	public void after() {
		this.departmentDao.delete(this.department);
		this.departmentDao.delete(this.parent);
	}

	@Before
	public void before() throws Exception {
		this.parent = Entities.department();
		this.parent.setId(this.departmentDao.save(this.parent));
		this.department = Entities.department(this.parent);
		this.department.setId(this.departmentDao.save(this.department));
	}

	@Test
	public void created() {
		assertNotNull(this.parent);
		assertNotNull(this.parent.getId());
		assertNotNull(this.department);
		assertNotNull(this.department.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateName() throws Exception {
		Department dept = Entities.department();
		dept.setName(this.department.getName());
		this.departmentDao.save(dept);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.departmentDao.toString());
		List<Department> depts = this.departmentDao.findAll();
		assertNotNull(depts);
		assertTrue(0 < depts.size());
	}

	@Test
	public void findByDeptId() throws Exception {
		Department dept = this.departmentDao.findById(this.department.getId());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findById_notPresent() throws Exception {
		Department dept = this.departmentDao.findById(NOT_PRESENT_ID);
		assertNull(dept);
	}

	@Test
	public void findById_null() throws Exception {
		Department dept = this.departmentDao.findById(null);
		assertNull(dept);
	}

	@Test
	public void findByName() throws Exception {
		Department dept = this.departmentDao.findByName(this.department.getName());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findByName_null() throws Exception {
		Department dept = this.departmentDao.findByName(NOT_PRESENT_VALUE);
		assertNull(dept);
	}

	@Test
	public void findByParentDeptId() throws Exception {
		List<Department> depts = this.departmentDao.findByParentDepartment(this.department.getParentDepartment());
		assertNotNull(depts);
		assertEquals(1, depts.size());
		Department dept = depts.get(0);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findByParentDeptId_null() throws Exception {
		List<Department> depts = this.departmentDao.findByParentDepartment(new Department());
		assertNotNull(depts);
		assertEquals(0, depts.size());
	}

	@Test
	public void update() throws Exception {
		Department dept = this.departmentDao.findByName(this.department.getName());
		dept.setName(SOME_NEW_NAME);
		this.departmentDao.update(dept);

		dept = null;
		dept = this.departmentDao.findByName(SOME_NEW_NAME);
		assertNotNull(dept);
		assertEquals(SOME_NEW_NAME, dept.getName());
	}
}
