package com.systemsinmotion.orgchart.service;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.JobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;

	JobTitleDAO mockJobTitleDAO = mock(JobTitleDAO.class);
	JobTitle mockJobTitle = mock(JobTitle.class);
	int id = TestObject.JOB_TITLE_ID;

	private ArrayList<JobTitle> findAllJobTitleList = new ArrayList<JobTitle>();

	@Before
	public void before() {

		// Mocking up Job Title
		when(mockJobTitle.getJobTitleID()).thenReturn(TestObject.JOB_TITLE_ID);
		when(mockJobTitle.getDesc()).thenReturn(TestObject.JOB_TITLE);

		// Mock up JobTitle DAO

		findAllJobTitleList.add(mockJobTitle);

		when(mockJobTitleDAO.findByJobTitleID(id)).thenReturn(mockJobTitle);
		when(mockJobTitleDAO.findAllJobTitles())
				.thenReturn(findAllJobTitleList);
		when(mockJobTitleDAO.createJobTitle(mockJobTitle)).thenReturn(
				TestObject.JOB_TITLE_ID);

		jobTitleService.setJobTitleDAO(mockJobTitleDAO);

	}

	@Test
	@Rollback
	public void testfindByJobTitleID() {

		assertEquals(mockJobTitle.getDesc(),
				jobTitleService.findByJobTitleID(id).getDesc());
	}

	@Test
	@Rollback
	public void testfindAllJobTitles() {
		List<JobTitle> test_JobTitles = jobTitleService.findAllJobTitles();
		assertTrue(test_JobTitles.size() > 0);

	}

	@Test
	@Rollback
	public void testcreateJobTitle() {
		
		assertEquals(TestObject.JOB_TITLE_ID, jobTitleService.createJobTitle(mockJobTitle) );

	}

}
