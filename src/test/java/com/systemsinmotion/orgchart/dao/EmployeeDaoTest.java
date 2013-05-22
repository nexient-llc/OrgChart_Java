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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeDaoTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	private Department department;
	private Employee employee;
	private JobTitle job;

	@Autowired
	IEmployeeDao employeeDao;

	@Autowired
	IDepartmentDao departmentDao;
	
	@Autowired
	IJobTitleDao jobTitleDao;

	@Before
	public void before() throws Exception {
		this.department = Entities.department();
		this.departmentDao.save(this.department);
		
		this.job = Entities.jobTitle();
		this.jobTitleDao.save(job);
		
		this.employee = Entities.employee();
		this.employee.setDepartment(this.department);
		this.employee.setJobTitle(this.job);
		this.employee.setId(this.employeeDao.save(this.employee));
	}
	
	@After
	public void after() {
		this.employeeDao.delete(this.employee);
		this.departmentDao.delete(this.department);
		this.jobTitleDao.delete(this.job);
	}

	@Test
	public void findAll() throws Exception {
		List<Employee> emps = this.employeeDao.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}
	
}