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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	
	private List<JobTitle> listOfJobTitles = new ArrayList<JobTitle>();
	
	@Before
	public void before() throws Exception{
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		when(this.mockJobTitle.getName()).thenReturn(Entities.JOB_TITLE_NAME);
		when(this.mockJobTitleRepo.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepo.findByName(Entities.JOB_TITLE_NAME)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepo.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
		listOfJobTitles.add(mockJobTitle);
		when(this.mockJobTitleRepo.findAll()).thenReturn(listOfJobTitles);
		jobTitleService.setRepository(mockJobTitleRepo);
	}
	
	@Test
	public void findJobTitleById() throws Exception {
		JobTitle jt = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(jt);
		assertEquals(jt.getId(), Entities.JOB_TITLE_ID);
	}
	
	@Test
	public void findJobTitleByName() throws Exception {
		JobTitle jt = this.jobTitleService.findJobTitleByName(Entities.JOB_TITLE_NAME);
		assertNotNull(jt);
		assertEquals(Entities.JOB_TITLE_NAME, jt.getName());
	}
	
	@Test
	public void storeJobTitle() throws Exception{
		Integer id = this.jobTitleService.storeJobTitle(this.mockJobTitle).getId();
		assertEquals(id,Entities.JOB_TITLE_ID);
	}
	
	@Test
	public void findAllJobTitles() throws Exception{
		List<JobTitle> jts = this.jobTitleService.findAllJobTitles();
		assertNotNull(jts);
		assertEquals(1, jts.size());
	}
}
