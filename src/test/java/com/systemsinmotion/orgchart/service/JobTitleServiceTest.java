package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.JobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class JobTitleServiceTest {

    @Autowired
    JobTitleService jobTitleService;

    // @Autowired
    // JobTitleDAO jobTitleDAO;

    JobTitleDAO mockJobTitleDAO = mock(JobTitleDAO.class);
    JobTitle mockJobTitle = mock(JobTitle.class);

    private ArrayList<JobTitle> findAllJobTitleList = new ArrayList<JobTitle>();

    @Before
    public void before() throws Exception {
	// set up mock JobTitle
	when(mockJobTitle.getJobTitleId()).thenReturn(TestObject.JOB_TITLE_ID);
	when(mockJobTitle.getDescription()).thenReturn(TestObject.JOB_TITLE);

	findAllJobTitleList.add(mockJobTitle);

	// set up mock JobTitleDAO
	when(mockJobTitleDAO.findAll()).thenReturn(findAllJobTitleList);
	when(mockJobTitleDAO.findByJobTitleID(TestObject.JOB_TITLE_ID))
		.thenReturn(mockJobTitle);
	when(mockJobTitleDAO.save(mockJobTitle)).thenReturn(
		TestObject.JOB_TITLE_ID);
	// replace the Autowired Service DAO with the mock DAO
	jobTitleService.setJobTitleDAO(mockJobTitleDAO);
    }

    @Test
    @Rollback
    public void shouldFindAllJobTitles() {
	List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
	assertNotNull(jobTitles);
	assertTrue(0 < jobTitles.size());
    }

    @Test
    @Rollback
    public void shouldFindJobTitlesByID() {
	assertNotNull(jobTitleService.findJobTitleByID(TestObject.JOB_TITLE_ID));
	assertEquals(TestObject.JOB_TITLE,
		jobTitleService.findJobTitleByID(TestObject.JOB_TITLE_ID)
			.getDescription());
    }

    @Test
    @Rollback
    public void shouldCallJobTitleDAOSaveMethod() {
	assertEquals(TestObject.JOB_TITLE_ID,
		jobTitleService.storeJobTitle(mockJobTitle));
    }
}
