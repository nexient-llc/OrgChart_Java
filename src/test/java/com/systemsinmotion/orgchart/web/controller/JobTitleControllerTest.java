package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class JobTitleControllerTest {

	@Autowired
	JobTitleController controller;
	
	@Autowired
	private JobTitleService mockJobTitleService;

	@Autowired
	private JobTitle mockJobTitle;

	private List<JobTitle> findAllJobTitlesList;

	Model model = new ExtendedModelMap();

	@Test
	public void testInit() {
		assertNotNull(controller);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewJobTitleList() {
		// Given
		String viewName = this.controller.doJobTitles_GET(this.model);

		// When
		this.findAllJobTitlesList = (List<JobTitle>) (this.model.asMap().get("jobs"));
		// Then
		assertNotNull(this.findAllJobTitlesList);
		assertEquals(Entities.JOB_TITLE_ID, this.findAllJobTitlesList.get(0).getId());
		assertEquals(View.JOB_TITLES, viewName);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void setJobTitleService() {
		this.controller.setJobTitleService(mockJobTitleService);
		// Given
		String viewName = this.controller.doJobTitles_GET(this.model);

		// When
		this.findAllJobTitlesList = (List<JobTitle>) (this.model.asMap().get("jobs"));
		// Then
		assertNotNull(this.findAllJobTitlesList);
		assertEquals(Entities.JOB_TITLE_ID, this.findAllJobTitlesList.get(0).getId());
		assertEquals(View.JOB_TITLES, viewName);		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePagePost() {
		//Given
		controller.doJobTitleNew_POST(mockJobTitle, model);
		//When
		findAllJobTitlesList = (List<JobTitle>)model.asMap().get("jobs");

		//Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 1);
		JobTitle newJobTitle = findAllJobTitlesList.get(findAllJobTitlesList.size() - 1);
		assertEquals(Entities.JOB_TITLE_ID, newJobTitle.getId());
		assertEquals(newJobTitle.getName(), mockJobTitle.getName());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePageUpdate() {
		mockJobTitle.setName(Entities.JOB_TITLE_NAME + "T");
		//Given
		controller.doJobTitleUpdate_POST(mockJobTitle, model);
		//When
		findAllJobTitlesList = (List<JobTitle>)model.asMap().get("jobs");

		//Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 1);
		JobTitle newJobTitle = findAllJobTitlesList.get(findAllJobTitlesList.size() - 1);
		assertEquals(Entities.JOB_TITLE_ID, newJobTitle.getId());
		assertEquals(newJobTitle.getName(), mockJobTitle.getName());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void doJobTitleDelete_DELETE() {
		controller.doJobTitleDelete_DELETE(Entities.JOB_TITLE_ID, model);
		findAllJobTitlesList = (List<JobTitle>)model.asMap().get("jobs");
		
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.isEmpty());

		// Reset
		controller.doJobTitleNew_POST(mockJobTitle, model);
		findAllJobTitlesList = (List<JobTitle>)model.asMap().get("jobs");
		
		assertNotNull(findAllJobTitlesList);
		assertFalse(findAllJobTitlesList.isEmpty());		
	}

}
