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
import com.systemsinmotion.orgchart.entity.JobTitle;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration("/src/main/webapp")
public class JobTitleServiceTest {
	
	@Autowired
	JobTitleService jobTitleService;
	
	JobTitleRepository mockJobTitleRepo = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);
	JobTitleService mockJobTitleService = mock(JobTitleService.class);
	
	private ArrayList<JobTitle> listOfFoundJobTitles = new ArrayList<JobTitle>();
	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobTitles.add(this.mockJobTitle);
		when(this.mockJobTitleRepo.findAll()).thenReturn(this.listOfFoundJobTitles);
		when(this.mockJobTitleRepo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		//when(this.mockJobTitleRepo.findJobTitleByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepo.findJobTitleByIsActiveTrue()).thenReturn(this.listOfFoundJobTitles);
		when(this.mockJobTitleRepo.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
		this.jobTitleService.setRepository(this.mockJobTitleRepo);
	}
	
	@Test
	public void findAllJobTitles() {
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobTitles);
		assertEquals(1, jobTitles.size());
	}
	
	@Test
	public void findJobTitleByID() {
		JobTitle jobTitle = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(jobTitle);
		assertEquals(Entities.JOB_TITLE_ID, jobTitle.getId());
	}
	
	@Test
	public void findJobTitleByIsActiveTrue() {
		List<JobTitle> jobTitles = this.jobTitleService.findJobTitleByIsActiveTrue();
		assertNotNull(jobTitles);
		assertEquals(1, jobTitles.size());
	}
	
	/*
	@Test
	public void findJobTitleByName() {
		JobTitle jobTitle = this.jobTitleService.findJobTitleByName(Entities.JOB_TITLE_NAME);
		assertNotNull(jobTitle);
		assertEquals(Entities.JOB_TITLE_ID, jobTitle.getId());
	}*/
	
	@Test
	public void storeJobTitle() {
		Integer jobTitleId = this.jobTitleService.storeJobTitle(this.mockJobTitle).getId();
		assertNotNull(jobTitleId);
		assertEquals(Entities.JOB_TITLE_ID, jobTitleId);
	}
}

