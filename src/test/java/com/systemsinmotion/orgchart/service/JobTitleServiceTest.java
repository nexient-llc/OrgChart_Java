package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/test-context.xml")
@ContextConfiguration(classes = JPAConfig.class)
@WebAppConfiguration("/src/main/webapp")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleServiceTest {

	@Autowired
	JobTitleService jobTitleService;

	JobTitleRepository mockJobTitleRepo = mock(JobTitleRepository.class);
	JobTitle mockJobTitle = mock(JobTitle.class);

	private ArrayList<JobTitle> listOfFoundDepts = new ArrayList<JobTitle>();

	@Test
	public void bs() {
		assertTrue(true);
	}
	
	@Before
	public void before() throws Exception {
		when(this.mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		this.listOfFoundDepts.add(this.mockJobTitle);
		when(this.mockJobTitleRepo.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockJobTitleRepo.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(this.mockJobTitleRepo.save(this.mockJobTitle)).thenReturn(Entities.jobTitle());
		this.jobTitleService.setRepository(this.mockJobTitleRepo);
	}

	@Test
    public void findAllJobTitles() {
        List<JobTitle> titles = this.jobTitleService.findAllJobTitles();
        assertNotNull(titles);
        assertEquals(1, titles.size());
    }

	@Test
	public void findDepartmentByID() {
		JobTitle title = this.jobTitleService.findJobTitleById(Entities.JOB_TITLE_ID);
		assertNotNull(title);
		assertEquals(Entities.JOB_TITLE_ID, title.getId());
	}

    @Test
    public void storeDepartment() {
        JobTitle title = this.jobTitleService.storeJobTitle(this.mockJobTitle);
        assertNotNull(title);
        assertEquals(Entities.jobTitle().getId(), title.getId());

    }

}
