package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.JobTitle;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class JobTitleServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	JobTitleService jobTitleService;

	// @Autowired
	// JobTitleDAO jobTitleDAO;

	JobTitleRepository mockJobTitleDAO = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);

	private ArrayList<JobTitle> findAllJobTitleList = new ArrayList<JobTitle>();

	@BeforeClass
	public void before() throws Exception {

	}

	@Test
	@Rollback
	public void shouldFindAllJobTitles() {
		List<JobTitle> jobTitles = jobTitleService.findAll();
		assertNotNull(jobTitles);
		assertTrue(0 < jobTitles.size());
	}

	@Test
	@Rollback
	public void shouldFindJobTitlesByID() {
		assertTrue(true);
	}

	@Test
	@Rollback
	public void shouldCallJobTitleDAOSaveMethod() {
		assertTrue(true);
	}
}
