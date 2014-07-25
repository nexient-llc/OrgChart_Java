package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class JobTitleController {

	@Autowired
	JobTitleService jobTitleService;

	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobs);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "titles", method = RequestMethod.POST)
	public void doJobTitles_POST(JobTitle mockJobTitle, Model model) {

		jobTitleService.storeJobTitle(mockJobTitle);
		List<JobTitle> titles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", titles);
	}

}
