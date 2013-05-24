package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
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
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department newDepartment, Model model) {
		departmentService.storeDepartment(newDepartment);
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Department department, Model model) {
		this.departmentService.updateDepartment(department);
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts/{departmentId}", method = RequestMethod.GET)
	public @ResponseBody
	String doDepartments_preFillEditForm(
			@PathVariable("departmentId") Integer departmentId) {
		Department dept = this.departmentService
				.findDepartmentByID(departmentId);
		Gson gson = new Gson();
		String json = gson.toJson(dept);
		return json;
	}

	private void getDepartmentsForDepartmentView(Model model) {
		List<Department> allDepts = departmentService.findAllDepartments();
		model.addAttribute("depts", allDepts);
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitle_PUT(JobTitle jobTitle, Model model) {
		this.jobTitleService.updateJobTitle(jobTitle);
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs/{jobId}", method = RequestMethod.GET)
	public @ResponseBody
	String doJobTitle_preFillEditForm(@PathVariable("jobId") Integer jobId) {
		JobTitle job = this.jobTitleService.findJobTitleById(jobId);
		Gson gson = new Gson();
		String json = gson.toJson(job);
		return json;
	}

	private void getJobTitlesForJobTitleView(Model model) {
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model) {
		employeeService.addEmployee(employee);
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Employee employee, Model model) {
		this.employeeService.updateEmployee(employee);
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(@RequestParam Integer empId, Model model) {
		this.employeeService.deleteEmployee(this.employeeService
				.findEmployeeById(empId));
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps/{empId}", method = RequestMethod.GET)
	public @ResponseBody
	String doEmployees_preFillEditForm(@PathVariable("empId") Integer employeeId) {
		Employee emp = this.employeeService.findEmployeeById(employeeId);
		Gson gson = new Gson();
		String json = gson.toJson(emp);
		return json;
	}

	private void getDepartmentsJobTitlesEmployeesForEmployeeView(Model model) {
		List<Employee> employees = this.employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		getDepartmentsForDepartmentView(model);
		getJobTitlesForJobTitleView(model);
	}
}
