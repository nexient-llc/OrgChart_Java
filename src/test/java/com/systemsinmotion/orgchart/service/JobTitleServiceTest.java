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
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;
	
	JobTitleRepository mockJobTitleRepository = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);
	
	private ArrayList<JobTitle> listOfFoundJobTitles = new ArrayList<JobTitle>();
	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobTitles.add(this.mockJobTitle);
		when(this.mockJobTitleRepository.findAll()).thenReturn(this.listOfFoundJobTitles);
		when(this.mockJobTitleRepository.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepository.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
		this.jobTitleService.setRepository(this.mockJobTitleRepository);
	}
	
	@Test
	public void findAllJobTitles() {
		List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobs);
		assertEquals(1, jobs.size());
	}
	
	@Test
	public void findJobTitleByID() {
		JobTitle jobTitle = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(jobTitle);
		assertEquals(Entities.JOB_TITLE_ID, jobTitle.getId());
	}
	
	@Test
	public void storeJobTitle() {
		Integer jobTitleId = this.jobTitleService.storeDepartment(this.mockJobTitle).getId();
		assertNotNull(jobTitleId);
		assertEquals(Entities.JOB_TITLE_ID, jobTitleId);
	}
}
