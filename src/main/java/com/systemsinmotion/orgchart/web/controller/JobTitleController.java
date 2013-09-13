package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class JobTitleController {

	@Autowired
	JobTitleService jobTitleService;
	
	//Job Title methods

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", jobTitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "CreateJob", method = RequestMethod.POST)
	public ModelAndView doJobTitles_POST(@ModelAttribute("JOB_TITLE") JobTitle jobTitle, Integer number, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(View.JOB_TITLES);
		mav.addObject("jobs", jobTitles);
		mav.setViewName(View.JOB_TITLES);
		return mav;
	}

	@RequestMapping(value = "EditJob", method = RequestMethod.POST)
	public String doJobTitles_PUT(@ModelAttribute("JOB_TITLE") JobTitle jobTitle, Model model){
		//TODO: Go to a show page
		this.jobTitleService.updateJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}

	@RequestMapping(value = "RemoveJob", method = RequestMethod.POST)
	public String doJobTitles_DELETE(@ModelAttribute("JOB_TITLE") JobTitle jobTitle, Model model){
		this.jobTitleService.removeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
}
