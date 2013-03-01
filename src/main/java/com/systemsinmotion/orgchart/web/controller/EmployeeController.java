package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value="emps")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	
	public void setDepartmentService(DepartmentService departmentService) 
	{
		this.departmentService = departmentService;
	}
	
	public void setEmployeeService(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService)
	{
		this.jobTitleService = jobTitleService;
	}
	
	//initial page load
	@RequestMapping(method = RequestMethod.GET)
	public String doEmployees_GET(Model model)
	{
		
		//retrieve the base data to be used in search drop downs
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for use
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the employee view
		return View.EMPLOYEES;
		
	}
	
	//find by ID
	
	
	//find by Name
	
	
	//find by Dept
	
	
	//find by Title
	
	
	//find by Email
	
	
	//find by Manager
	
	
	//add new
	
	//update existing
	
	//delete
	
}
