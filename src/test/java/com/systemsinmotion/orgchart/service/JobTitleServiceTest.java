package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class JobTitleServiceTest {

	@Autowired
	private JobTitleService titleService;

	@Autowired
	private JobTitle mockTitle;

	@Before
	public void before() throws Exception {

	}

	@Test
	public void findAllJobTitles() {
		List<JobTitle> titles = this.titleService.findAllJobTitles();
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
	}

	@Test
	public void findAllActiveJobTitles() {
		List<JobTitle> titles = this.titleService.findAllActiveJobTitles();
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
	}

	@Test
	public void findByName() throws Exception {
		JobTitle jobTItle = titleService.findByName(mockTitle.getName());
		assertNotNull(jobTItle);
		assertEquals(mockTitle.getName(), jobTItle.getName());
	}

	@Test
	public void findById() throws Exception {
		Integer mockId = mockTitle.getId();
		JobTitle jobTitle = titleService.findbyID(mockId);
		assertNotNull(jobTitle);
		assertEquals(mockId, jobTitle.getId());
	}

	@Test
	public void storeJobTitle() {
		JobTitle title = this.titleService.saveJobTitle(this.mockTitle);
		assertNotNull(title);
		assertEquals("Expected " + this.mockTitle.getName() + " but got "
				+ title.getName(), this.mockTitle.getName(), title.getName());
	}

}
