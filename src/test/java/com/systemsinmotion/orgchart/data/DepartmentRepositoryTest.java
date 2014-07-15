package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentRepositoryTest {

	private static Random random = new Random();

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private Department department;
	private Department parent;

	@Autowired
	DepartmentRepository repository;

	@Before
	public void before() throws Exception {
		this.parent = Entities.department();
		this.parent = this.repository.saveAndFlush(parent);

		this.department = Entities.department(this.parent);
		this.department = this.repository.saveAndFlush(department);
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
		this.repository.save(dept);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.repository.toString());
		List<Department> depts = this.repository.findAll();
		assertNotNull(depts);
		assertTrue(0 < depts.size());
	}

	@Test
	public void findByDeptId() throws Exception {
		Department dept = this.repository.findOne(this.department.getId());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findById_notPresent() throws Exception {
		Department dept = this.repository.findOne(NOT_PRESENT_ID);
		assertNull(dept);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void findById_null() throws Exception {
		Department dept = this.repository.findOne(null);
		assertNull(dept);
	}

	@Test
	public void findByName() throws Exception {
		Department dept = this.repository.findByName(this.department.getName());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findByName_null() throws Exception {
		Department dept = this.repository.findByName(NOT_PRESENT_VALUE);
		assertNull(dept);
	}

	@Test
	public void findByParentDeptId() throws Exception {
		List<Department> depts = this.repository.findByParentDepartmentId(this.department.getParentDepartment().getId());
		assertNotNull(depts);
		assertEquals(1, depts.size());
		Department dept = depts.get(0);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
	}

	@Test
	public void findByParentDeptId_unknowId() throws Exception {
		List<Department> depts = this.repository
				.findByParentDepartmentId(random.nextInt());
		assertNotNull(depts);
		assertEquals(0, depts.size());
	}

	@Test
	public void update() throws Exception {
		Department dept = this.repository.findByName(this.department.getName());
		dept.setName(SOME_NEW_NAME);
		this.repository.saveAndFlush(dept);

		dept = null;
		dept = this.repository.findByName(SOME_NEW_NAME);
		assertNotNull(dept);
		assertEquals(SOME_NEW_NAME, dept.getName());
	}

}
