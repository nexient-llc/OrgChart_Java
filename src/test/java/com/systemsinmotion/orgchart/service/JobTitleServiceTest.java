package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
	private JobTitleService titleService;

	@Autowired
	private JobTitle mockTitle;

	@Mock
	private JobTitleService service;

	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void findAllJobTitles() {
		List<JobTitle> titles = this.titleService.findAllJobTitles();
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
	}

	@Test
	public void findAllActiveJobTitles() {
		List<JobTitle> titles = this.titleService.activeJobTitles();
		assertNotNull(titles);
		assertTrue(titles.size() > 0);
	}

	@Test
	public void storeJobTitle() {
		JobTitle title = this.titleService.storeJobTitle(this.mockTitle);
		assertNotNull(title);
		assertEquals("Expected " + this.mockTitle.getName() + " but got "
				+ title.getName(), this.mockTitle.getName(), title.getName());
	}

	@Test
	public void findByID() {
		JobTitle titles = this.titleService
				.findJobTitleByID(Entities.JOB_TITLE_ID);
		assertNotNull(titles);
		assertTrue(Entities.JOB_TITLE_ID == titles.getId());
	}

	@Test
	public void getRepoTest() {
		JobTitleRepository titles = this.titleService.getRepository();
		assertNotNull(titles);
	}

	@Test
	public void titleExists() {
		Boolean exists = this.titleService.titleExists(mockTitle);
		assertTrue(exists == false);
	}

	@Test
	public void setRepo() {
		titleService.setRepository(service.getRepository());
	}

	@Test
	public void delete() {
		@SuppressWarnings("unused")
		JobTitle title = titleService.removeJobTitle(mockTitle);
		assertNotNull(title);

	}

}
