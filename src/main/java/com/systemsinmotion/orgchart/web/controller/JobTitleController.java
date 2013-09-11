package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("jobs")
public class JobTitleController {
	
	@Autowired
	private JobTitleService jobTitleService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		loadModel(model);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doJobTitles_POST(@ModelAttribute("jobTitle") JobTitle jobTitle, Model model) {
		this.jobTitleService.storeJobTitle(jobTitle);
		loadModel(model);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String doJobTitles_PUT(@ModelAttribute("jobTitle") JobTitle jobTitle, Model model) {
		this.jobTitleService.updateJobTitle(jobTitle);
		loadModel(model);
		return View.JOB_TITLES;
	}
	
	@ModelAttribute("jobTitle")
	private JobTitle getModelJobTitle() {
		return new JobTitle();
	}
	
	private void loadModel(Model model) {
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
	}
	
	public void setJobTitleService(JobTitleService mockJobTitleService) {
		this.jobTitleService = mockJobTitleService;
	}
	
}