package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	
	// HOME
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	// DEPARTMENT
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department dept, Model model) {
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	

	// EMPLOYEE 
	
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value ="emps", method = RequestMethod.GET)
	public String doEmployee_GET(Model model){
		List<Employee> employee = employeeService.findAllEmployees();
		model.addAttribute("emps", employee);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value="emps", method= RequestMethod.POST)
	public String doEmployee_POST(Employee employee, Model model){
		employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return View.EMPLOYEES;
		
	}
	
	// JOBS
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value ="jobAdd", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle job, Model model){
		jobTitleService.storeJobTitle(job);
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value="jobUpdate", method = RequestMethod.PUT)
	public String doJobTitle_PUT(JobTitle job, Model model){
		jobTitleService.updateJobTitle(job);
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobDelete", method = RequestMethod.DELETE)
	public String doJobTitle_DELETE(JobTitle job, Model model){
		jobTitleService.removeJobTitle(job);
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}

}