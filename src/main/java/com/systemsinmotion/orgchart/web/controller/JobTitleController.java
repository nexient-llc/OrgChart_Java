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
	private JobTitleService jobTitleService;

	@RequestMapping(value = "jobtitles", method = RequestMethod.GET)
	public String doJobTitles(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
}
