package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IDepartmentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentTest {

	private Department department;
	private Department parent;

	@Autowired
	IDepartmentDao departmentDao;

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
}
