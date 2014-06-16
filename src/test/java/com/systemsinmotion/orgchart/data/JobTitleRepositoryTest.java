package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

	private static final Integer NOT_PRESENT_ID = -666;

	private JobTitle jobTitle;

	@Autowired
	JobTitleRepository jobTitleRepo;

	@Before
	public void before() throws Exception {
		this.jobTitle = Entities.jobTitle();
		this.jobTitle = this.jobTitleRepo.saveAndFlush(jobTitle);
	}

	@Test
	public void testInstantiation() {
		assertNotNull(this.jobTitleRepo);
	}
	
	@Test
	public void created() {
		assertNotNull(this.jobTitle);
		assertNotNull(this.jobTitle.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateName() throws Exception {
		JobTitle dept = Entities.jobTitle();
		dept.setName(this.jobTitle.getName());
		this.jobTitleRepo.save(dept);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.jobTitleRepo.toString());
		List<JobTitle> depts = this.jobTitleRepo.findAll();
		assertNotNull(depts);
		assertTrue(0 < depts.size());
	}

	@Test
	public void findByDeptId() throws Exception {
		JobTitle dept = this.jobTitleRepo.findById(this.jobTitle.getId());
		assertNotNull(dept);
		assertEquals(this.jobTitle.getName(), dept.getName());
	}

	@Test
	public void findByDeptId_null() throws Exception {
		JobTitle dept = this.jobTitleRepo.findById(NOT_PRESENT_ID);
		assertNull(dept);
	}

	@Test
	public void findByName() throws Exception {
		JobTitle dept = this.jobTitleRepo.findByName(this.jobTitle.getName());
		assertNotNull(dept);
		assertEquals(this.jobTitle.getName(), dept.getName());
	}

	@Test
	public void findByName_null() throws Exception {
		JobTitle dept = this.jobTitleRepo.findByName(NOT_PRESENT_VALUE);
		assertNull(dept);
	}

	@Test
	public void update() throws Exception {
		JobTitle dept = this.jobTitleRepo.findByName(this.jobTitle.getName());
		dept.setName(SOME_NEW_NAME);
		this.jobTitleRepo.saveAndFlush(dept);

		dept = null;
		dept = this.jobTitleRepo.findByName(SOME_NEW_NAME);
		assertNotNull(dept);
		assertEquals(SOME_NEW_NAME, dept.getName());
	}
}
