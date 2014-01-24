package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@WebAppConfiguration("/src/main/webapp")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class JobTitleRepositoryTest {

	private static Random random = new Random();

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private JobTitle jobTitle;
	private JobTitle parent;

	@Autowired
	JobTitleRepository repository;

	@Before
	public void before() throws Exception {
		this.jobTitle = Entities.jobTitle();
		this.jobTitle = this.repository.save(this.jobTitle);
	}

    @Test
    public void testInstantiation() {
        assertNotNull(repository);
    }

	@Test
	public void created() {
		assertNotNull(this.jobTitle);
		assertNotNull(this.jobTitle.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateName() throws Exception {
		JobTitle jobTitle1 = Entities.jobTitle();
		jobTitle1.setName(this.jobTitle.getName());
		this.repository.save(jobTitle1);
	}

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.repository.toString());
		List<JobTitle> jobTitles = this.repository.findAll();
		assertNotNull(jobTitles);
		assertTrue(0 < jobTitles.size());
	}

	@Test
	public void findByJobTitleId() throws Exception {
		JobTitle jobTitle1 = this.repository.findOne(this.jobTitle.getId());
		assertNotNull(jobTitle1);
		assertEquals(this.jobTitle.getName(), jobTitle1.getName());
		//assertNotNull(this.jobTitle.getParentDepartment());
	}

	@Test
	public void findById_notPresent() throws Exception {
		JobTitle jobTitle1 = this.repository.findOne(NOT_PRESENT_ID);
		assertNull(jobTitle1);
	}

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findById_null() throws Exception {
        JobTitle jobTitle1 = this.repository.findOne(null);
        assertNull(jobTitle1);
    }

	@Test
	public void findByName() throws Exception {
		JobTitle jobTitle1 = this.repository.findByName(this.jobTitle.getName());
		assertNotNull(jobTitle1);
		assertEquals(this.jobTitle.getName(), jobTitle1.getName());
		//assertNotNull(this.jobTitle.getParentDepartment());
	}

	@Test
	public void findByName_null() throws Exception {
		JobTitle jobTitle1 = this.repository.findByName(NOT_PRESENT_VALUE);
		assertNull(jobTitle1);
	}

//	@Test
//	public void findByParentDeptId() throws Exception {
//		List<Department> depts = this.repository
//				.findByParentDepartmentId(this.jobTitle.getParentDepartment()
//                        .getId());
//		assertNotNull(depts);
//		assertEquals(1, depts.size());
//		Department dept = depts.get(0);
//		assertEquals(this.jobTitle.getName(), dept.getName());
//		assertNotNull(this.jobTitle.getParentDepartment());
//	}
//
//	@Test
//	public void findByParentDeptId_unknowId() throws Exception {
//		List<Department> depts = this.repository
//				.findByParentDepartmentId(random.nextInt());
//		assertNotNull(depts);
//		assertEquals(0, depts.size());
//	}
//
	@Test
	public void update() throws Exception {
		JobTitle jobTitle1 = this.repository.findByName(this.jobTitle.getName());
		jobTitle1.setName(SOME_NEW_NAME);
		this.repository.saveAndFlush(jobTitle1);

		jobTitle1 = null;
		jobTitle1 = this.repository.findByName(SOME_NEW_NAME);
		assertNotNull(jobTitle1);
		assertEquals(SOME_NEW_NAME, jobTitle1.getName());
	}
}
