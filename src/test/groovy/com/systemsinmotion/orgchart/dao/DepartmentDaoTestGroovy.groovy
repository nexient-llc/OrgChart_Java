package com.systemsinmotion.orgchart.dao

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
import com.systemsinmotion.orgchart.dao.DepartmentDao;

 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration("/test-context.xml")
 @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
 @Transactional
class DepartmentDaoTestGroovy extends GroovyTestCase {
	def SOME_NEW_NAME = "Some New Name"
	def NOT_PRESENT_VALUE = "XXX"
	def NOT_PRESENT_ID = -666

	def parent
	def department
	
	@Autowired
	IDepartmentDao departmentDao

	@After
	void tearDown(){
		departmentDao.delete(department)
		departmentDao.delete(parent)
	}

	@Before
	void setUp() throws Exception {
		this.parent = Entities.department()
		this.parent.id = departmentDao.save(parent)
		this.department = Entities.department(parent) 
		this.department.id = departmentDao.save(department)
	}

	@Test
	void testCreate() throws Exception {
		assertNotNull(this.parent)
		assertNotNull(this.parent.id)
		assertNotNull(this.department)
		assertNotNull(this.department.id)
	}

	@Test(expected = DataIntegrityViolationException.class)
	void testDuplicateName() throws Exception {
		def dept = Entities.department()
		dept.name = this.department.name
		departmentDao.save(dept)
	}

	@Test
	void testFindAll_notNull() throws Exception {
		def depts = this.departmentDao.findAll()
		assertNotNull(depts);
		assertTrue(0 < depts.size)
	}

	@Test
	void testFindByDeptId() throws Exception {
		def dept = this.departmentDao.findById(this.department.id)
		assertNotNull(dept)
		println(this.department.getProperties())
		assertEquals(this.department.name, dept.name)
		assertNotNull(this.department.parentDepartment)
	}
	
	@Test
	void findById_notPresent() throws Exception {
		def dept = this.departmentDao.findById(NOT_PRESENT_ID);
		assertNull(dept)
	}
	@Test
	void findById_null() throws Exception {
		def dept = this.departmentDao.findById(null)
		assertNull(dept)
	}
	
	@Test
	void findByParentDeptId() throws Exception {
		def depts = this.departmentDao.findByParentDepartment(this.department.parentDepartment)
		assertNotNull(depts)
		def dept = depts[0]
		assertEquals(this.department.name, dept.name)
		assertNotNull(this.department.parentDepartment)
	}
	
	@Test
	void findByName_null() throws Exception {
		assertNull(this.departmentDao.findByName(NOT_PRESENT_VALUE))
	}
	
	@Test
	void update() throws Exception {
		def dept = this.departmentDao.findByName(this.department.name)
		dept.name = SOME_NEW_NAME
		this.departmentDao.update(dept)
		
		dept = this.departmentDao.findByName(SOME_NEW_NAME)
		assertNotNull(dept)
		assertEquals(SOME_NEW_NAME, dept.name)
	}
}
