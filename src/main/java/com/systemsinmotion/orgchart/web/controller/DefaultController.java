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
//		JobTitle newJob = jobTitleService.findById(1007);
//		Department newDep = new Department();
//		newDep.setName("Cake Smacking");
//		newDep.setIsActive(true);
//		departmentService.storeDepartment(newDep);
		//		JobTitle newJob = new JobTitle();
		//newJob.setName("Being Catman 'cause he's better");
		//newJob.setIsActive(true);
		JobTitle title; 
		title = jobTitleService.findById(55);	
		List<Employee> employees = employeeService.listByNameSubstring("bo");
		for(Employee e : employees) {
			System.out.println(e.getFirstName() + " " + e.getLastName());
		}
		//System.out.println(employees.size());
		//newJob.setName("Being snackman 'cause he's hungry");
		//jobTitleService.save(newJob);
		List<Department> departments = departmentService.findAllDepartments();
		//List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
        model.addAttribute("depts", departments);
        return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
