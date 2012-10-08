package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JobTitleDAOTest {

	@Autowired
	JobTitleDAO jobTitleDAO;

	
	JobTitle jobTitle;

	@Before
	public void before() throws Exception {
		jobTitle = TestObject.jobTitle();
		jobTitle.setJobTitleId(jobTitleDAO.save(jobTitle));
	}

	@After
	public void after() throws Exception {
		jobTitleDAO.delete(jobTitle);
	}

	
	
	
	@Test
	@Rollback
	public void updateTest() throws Exception {
	    jobTitle.setDescription("SOMETITLE");
	    jobTitleDAO.update(jobTitle);
		
	    JobTitle ajobTitle = jobTitleDAO.findByJobTitleID(jobTitle.getJobTitleId());
	    assertNotNull(ajobTitle);
	    assertEquals(ajobTitle.getDescription(), jobTitle.getDescription());
	}

	@Test
	@Rollback
	public void findAll() throws Exception {
		List<JobTitle> jobTitles = jobTitleDAO.findAll();
		assertNotNull(jobTitles);
		assertTrue(jobTitles.size() > 0);
	}
	
	@Test
	@Rollback
	public void findByDescription() throws Exception {
		JobTitle jobTitle = jobTitleDAO.findByDescription(TestObject.JOB_TITLE);
		assertNotNull(jobTitle);
		assertEquals(jobTitle.getDescription(), this.jobTitle.getDescription());
	}
	
	@Test
	@Rollback
	public void findByID() throws Exception {
	    JobTitle newJobTitle = jobTitleDAO.findByJobTitleID(jobTitle.getJobTitleId());
	    assertEquals(jobTitle.getDescription(), newJobTitle.getDescription());
	}
}
