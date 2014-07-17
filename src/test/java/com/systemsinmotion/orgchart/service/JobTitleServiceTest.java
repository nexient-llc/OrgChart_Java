package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.systemsinmotion.orgchart.entity.Department;
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
		JobTitle title = this.titleService.findJobTitleByID(Entities.JOB_TITLE_ID);		
		title.setIsActive(true);
	}
	
	@Test
	public void findAllJobTitles() {
		List<JobTitle> titles = this.titleService.findAllJobTitles();
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
	}
	
	@Test
	public void storeJobTitle() {
		JobTitle title = this.titleService.storeJobTitle(this.mockTitle);
		assertNotNull(title);
		assertEquals("Expected " + this.mockTitle.getName() + " but got " + title.getName(),
				this.mockTitle.getName(), title.getName());
	}
	
	@Test
	public void findAllActiveJobTitles() {
		List<JobTitle> title = this.titleService.findAllActiveJobTitles();
		assertNotNull(title);
		assertTrue(title.size() > 0);
	}

	@Test
	public void findJobTitleByID() {
		JobTitle title = this.titleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(title);
		assertEquals(Entities.JOB_TITLE_ID, title.getId());
	}

	@Test
	public void removeJobTitle() {
		JobTitle title = this.titleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertTrue(title.getIsActive());
		this.titleService.removeJobTitle(title);
		assertFalse(title.getIsActive());
	}

	@Test
	public void removeJobTitleById() {
		JobTitle title = this.titleService.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertTrue(title.getIsActive());
		this.titleService.removeJobTitleById(Entities.JOB_TITLE_ID);
		assertFalse(title.getIsActive());
	}
	
	@Test
	public void setAndGetRepository() {
		JobTitleRepository repo = this.titleService.getRepository();
		assertNotNull(repo);
		this.titleService.setRepository(null);
		assertNull(this.titleService.getRepository());
		this.titleService.setRepository(repo);
	}

}
