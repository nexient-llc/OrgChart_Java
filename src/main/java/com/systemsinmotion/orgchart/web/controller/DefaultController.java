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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	private static final String REDIRECT = "redirect:/app/";

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

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(String string, String string2,
			String string3, String string4, Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		doJobTitles_GET(model);
		doDepartments_GET(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobTitles);
		return View.JOB_TITLES;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model) {
		this.departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return REDIRECT + View.DEPARTMENTS;
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model) {
		this.employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return REDIRECT + View.EMPLOYEES;
	}

	@RequestMapping(value = "titles", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model) {
		this.jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobTitles);
		return REDIRECT + View.JOB_TITLES;
	}

	// Code Spike AJAX
	@RequestMapping(value = "depts/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDeptAjax(@PathVariable("id") Integer id) {
		if (id == null) {
			return "";
		}
		Department dept = departmentService.findDepartmentByID(id);
		return dept.toString();
	}

}
