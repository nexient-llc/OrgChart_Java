package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceRealRepoTest {

	@Autowired
	EmployeeService employeeService;
	
	@Before
	public void before() throws Exception {
		this.department = this.deptRepo.saveAndFlush(Entities.department());
		
		this.jobTitle = this.jobRepo.saveAndFlush(Entities.jobTitle());
		
		this.employee = Entities.employee();
		this.employee.setJobTitle(this.jobTitle);
		this.employee.setDepartment(this.department);
		this.employee = this.empRepo.saveAndFlush(this.employee);
	}
	
	@Test
	public void findEmployeeByCriteria(){
		Employee employee = Entities.employee();
		List<Employee> emps = this.employeeService.findByCriteria(employee);
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
}
