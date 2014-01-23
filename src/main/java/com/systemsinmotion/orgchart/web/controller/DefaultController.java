package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
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
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

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
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		model.addAttribute("depts", departmentService.findDepartmentsByIsActiveTrue());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Model model, String name, Integer parentDepartmentId) {
		Department department = new Department();
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		department.setIsActive(true);
		departmentService.storeDepartment(department);
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Model model, Integer departmentId, String name, Integer parentDepartmentId) {
		Department department = departmentService.findDepartmentById(departmentId);
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		departmentService.storeDepartment(department);
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Model model, Integer departmentId) {
		departmentService.setInactiveDepartment(departmentService.findDepartmentById(departmentId));
		return doDepartments_GET(model);
	}
	
	/* Employees */
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		model.addAttribute("emps", employeeService.findEmployeesByIsActiveTrue());
		model.addAttribute("depts", departmentService.findDepartmentsByIsActiveTrue());
		model.addAttribute("jobs", jobTitleService.findJobTitleByIsActiveTrue());
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Model model, String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Model model, Integer employeeId, String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(Model model, Integer employeeId) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setDepartment(null);
		employee.setJobTitle(null);
		employee.setIsActive(false);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	/* Job Titles */
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		model.addAttribute("jobs", jobTitleService.findJobTitleByIsActiveTrue());
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(Model model, String name) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(name);
		jobTitle.setIsActive(true);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(Model model, Integer jobTitleId, String name) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.DELETE)
	public String doJobTitles_DELETE(Model model, Integer jobTitleId) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setIsActive(false);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
}