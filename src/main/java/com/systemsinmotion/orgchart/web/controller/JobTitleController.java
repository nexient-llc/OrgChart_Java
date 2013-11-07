package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	private JobTitleService jobTitleService;

	@RequestMapping(value = "job_titles", method = RequestMethod.GET)
	public String doJobTitles(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "job_titles/{id}/json", method = RequestMethod.GET)
	public @ResponseBody
	String doJobTitlePrefillForm_GET(@PathVariable Integer id) {
		JobTitle jobTitle = jobTitleService.findJobTitletByID(id);
		
		if(jobTitle != null) {
			Gson gson = new Gson();
			return gson.toJson(jobTitle);
		}
	
		return null;
	}

	@RequestMapping(value = "job_titles/edit", method = RequestMethod.PUT)
	String doJobTitleEdit_PUT(JobTitle jobTitle) {
		jobTitleService.updateJobTitle(jobTitle);
		return "redirect:../" + View.JOB_TITLES;
	}
	
	@RequestMapping(value = "job_titles/delete", method = RequestMethod.POST)
	public String doJobTitleRemove_POST(
			@RequestParam("deleteId") Integer deleteId,
			@RequestParam("confirmString") String confirmString)
	{
		jobTitleService.removeJobTitleConfirm(deleteId, confirmString);
		return "redirect:../" + View.JOB_TITLES;
	}

	@RequestMapping(value = "job_titles/add", method = RequestMethod.PUT)
	public String doJobTitleAdd_PUT(JobTitle jobTitle) {
		jobTitleService.storeJobTitle(jobTitle);
		return "redirect:../" + View.JOB_TITLES;
	}
}
