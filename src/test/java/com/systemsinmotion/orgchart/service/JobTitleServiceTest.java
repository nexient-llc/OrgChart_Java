package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleServiceTest {
	
	@Autowired
	private JobTitleService jobTitleService;
	
	private IJobTitleDao mockJobTitleDao = mock(IJobTitleDao.class);
	private JobTitle mockJobTitle = mock(JobTitle.class);
	
	private ArrayList<JobTitle> listOfFoundJobTitles = new ArrayList<JobTitle>(0);
	
	@Before
	public void before() throws Exception {
		when(mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		when(mockJobTitle.getName()).thenReturn(Entities.JOB_TITLE_NAME);
		this.listOfFoundJobTitles.add(this.mockJobTitle);
		when(this.mockJobTitleDao.findAll()).thenReturn(listOfFoundJobTitles);
		when(this.mockJobTitleDao.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleDao.findByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleDao.save(this.mockJobTitle)).thenReturn(Entities.JOB_TITLE_ID);
		this.jobTitleService.setJobTitleDao(mockJobTitleDao);
		
	}
	
	@Test
	public void findAllJobTitles() {
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobTitles);
		assertEquals(1, jobTitles.size());
	}
	
	@Test
	public void findJobTitleById() {
		JobTitle jobTitle = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(jobTitle);
		assertEquals(Entities.JOB_TITLE_ID, jobTitle.getId());
	}
	
	@Test
	public void findJobTitleByName() {
		JobTitle jobTitle = this.jobTitleService.findJobTitleByName(Entities.JOB_TITLE_NAME);
		assertNotNull(jobTitle);
		assertEquals(Entities.JOB_TITLE_NAME, jobTitle.getName());
	}
	
	@Test
	public void storeJobTitle() {
		Integer jobTitleId = this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(jobTitleId);
		assertEquals(Entities.JOB_TITLE_ID, jobTitleId);
	}
	
	@Test
	public void updateJobTitle() {
		this.jobTitleService.updateJobTitle(this.mockJobTitle);
		Mockito.verify(this.mockJobTitleDao).update(this.mockJobTitle);
	}
	
	@Test
	public void removeJobTitle() {
		this.jobTitleService.removeJobTitle(this.mockJobTitle);
		Mockito.verify(this.mockJobTitleDao).delete(this.mockJobTitle);
	}
}
