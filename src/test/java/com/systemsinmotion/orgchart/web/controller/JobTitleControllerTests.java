package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class JobTitleControllerTests {
	
	@Autowired
	JobTitleController controller;
	
	JobTitle mockJobTitle = mock(JobTitle.class);
	JobTitleService mockJobTitleService = mock(JobTitleService.class);
	
	Model model;
	List<JobTitle> jobTitles;
	
	@Before
	public void before() {
		model = new ExtendedModelMap();
		jobTitles = new ArrayList<JobTitle>();
		
		when(mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		jobTitles.add(mockJobTitle);
		
		when(mockJobTitleService.findAllJobTitles()).thenReturn(jobTitles);
		controller.setJobTitleService(mockJobTitleService);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doJobTitles_GET() {
		String returnvalue = controller.doJobTitles_GET(model);
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		assertNotNull(returnvalue);
		assertNotNull(jobs);
		assertEquals(View.JOB_TITLES, returnvalue);
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doJobTitles_POST() {
		String returnvalue = controller.doJobTitles_POST(mockJobTitle, model);
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		Mockito.verify(mockJobTitleService).storeJobTitle(mockJobTitle);
		assertNotNull(returnvalue);
		assertNotNull(jobs);
		assertEquals(View.JOB_TITLES, returnvalue);
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doJobTitles_PUT() {
		String returnvalue = controller.doJobTitles_PUT(mockJobTitle, model);
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		Mockito.verify(mockJobTitleService).updateJobTitle(mockJobTitle);
		assertNotNull(returnvalue);
		assertNotNull(jobs);
		assertEquals(View.JOB_TITLES, returnvalue);
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
}
