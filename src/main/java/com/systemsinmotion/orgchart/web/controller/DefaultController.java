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
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(String name, Integer parent_id, Model model) {
		Department department = new Department();
		department.setName(name);
		if(parent_id != null) department.setParentDepartment(departmentService.findDepartmentByID(parent_id));
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "editdepts", method = RequestMethod.POST)
	public String doDepartmentsEdit_POST(Integer id, String name, Integer parent_id, Model model) {
		Department department = departmentService.findDepartmentByID(id);
		department.setName(name);
		if(parent_id != null) department.setParentDepartment(departmentService.findDepartmentByID(parent_id));
		else department.setParentDepartment(null);
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(String firstName, String lastName, Character middleInitial, Integer departmentId, 
			String email, String skypeName, Integer jobTitle, Model model) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentByID(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitle));
		try {
			employeeService.storeEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "empsedit", method = RequestMethod.POST)
	public String doEmployeesEdit_POST(Integer id, String firstName, String lastName, Character middleInitial, Integer departmentId, 
			String email, String skypeName, Integer jobTitle, Model model) {
		Employee employee = employeeService.findEmployeeById(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentByID(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitle));
		try {
			employeeService.storeEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobs_GET(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobs_POST(String name, Model model) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "editJob", method = RequestMethod.POST)
	public String doJobEdit_POST(Integer id, String name, Model model) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(id);
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "empsfilter", method = RequestMethod.POST)
	public String doEmployeeFilter_POST(String name, Integer deptId, Integer jobId, Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		if(name.isEmpty() && deptId == null && jobId == null) {
			employees = employeeService.findAllEmployees();
		} else {
			if(!name.isEmpty()) {
				Employee employee = employeeService.findEmployeeByFirstName(name);
				if(employee != null) employees.add(employee);
				employee = employeeService.findEmployeeByLastName(name);
				if(employee != null) employees.add(employee);
			}
			if(deptId != null) {
				
			}
		}
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}

}
