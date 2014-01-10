package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	
	EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);
	
	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();
	
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getFirstName()).thenReturn(Entities.FIRST_NAME);
		when(this.mockEmployee.getLastName()).thenReturn(Entities.LAST_NAME);
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeRepository.findAll()).thenReturn(this.listOfFoundEmployees);
		when(this.mockEmployeeRepository.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepository.save(this.mockEmployee)).thenReturn(this.mockEmployee);
//		when(this.mockEmployeeRepository.findByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockEmployee);
//		this.jobTitleService.setRepository(this.mockEmployeeRepository);
	}
	
	@Test
	public void findAllEmployees() {
//		List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
//		assertNotNull(jobs);
//		assertEquals(1, jobs.size());
	}
	
//	@Test
//	public void findJobTitleByID() {
//		JobTitle jobTitle = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
//		assertNotNull(jobTitle);
//		assertEquals(Entities.JOB_TITLE_ID, jobTitle.getId());
//	}
//	
//	@Test
//	public void storeJobTitle() {
//		Integer jobTitleId = this.jobTitleService.storeDepartment(this.mockEmployee).getId();
//		assertNotNull(jobTitleId);
//		assertEquals(Entities.JOB_TITLE_ID, jobTitleId);
//	}
//	
//	@Test
//	public void findJobTitleByName(){
//		JobTitle jobTitle = this.jobTitleService.findJobTitleByName(Entities.JOB_TITLE_NAME);
//		assertNotNull(jobTitle);
//		assertEquals(Entities.JOB_TITLE_NAME, jobTitle.getName());
//	}
}
