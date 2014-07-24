package com.systemsinmotion.orgchart.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.config.JPAConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceRealRepoTest {

	@Autowired
	EmployeeService employeeService;
// TODO make this	
	
//	@Before
//	public void before() throws Exception {
//		this.department = this.deptRepo.saveAndFlush(Entities.department());
//		
//		this.jobTitle = this.jobRepo.saveAndFlush(Entities.jobTitle());
//		
//		this.employee = Entities.employee();
//		this.employee.setJobTitle(this.jobTitle);
//		this.employee.setDepartment(this.department);
//		this.employee = this.empRepo.saveAndFlush(this.employee);
//	}
//	
//	@Test
//	public void findEmployeeByCriteria(){
//		Employee employee = Entities.employee();
//		List<Employee> emps = this.employeeService.findByCriteria(employee);
//		assertNotNull(emps);
//		assertTrue(emps.size() > 0);
//	}
}
