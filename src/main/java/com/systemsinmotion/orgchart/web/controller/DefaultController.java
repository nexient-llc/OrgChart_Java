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
		// uncomment when database connection is set up. will throw error when
		// run
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", new Department());
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department dept,
			Integer parentDepartmentId, Model model) {
		Department testDepartment = departmentService.findDepartmentByName(dept
				.getName());
		if (testDepartment != null) {
			dept = testDepartment;
		}

		if (parentDepartmentId != null) {
			Department parent = departmentService
					.findDepartmentById(parentDepartmentId);// dept.getParentDepartment();
			dept.setParentDepartment(parentDepartmentId == null ? null : parent);
		}
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Integer id, String name, Integer parentID, Model model) {
        Department dept = departmentService.findDepartmentById(id);
        dept.setName(name);
        dept.setParentDepartment(departmentService.findDepartmentById(parentID));
        departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String doLogin_GET(){
	// return View.LOGIN;
	// }
	//
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle job, Model model) {
		jobTitleService.storeJobTitle(job);
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(Integer id, String name, Model model) {
		JobTitle job = jobTitleService.findJobTitleById(id);
        job.setName(name);
        jobTitleService.storeJobTitle(job);
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}

	// @RequestMapping(value = "edit", method = RequestMethod.GET)
	// public String doEdit_GET(/*Model model*/){
	// return View.EDIT;
	// }
	//
	// @RequestMapping(value = "ajax", method = RequestMethod.GET)
	// public String doAjax_GET(){
	// return View.AJAX;
	// }
	
	 @RequestMapping(value = "emps", method = RequestMethod.GET)
	 public String doEmployees_GET(Model model){
		 List<Employee> emps = employeeService.findAllEmployees();
		 model.addAttribute("emps", emps);
		 return View.EMPLOYEES;
	 }

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
