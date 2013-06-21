/*package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.assertNotNull;

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
import com.systemsinmotion.orgchart.dao.IEmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeTest {

	private Employee employee;

	@Autowired
	IEmployeeDao employeeDao;

	@After
	public void after() {
		this.employeeDao.delete(this.employee);
	}

	@Before
	public void before() throws Exception {
		this.employee = Entities.employee();
		this.employee.setId(this.employeeDao.save(this.employee));
	}

	@Test
	public void created() {
		assertNotNull(this.employee);
		assertNotNull(this.employee.getId());
	}

}*/