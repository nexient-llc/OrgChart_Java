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
	
	private final static String NAV_DEPARTMENT = "depts"; 
	private final static String NAV_EMPLOYEE = "emps";
	private final static String NAV_JOBTITLES = "jobs";
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = NAV_DEPARTMENT, method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 List<Department> departments = departmentService.findAllDepartments();
		 model.addAttribute(NAV_DEPARTMENT, departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = NAV_EMPLOYEE, method = RequestMethod.GET)
	public String doEmployees_GET(String st1, String st2, String st3, String st4, Model model) {
		//uncomment when database connection is set up. will throw error when run
		 List<Employee> employees = employeeService.findAllEmployees();
		 model.addAttribute(NAV_EMPLOYEE, employees);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = NAV_JOBTITLES, method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 List<JobTitle> jobs = jobTitleService.findAllActiveJobTitles();
		 model.addAttribute(NAV_JOBTITLES, jobs);
		return View.JOB_TITLES;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = NAV_DEPARTMENT, method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute(NAV_DEPARTMENT, departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = NAV_EMPLOYEE, method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllActiveEmployees();
		model.addAttribute(NAV_EMPLOYEE, employees);
		return View.EMPLOYEES;
	}

	public void doJobTitles_POST(JobTitle mockJobTitle, Model model) {
		// TODO Auto-generated method stub
		
	}

}
