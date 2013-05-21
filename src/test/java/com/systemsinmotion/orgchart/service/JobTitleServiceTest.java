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
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.dao.IJobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;

	IJobTitleDAO mockJobTitleDAO = mock(IJobTitleDAO.class);
	JobTitle mockJobTitle = mock(JobTitle.class);

	private ArrayList<JobTitle> listOfFoundJobTitles = new ArrayList<JobTitle>();

	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundJobTitles.add(this.mockJobTitle);
		when(this.mockJobTitleDAO.findAll()).thenReturn(this.listOfFoundJobTitles);
		when(this.mockJobTitleDAO.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleDAO.save(this.mockJobTitle)).thenReturn(Entities.JOB_TITLE_ID);
		this.jobTitleService.setJobTitleDAO(this.mockJobTitleDAO);
	}

	@Test
	public void findAllJobTitles() {
		List<JobTitle> titles = this.jobTitleService.findAllJobTitles();
		assertNotNull(titles);
		assertEquals(1, titles.size());
	}

	@Test
	public void findJobTitleByID() {
		JobTitle titles = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(titles);
		assertEquals(Entities.JOB_TITLE_ID, titles.getId());
	}

	@Test
	public void storeJobTitle() {
		Integer titleId = this.jobTitleService.storeJobTitle(this.mockJobTitle);
		assertNotNull(titleId);
		assertEquals(Entities.JOB_TITLE_ID, titleId);
	}

}
