package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("jobs")
public class JobTitleController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

	@Autowired
	JobTitleService jobTitleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		loadModelData(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doJobTitle_POST(@ModelAttribute("modelJob") JobTitle newJob, Model model) {
		jobTitleService.storeJobTitle(newJob);
		loadModelData(model);
		return View.JOB_TITLES;
	}

	// delete job with given id
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String doJobTitle_DELETE(@PathVariable Integer id, Model model) {
		JobTitle delJob = jobTitleService.findJobTitleByID(id);
		jobTitleService.removeJobTitle(delJob);
		loadModelData(model);
		return View.JOB_TITLES;
	}

	// load job with given id into the edit form
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String doEditJobTitle_GET(@PathVariable Integer id, Model model) {
		JobTitle updateJob = jobTitleService.findJobTitleByID(id);
		model.addAttribute("modelJob", updateJob);
		return View.EDIT_JOB_TITLE;
	}

	// save changes made in edit form, see above
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public String doJobTitle_UPDATE(@ModelAttribute("modelJob") @Valid JobTitle upJob, Model model) {
		jobTitleService.updateJobTitle(upJob);
		loadModelData(model);
		return View.JOB_TITLES;
	}

	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	private void loadModelData(Model model) {
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		JobTitle newJob = new JobTitle();
		model.addAttribute("jobs", jobs);
		model.addAttribute("modelJob", newJob);
	}

}
