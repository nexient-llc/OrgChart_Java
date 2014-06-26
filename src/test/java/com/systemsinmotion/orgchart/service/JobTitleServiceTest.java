package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;

	JobTitleRepository mockJobTitRepo = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);

	private ArrayList<JobTitle> listOfFoundJobTits = new ArrayList<JobTitle>();

	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobTits.add(this.mockJobTitle);
		when(this.mockJobTitRepo.findAll()).thenReturn(this.listOfFoundJobTits);
		when(this.mockJobTitRepo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitRepo.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
	}

	@Test
	public void findAllJobTitles() {
		List<JobTitle> jobTits = this.jobTitleService.findAllJobTitles();
		assertNotNull(jobTits);
		assertTrue(jobTits.size()>0);
	}

	@Test
	public void findJobTitleByID() {
		JobTitle jobTit = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(jobTit);
		assertEquals(Entities.JOB_TITLE_ID, jobTit.getId());
	}
		
	@Test
	public void storeJobTitleByID() {
		JobTitle jobTit = this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(jobTit);
		assertEquals(Entities.JOB_TITLE_ID, jobTit.getId());
	}


}