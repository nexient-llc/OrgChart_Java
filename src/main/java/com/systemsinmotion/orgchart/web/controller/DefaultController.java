package com.systemsinmotion.orgchart.web.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.Department;
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

	
	@Autowired 
	Department department;
	
	@Autowired 
	JobTitle jobTitle;
	

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
	

	
	@RequestMapping(value="depts", method=RequestMethod.POST)
	public String doCreateDepartmemnt(@RequestParam("name") String name, @RequestParam(value="parent_id", defaultValue="-1") Integer parent_id, Model model)
{
	
		
		if (parent_id>0)
		{	
			Department tempDept=departmentService.findDepartmentByID(parent_id);		
			this.department.setParentDepartment(tempDept);		
		}
		
		this.department.setName(name);
		
		departmentService.storeDepartment(this.department);
		
	return doDepartments_GET(model);
}
	
	
	
	
	@RequestMapping(value="remove_depts", method=RequestMethod.POST)
	public String doDeleteDepartmemnt(@RequestParam("id") Integer id, Model model)
{		
		//test for value selected
	if (id>0)
		{	
			this.department=this.departmentService.findDepartmentByID(id);			
			this.departmentService.removeDepartment(this.department);						
		}
	
		return doDepartments_GET(model);

		
}
		
	@RequestMapping(value="update_dept", method=RequestMethod.POST)
	public String doUpdateDepartmemnt(@RequestParam("newName") String newName,  @RequestParam(value="parent_id") Integer parent_id, 
			@RequestParam(value="oldName") String oldName, Model model)
{	
		
		this.department= this.departmentService.findDepartmentByName(oldName);
		
		if (parent_id>0)
		{	
			Department tempDept=departmentService.findDepartmentByID(parent_id);		
			this.department.setParentDepartment(tempDept);		
		}
		
		
		else this.department.setParentDepartment(null);
		
		this.department.setName(newName);
		this.departmentService.updateDepartment(this.department);
		
		return doDepartments_GET(model);	
		
}
		
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
//		 List<Department> departments = departmentService.findAllDepartments();
//		 model.addAttribute("depts", departments);
		return View.EMPLOYEES;
	}
	
	public void seteMPLOYEEService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) 
	{		
		 List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobs);
		return View.JOB_TITLES;
	}
	
	
	@RequestMapping(value="addJob", method=RequestMethod.POST)
	public String doJobTitles_Add(@RequestParam("name") String name,  Model model)
	{
		
		if (name==null)
			return doJobTitles_GET(model);
		
		this.jobTitle.setName(name);		
		this.jobTitleService.storeJobTitle(this.jobTitle);				
		return doJobTitles_GET(model);
	}
	
	
	@RequestMapping(value="update_job", method=RequestMethod.POST)
	public String doJobTitles_Edit(@RequestParam("newName") String newName,  
			@RequestParam(value="oldName") String oldName, Model model)
	{
		
		if (newName==null||oldName==null) 
		return doJobTitles_GET(model);
		
		this.jobTitle= this.jobTitleService.findJobTitleByName(oldName);
		this.jobTitle.setName(newName);
		this.jobTitleService.updateJobTitle(this.jobTitle);
		
		return doJobTitles_GET(model);
	}
	
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}	
	

}
