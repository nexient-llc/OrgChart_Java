package com.systemsinmotion.orgchart.service;

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
import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional

public class JobTitleServiceTest 
{
	@Autowired
	JobTitleService jobTitleService;

	JobTitleDao mockJobTitleDao= mock(JobTitleDao.class);
	JobTitle mockJobTitle =mock(JobTitle.class);
		
	private ArrayList<JobTitle> foundJobTitles = new ArrayList<JobTitle>();
	
	@Before
	public void before ()
	{
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.foundJobTitles.add(mockJobTitle);
		when(this.mockJobTitleDao.queryAll()).thenReturn(this.foundJobTitles);
		when(this.mockJobTitleDao.queryById(Entities.JOB_TITLE_ID)).thenReturn(mockJobTitle);
		when(this.mockJobTitleDao.create(this.mockJobTitle)).thenReturn(Entities.JOB_TITLE_ID);
	
		this.jobTitleService.setJobTitleDAO(this.mockJobTitleDao);		
	}
	
	@Test
	public void findAllJobTitles() 
	{
		List<JobTitle> tempJobs = this.jobTitleService.findAllJobTitles();
		assertNotNull(tempJobs);
		assertEquals(1, this.foundJobTitles.size());		
	}

	@Test
	public  void findJobTitleByID() 
	{		
		JobTitle tempTitle= this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(tempTitle);
		assertEquals(tempTitle.getId(),Entities.JOB_TITLE_ID );		
	}
		
	@Test
	public void storeJobTitle()
	{
		Integer tempInt=this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(tempInt);
		assertEquals(tempInt, Entities.JOB_TITLE_ID);				
	}

}
