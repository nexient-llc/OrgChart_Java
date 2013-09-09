package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
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
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Integer number, Model model){
		//TODO
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model){
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Integer number, Model model){
		//TODO
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Integer number, Model model){
		//TODO
		return View.JOB_TITLES;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
}
