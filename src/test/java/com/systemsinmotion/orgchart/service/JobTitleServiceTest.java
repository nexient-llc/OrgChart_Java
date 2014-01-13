package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@Ignore
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;

	JobTitleRepository mockJobTitleRepo = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);

	private ArrayList<JobTitle> listOfFoundDepts = new ArrayList<JobTitle>();

	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundDepts.add(this.mockJobTitle);
		when(this.mockJobTitleRepo.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockJobTitleRepo.findById(Entities.JOB_TITLE_ID)).thenReturn(
				this.mockJobTitle);
		when(this.mockJobTitleRepo.save(this.mockJobTitle)).thenReturn(
				mockJobTitle);
		this.jobTitleService.setJobTitleService(this.mockJobTitleRepo);
	}

	@Test
	public void findAllJobTitles() {
		List<JobTitle> jerbs = this.jobTitleService.findAllJobTitles();
		assertNotNull(jerbs);
		assertEquals(1, jerbs.size());
	}

	@Test
	public void findJobTitleByID() {
		JobTitle jerb = this.jobTitleService
				.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(jerb);
		assertEquals(Entities.JOB_TITLE_ID, jerb.getId());
	}

	@Test
	public void storeJobTitle() {
		JobTitle deptId = this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(deptId);
		assertEquals(Entities.DEPT_ID, deptId);
	}

}
