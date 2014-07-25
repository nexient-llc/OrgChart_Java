package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class JobTitleControllerTest {

	@Autowired
	private JobTitleController jobTitleController;

	@Autowired
	private JobTitle mockJobTitle;

	Model model = new ExtendedModelMap();

	private List<JobTitle> findAllJobTitlesList;

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewJobTitleList() {
		// Given
		@SuppressWarnings("unused")
		String viewName = this.jobTitleController.doJobTitles_GET(this.model);

		// When
		this.findAllJobTitlesList = (ArrayList<JobTitle>) (this.model.asMap()
				.get("titles"));
		// Then
		assertNotNull(this.findAllJobTitlesList);
		assertEquals(Entities.JOB_TITLE_ID, this.findAllJobTitlesList.get(0)
				.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePagePost() {

		model.addAttribute("titles", findAllJobTitlesList);
		// Given
		jobTitleController.doJobTitles_POST(mockJobTitle, model);
		// When
		findAllJobTitlesList = (ArrayList<JobTitle>) model.asMap()
				.get("titles");

		// Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 1);
		assertEquals(Entities.JOB_TITLE_ID, findAllJobTitlesList.get(1).getId());
		assertEquals(findAllJobTitlesList.get(1).getName(),
				mockJobTitle.getName());

	}

}
