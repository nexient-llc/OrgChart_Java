package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
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
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleDaoTest {

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private JobTitle jobTitle;

	@Autowired
	IJobTitleDao jobTitleDao;

	@After
	public void after() {
		this.jobTitleDao.delete(this.jobTitle);
	}

	@Before
	public void before() throws Exception {
		this.jobTitle = Entities.jobTitle();
		this.jobTitle.setId(this.jobTitleDao.save(this.jobTitle));
	}

	@Test
	public void created() {
		assertNotNull(this.jobTitle);
		assertNotNull(this.jobTitle.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateName() throws Exception {
		JobTitle jobTitle = Entities.jobTitle();
		jobTitle.setName(this.jobTitle.getName());
		this.jobTitleDao.save(jobTitle);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.jobTitleDao.toString());
		List<JobTitle> jobTitles = this.jobTitleDao.findAll();
		assertNotNull(jobTitles);
		assertTrue(0 < jobTitles.size());
	}

	@Test
	public void findByDeptId() throws Exception {
		JobTitle jobTitle = this.jobTitleDao.findById(this.jobTitle.getId());
		assertNotNull(jobTitle);
		assertEquals(this.jobTitle.getName(), jobTitle.getName());
	}

	@Test
	public void findByDeptId_null() throws Exception {
		JobTitle jobTitle = this.jobTitleDao.findById(NOT_PRESENT_ID);
		assertNull(jobTitle);
	}

	@Test
	public void findByName() throws Exception {
		JobTitle jobTitle = this.jobTitleDao
				.findByName(this.jobTitle.getName());
		assertNotNull(jobTitle);
		assertEquals(this.jobTitle.getName(), jobTitle.getName());
	}

	@Test
	public void findByName_null() throws Exception {
		JobTitle jobTitle = this.jobTitleDao.findByName(NOT_PRESENT_VALUE);
		assertNull(jobTitle);
	}

	@Test
	public void update() throws Exception {
		JobTitle jobTitle = this.jobTitleDao
				.findByName(this.jobTitle.getName());
		jobTitle.setName(SOME_NEW_NAME);
		this.jobTitleDao.update(jobTitle);

		jobTitle = null;
		jobTitle = this.jobTitleDao.findByName(SOME_NEW_NAME);
		assertNotNull(jobTitle);
		assertEquals(SOME_NEW_NAME, jobTitle.getName());
	}
}
