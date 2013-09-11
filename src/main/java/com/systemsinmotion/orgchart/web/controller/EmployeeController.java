package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("emps")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private JobTitleService jobTitleService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		loadModel(model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doEmployees_POST(@ModelAttribute("employee") Employee employee, Model model) {
		this.employeeService.storeEmployee(employee);
		loadModel(model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String doEmployees_PUT(@ModelAttribute("employee") Employee employee, Model model) {
		this.employeeService.updateEmployee(employee);
		loadModel(model);
		return View.EMPLOYEES;
	}
	
	private void loadModel(Model model) {
		List<Employee> employees = this.employeeService.findAllEmployees();
		List<Department> departments = this.departmentService.findAllActiveDepartments();
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("jobs", jobTitles);
	}
	
	@ModelAttribute("employee")
	private Employee getModelEmployee() {
		return new Employee();
	}
	
	
	public void setEmployeeService(EmployeeService mockEmployeeService) {
		this.employeeService = mockEmployeeService;
	}
	
	public void setDepartmentService(DepartmentService mockDepartmentService) {
		this.departmentService = mockDepartmentService;
	}
	
	public void setJobTitleService(JobTitleService mockJobTitleService) {
		this.jobTitleService = mockJobTitleService;
	}
}
