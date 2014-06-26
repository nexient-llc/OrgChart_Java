package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		//uncomment when database connection is set up. will throw error when run
		 List<Department> departments = departmentService.findAllDepartments();
		 model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public void doDepartments_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		
	}
	
	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 List<Employee> employees = employeeService.findAllEmployees();
		 model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public void doEmployees_POST(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		
	}
	
	@RequestMapping(value = "jobTitles", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		 model.addAttribute("jtitles", jobTitles);
		 return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobTitles", method = RequestMethod.POST)
	public void doJobTitles_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jtitles", jobTitles);
		
	}
	
}
