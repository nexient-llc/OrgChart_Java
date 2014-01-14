package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.systemsinmotion.orgchart.entity.Department;
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
	
	
	// DEPARTMENT
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		setUpDepartmentView(model, new Department());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model){
        departmentService.storeDepartment(department);
        
        setUpDepartmentView(model, new Department());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Integer id, String name, Integer parentId, Model model){
		Department dept = departmentService.findDepartmentByID(id);
		dept.setName(name);
		dept.setParentDepartment(departmentService.findDepartmentByID(parentId));
		departmentService.storeDepartment(dept);
		
		setUpDepartmentView(model, new Department());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts/{id}", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@PathVariable(value = "id") Integer id, Model model){
		Department dept = departmentService.findDepartmentByID(id);
		this.departmentService.removeDepartment(dept);
		
		setUpDepartmentView(model, new Department());
		return View.DEPARTMENTS;
	}
	
	private void setUpDepartmentView(Model model, Department newDept){
		model.addAttribute("newDept", newDept);
        model.addAttribute("depts", departmentService.findAllDepartments());
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	//JOBTITLE
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		
		
		setUpJobTitlesView(model, new JobTitle());
		return View.JOB_TITLES;
	}
	
	private void setUpJobTitlesView(Model model, JobTitle jobTitle){
		model.addAttribute("newJob", jobTitle);
		model.addAttribute("jobs",jobTitleService.findAllJobTitles());
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
}
