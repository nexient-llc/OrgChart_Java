package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.*;
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

	private ArrayList<JobTitle> listOfFoundJobs = new ArrayList<JobTitle>();
	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobs.add(this.mockJobTitle);
		when(this.mockJobTitleRepository.findAll()).thenReturn(this.listOfFoundJobs);
		when(this.mockJobTitleRepository.findByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepository.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepository.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
		this.jobTitleService.setRepository(mockJobTitleRepository);
	}
	
	@Test
	public void findAllJobTitles(){
		List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobs);
		assertEquals(1,jobs.size());
	}
	
	@Test
	public void findJobTitleByID(){
		JobTitle job = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(job);
		assertEquals(Entities.JOB_TITLE_ID, job.getId());
	}
	
	@Test
	public void storeJobTitle(){
		Integer jobId = this.jobTitleService.storeJobTitle(this.mockJobTitle).getId();
		assertNotNull(jobId);
		assertEquals(Entities.JOB_TITLE_ID, jobId);
	}
	
}
