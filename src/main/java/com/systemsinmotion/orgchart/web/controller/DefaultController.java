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
	
	@Autowired 
	Department department;
	
	@Autowired 
	JobTitle jobTitle;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	
	//begin dept methods
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 getDepartmentModel(model);
		
		 		 
		return View.DEPARTMENTS;
	}

	private void getDepartmentModel(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		 model.addAttribute("depts", departments);
	}
	
	
	
	@RequestMapping(value="depts", method=RequestMethod.POST)
	public String doCreateDepartmemnt(Department dept, Model model)
{
			
		departmentService.storeDepartment(dept);
		
		 getDepartmentModel(model);
		
		 return View.DEPARTMENTS;
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
	
	 getDepartmentModel(model);	
	 return View.DEPARTMENTS;		
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
		
		 getDepartmentModel(model);
		
		return View.DEPARTMENTS;			
}
			
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

		
//begin employee methods
	

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		
		 getEmployeeModel(model, null);
		 		 
		return View.EMPLOYEES;
	}

	private void getEmployeeModel(Model model, List<Employee> emps_In) {

		
		if (emps_In==null)
		{
			List<Employee> employees = this.employeeService.findAll();		
			model.addAttribute("emps", employees);
		}	 		 

		getJobTitleModel(model);		 
		getDepartmentModel(model);
	}
	
	
	@RequestMapping(value = "addEmp", method = RequestMethod.POST)
	public String doEmployees_ADD(Employee employee, Model model) {
	
		this.employeeService.storeEmployee(employee); 		 
	
		getEmployeeModel(model, null);
		
		return View.EMPLOYEES;
	}
	
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value="remove_emp", method=RequestMethod.POST )
	public String doEmployees_remove(Employee employee, Model model)
	{
		this.employeeService.removeEmployee(employee);		
		getEmployeeModel(model, null);		
		return View.EMPLOYEES;		
	}
		
	@RequestMapping(value="update_emp", method=RequestMethod.POST)
	public String doEmployees_update(Employee employee, Model model)
	{				
		this.employeeService.updateEmployee(employee);			
		getEmployeeModel(model, null);	
		return View.EMPLOYEES;
	}
		
	//search
	@RequestMapping(value="search_emp", method=RequestMethod.POST)
	public String doEmployees_filterSearch(Employee employee, Model model)
	{						
		List<Employee>employees =this.employeeService.findbyCriteria(employee);		
		model.addAttribute("emps", employees);
		getEmployeeModel(model, employees);
		return View.EMPLOYEES;
	}
	
	
	
	
	//begin jobTitle Methods
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) 
	{		
		 getJobTitleModel(model);
		 
		return View.JOB_TITLES;
	}

	private void getJobTitleModel(Model model) {
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobs);
	}
	
	
	@RequestMapping(value="addJob", method=RequestMethod.POST)
	public String doJobTitles_Add(@RequestParam("name") String name,  Model model)
	{
		
		if (name==null)
			return doJobTitles_GET(model);
		
		this.jobTitle.setName(name);		
		this.jobTitleService.storeJobTitle(this.jobTitle);				
		
		 getJobTitleModel(model);
		
		return View.JOB_TITLES;
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
		
		 getJobTitleModel(model);
		
		return View.JOB_TITLES;
	}
		
	@RequestMapping(value="remove_job", method= RequestMethod.POST)
	public String doRemoveJobTitle(@RequestParam(value="name") String name,   Model model)
	{
		this.jobTitle= this.jobTitleService.findJobTitleByName(name);
		this.jobTitleService.removeJobTitle(this.jobTitle);
	
		 getJobTitleModel(model);
		return View.JOB_TITLES;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}	

}
