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
	public String doDepartments_POST(String deptName,
			Integer parentDeptId, Model model) {

		// List<Department> depts = employeeService.findDepartments();
		// model.addAttribute(ModelKey.DEPARTMENTS, depts);
		return View.DEPARTMENTS;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setJobTitleSErvice(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	public String doHelloWorld(Model model) {
	    	    model.addAttribute("hello", "HelloWorld");
	    return "home";
	}

	public String doEmployees_GET(Model model) {
	    List<Employee> emps = this.employeeService.findAllEmployees();
	    model.addAttribute("emps", emps);
	    return "emps";
	}

	public String doJobTitle_Get(Model model) {
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    model.addAttribute("jobs", jobs);
	    return "jobs";
	}

}
