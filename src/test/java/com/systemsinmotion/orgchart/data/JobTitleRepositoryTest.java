package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleRepositoryTest {
	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private JobTitle jobTitle;

	@Autowired
	JobTitleRepository jobTitleRepo;

	@Before
	public void before() throws Exception {
		this.jobTitle = Entities.jobTitle();
		this.jobTitle.setId(this.jobTitleRepo.save(this.jobTitle).getId());
	}
	
	@Test
	public void testInstantiation() {
		assertNotNull(jobTitleRepo);
	}
	
	@Test
	public void created() {
		assertNotNull(this.jobTitle);
		assertNotNull(this.jobTitle.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateName() throws Exception {
		JobTitle title = Entities.jobTitle();
		title.setName(this.jobTitle.getName());
		this.jobTitleRepo.save(title);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.jobTitleRepo.toString());
		List<JobTitle> titles = this.jobTitleRepo.findAll();
		assertNotNull(titles);
		assertTrue(0 < titles.size());
	}

	@Test
	public void findByName() throws Exception {
		JobTitle title = this.jobTitleRepo.findByName(this.jobTitle.getName());
		assertNotNull(title);
		assertEquals(this.jobTitle.getName(), title.getName());
	}

	@Test
	public void findByName_null() throws Exception {
		JobTitle title = this.jobTitleRepo.findByName(NOT_PRESENT_VALUE);
		assertNull(title);
	}

	@Test
	public void update() throws Exception {
		JobTitle title = this.jobTitleRepo.findByName(this.jobTitle.getName());
		title.setName(SOME_NEW_NAME);
		this.jobTitleRepo.save(title);

		title = null;
		title = this.jobTitleRepo.findByName(SOME_NEW_NAME);
		assertNotNull(title);
		assertEquals(SOME_NEW_NAME, title.getName());
	}
}
