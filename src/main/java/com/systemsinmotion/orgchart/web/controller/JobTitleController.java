package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class JobTitleController {

	@Autowired
	JobTitleService jobTitleService;

	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model, HttpServletRequest request) {
		List<JobTitle> jobs;
		if (request.isUserInRole("ADMIN")) {
			jobs = jobTitleService.findAllJobTitles();
		} else {
			jobs = jobTitleService.findAllJobTitles();
		}
		model.addAttribute("titles", jobs);
		return View.JOB_TITLES;
	}

	@PreAuthorize("ADMIN")
	@RequestMapping(value = "title", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model,
			HttpServletRequest request,
			@RequestParam(value = "isActive", required = false) Object active) {

		if (jobTitle.getId() == null) {
			jobTitleService.storeJobTitle(jobTitle);
		} else {
			doJobTitles_EDIT(jobTitle, model, active);
		}
		List<JobTitle> titles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", titles);
		return "redirect:titles";
	}

	// auto fill for edit employee text boxes
	@RequestMapping(value = "findJobTitle", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_EMP(Integer id) {

		Gson json = new Gson();
		return json.toJson(jobTitleService.findJobTitleByID(id));

	}

	private void doJobTitles_EDIT(JobTitle jobTitle, Model model, Object active) {

		jobTitleService.editJobTitle(jobTitle, active);
	}

}
