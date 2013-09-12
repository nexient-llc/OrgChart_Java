package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JobTitleService jobTitleService;
	

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	//employee methods
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model){
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "empCreate", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Integer number, Model model){
		employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "empEdit", method = RequestMethod.POST)
	public String doEmployees_PUT(Employee employee, Model model){
		//TODO: have it go to a show page
		employeeService.updateEmployee(employee);
		return doEmployees_GET(model);
	}
	
	@RequestMapping(value = "empRemove", method = RequestMethod.POST)
	public String doEmployee_DELETE(Employee employee, Model model){
		employeeService.removeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	//job Title methods
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Integer number, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobEdit", method = RequestMethod.POST)
	public String doJobTitles_PUT(JobTitle jobTitle, Model model){
		//TODO: Go to a show page
		this.jobTitleService.updateJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	@RequestMapping(value = "jobRemove", method = RequestMethod.POST)
	public String doJobTitles_DELETE(JobTitle jobTitle, Model model){
		this.jobTitleService.removeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}

	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
}
