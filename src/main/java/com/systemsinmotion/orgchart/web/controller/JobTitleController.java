package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class JobTitleController {

	@Autowired
	JobTitleService jobTitleService;

	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String doJobTitles_GET(Model model) {
		refreshJobTitleModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "newJob", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public RedirectView doJobTitleNew_POST(JobTitle jobTitle, Model model, RedirectAttributes ra) {
		JobTitle newJobTitle = jobTitleService.storeJobTitle(jobTitle);
		ra.addFlashAttribute("createdJob", newJobTitle);
		refreshJobTitleModel(model);
		RedirectView rv = new RedirectView(View.JOB_TITLES);
		return rv;
	}

	@RequestMapping(value = "updateJob", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doJobTitleUpdate_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		refreshJobTitleModel(model);
		return "redirect:" + View.JOB_TITLES;
	}
	
	@RequestMapping(value = "findJob", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean doJobTitleFind_GET(@RequestParam("name") String name, @RequestParam(value = "id", defaultValue = "-1") Integer currentId) {
		List<JobTitle> jobs = jobTitleService.findJobTitleByName(name);
		if (currentId != -1) {
			jobs.removeIf(p -> p.getId().equals(currentId));
		}
		if (jobs.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping(value = "job/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody ResponseEntity<String> doJobTitleDelete_DELETE(@PathVariable("id") Integer jobId, Model model)
	{
		jobTitleService.removeJobTitleById(jobId);
		refreshJobTitleModel(model);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "getJobTitles", method = RequestMethod.GET)
	public @ResponseBody Page<JobTitle> doJobTitless_ajax_GET(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
		return jobTitleService.findAllActiveJobTitles(new PageRequest(page, 5, new Sort(new Sort.Order(Sort.Direction.ASC, "name").ignoreCase())));
	}


	private void refreshJobTitleModel(Model model) {
		List<JobTitle> titles = jobTitleService.findAllActiveJobTitles();
		model.addAttribute("jobs", titles);
	}
}
