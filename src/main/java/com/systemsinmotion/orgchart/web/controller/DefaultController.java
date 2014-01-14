package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

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

	// @SuppressWarnings("unused")
	// private static final Logger log = LoggerFactory
	// .getLogger(DefaultController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private JobTitleService jobTitleService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", new Department());
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "jobTitle", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "empo", method = RequestMethod.GET)
	public String doEmployee_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department dept, Model model) {
		Department parent = dept.getParentDepartment();
		if (parent == null) {
			dept.setParentDepartment(parent.getId() == null ? null : parent);
		}
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "jobTitle", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitle", jobTitle);
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "empo", method = RequestMethod.POST)
	public String doEmployee_POST(Employee empo, Model model) {
		employeeService.storeEmployee(empo);
		List<Employee> empos = employeeService.findAllEmployees();
		model.addAttribute("empo", empo);
		model.addAttribute("empos", empos);
		return View.EMPLOYEES;
	}

}
