package com.systemsinmotion.orgchart.dao;

import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.JobTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class JobTitleDAOTest {

	private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_PRESENT_ID = -666;
	
	@Autowired
	JobTitleDAO jobTitleDAO;

	JobTitle jobTitle;

	@Before
	public void before() throws Exception 
	{
		
		jobTitle = TestObject.jobTitle();
		jobTitle.setJobTitleID(jobTitleDAO.save(jobTitle));
		
	}

	@After
	public void after() throws Exception 
	{
		
		jobTitleDAO.delete(jobTitle);
	
	}

	@Test
	@Rollback
	public void updateTest() throws Exception 
	{
		
	    jobTitle.setDesc("New Job Title");
	    jobTitleDAO.update(jobTitle);
		
	    JobTitle jt = jobTitleDAO.findByJobTitleID(jobTitle.getJobTitleID());
	    assertThat(jt, is(not(nullValue())));
	    assertThat(jt, is(instanceOf(JobTitle.class)));
	    assertThat(jt, describedAs("desc property has value New Job Title", 
	    		hasProperty("desc", is(jobTitle.getDesc()))));
	    
	}

	@Test
	@Rollback
	public void findAllTest() throws Exception 
	{
		
		List<JobTitle> jt = jobTitleDAO.findAll();
		assertThat(jt, is(not(nullValue())));
		assertThat(jt, hasSize(greaterThan(0)));
		
		/* Due to some issues with the matchers in the current version of
		 * Hamcrest, I am unable to add in an assert, at this time, to verify our test
		 * object is returned in the list of returned objects.
		 */
		
	}
	
	@Test
	@Rollback
	public void findByDescription() throws Exception 
	{
		
		List<JobTitle> jts = jobTitleDAO.findByJobTitleDesc(TestObject.JOB_TITLE);
		assertThat(jts, is(List.class));
		assertThat(jts, is(not(nullValue())));
		assertThat(jts, hasSize(greaterThan(0)));
		assertThat(jts, contains(instanceOf(JobTitle.class)));
		
		JobTitle jt = jts.get(0);
		assertThat(jt.getDesc(), is(TestObject.JOB_TITLE));

	}
	
	@Test
	@Rollback
	public void findByDescription_noMatchFound() throws Exception 
	{
		
		List<JobTitle> jts = jobTitleDAO.findByJobTitleDesc(NOT_PRESENT_VALUE);
		assertThat(jts, is(List.class));
		assertThat(jts, is(not(nullValue())));
		assertThat(jts, hasSize(is(0)));
		
	}
	
	@Test
	@Rollback
	public void findByID() throws Exception 
	{
		
	    JobTitle jt = jobTitleDAO.findByJobTitleID(jobTitle.getJobTitleID());
	    assertThat(jt, is(not(nullValue())));
	    assertThat(jt, is(instanceOf(JobTitle.class)));
	    assertThat(jt, hasProperty("desc", is(jobTitle.getDesc())));
	    
	}
	
	@Test
	@Rollback
	public void findByID_noMatchFound() throws Exception 
	{
		
	    JobTitle jt = jobTitleDAO.findByJobTitleID(NOT_PRESENT_ID);
	    assertThat(jt, is(nullValue()));
	    
	}
	
}
