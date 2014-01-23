package com.systemsinmotion.orgchart.data;

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
    	assertNotNull(jobTitleRepo);
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
    	this.jobTitleRepo.save(jobTitle);
    }
    
    @Test
    public void findAll_notNull() throws Exception {
    	System.out.println(this.jobTitleRepo.toString());
    	List<JobTitle> jobTitle = this.jobTitleRepo.findAll();
    	assertNotNull(jobTitle);
    	assertTrue(0 < jobTitle.size());
    }
    
    @Test
    public void findByJobTitleId() throws Exception {
    	JobTitle jobTitle = this.jobTitleRepo.findOne(NOT_PRESENT_ID);
    	assertNull(jobTitle);
    }
    
    /*
    @Test
    public void findByName() throws Exception {
    	JobTitle jobTitle = this.jobTitleRepo.findJobTitleByName(this.jobTitle.getName());
    	assertNotNull(jobTitle);
    	assertEquals(this.jobTitle.getName(), jobTitle.getName());
    }
    
    @Test
    public void findByName_null() throws Exception {
    	JobTitle jobTitle = this.jobTitleRepo.findJobTitleByName(NOT_PRESENT_VALUE);
    	assertNull(jobTitle);
    }
    
    @Test
    public void update() throws Exception {
    	JobTitle jobTitle = this.jobTitleRepo.findJobTitleByName(this.jobTitle.getName());
    	jobTitle.setName(SOME_NEW_NAME);
    	this.jobTitleRepo.save(jobTitle);
    	
    	jobTitle = null;
    	jobTitle = this.jobTitleRepo.findJobTitleByName(SOME_NEW_NAME);
    	assertNotNull(jobTitle);
    	assertEquals(SOME_NEW_NAME, jobTitle.getName());
    }
    */
}
