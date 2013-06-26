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
import org.springframework.transaction.annotation.Transactional;
import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;
	JobTitle mockJobTitle = mock(JobTitle.class);
	IJobTitleDao mockJobTitleDao = mock(IJobTitleDao.class);
	
	private ArrayList<JobTitle> listOfFoundJobs = new ArrayList<JobTitle>();
	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobs.add(this.mockJobTitle);
		when(this.mockJobTitleDao.findAll()).thenReturn(this.listOfFoundJobs);
		when(this.mockJobTitleDao.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleDao.save(this.mockJobTitle)).thenReturn(Entities.JOB_TITLE_ID);
		when(this.mockJobTitleDao.findByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitle.getName()).thenReturn(Entities.JOB_TITLE_NAME);
		this.jobTitleService.setJobTitleDao(this.mockJobTitleDao);
	}
	
	@Test
	public void findAllJobTitles() {
		List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobs);
		assertEquals(1, jobs.size());
	}

	@Test
	public void findJobTitleByID() {
		JobTitle job = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(job);
		assertEquals(Entities.JOB_TITLE_ID, job.getId());
	}

	@Test
	public void storeDepartment() {
		Integer jobId = this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(jobId);
		assertEquals(Entities.JOB_TITLE_ID, jobId);
	}
	
	@Test
	public void findJobTitleByName(){
		JobTitle job = this.jobTitleService.findJobByName(Entities.JOB_TITLE_NAME);
		assertNotNull(job);
		assertEquals(Entities.JOB_TITLE_NAME, job.getName());
	}
	
	@Test
	public void removeJobTitle(){
		
	}
	
	@Test
	public void updateJobTitle() {
		
	}
	
}
