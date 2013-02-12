package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class JobTileDAOTest {
	@Autowired
	JobTitleDAO jobTitleDAO;
	private JobTitle testJobTitle;

	@Before
	public void before() throws Exception {
		testJobTitle = TestObject.jobTitle();
		int intJobTitle = jobTitleDAO.createJobTitle(testJobTitle);
		testJobTitle.setJobTitleID(intJobTitle);

	}
	
	@After
	public void after() {
		jobTitleDAO.deleteJobTitle(testJobTitle);
	}

	@Test
	@Rollback
	public void testfindByJobTitleID() {
		JobTitle actualJobTitle = jobTitleDAO.findByJobTitleID(testJobTitle.getJobTitleID());
		assertEquals(TestObject.JOB_TITLE, actualJobTitle.getDesc());
		assertTrue(TestObject.JOB_TITLE.equals(actualJobTitle.getDesc()));
	}

	@Test
	@Rollback
	public void testfindAllJobTitles() throws Exception {

		List<JobTitle> jobTitleList = jobTitleDAO.findAllJobTitles();
		assertNotNull(jobTitleList);
		assertTrue(jobTitleList.size() > 0);
		

	}

	@Test
	@Rollback
	public void testupdatejobTitle() {
		testJobTitle.setDesc("job title update");
		jobTitleDAO.updatejobTitle(testJobTitle);
		JobTitle updateJobTitle = jobTitleDAO.findByJobTitleID(testJobTitle
				.getJobTitleID());

		assertEquals("job title update", updateJobTitle.getDesc());

	}

}
