package com.systemsinmotion.orgchart.web.controller;

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
	

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) 
	{
		
		//retrieve the requested data from the database
		List<Department> departments = departmentService.findAllDepartments();
		
		//pass it to the model for display
		model.addAttribute("depts", departments);
		
		//and then return the view
		return View.DEPARTMENTS;
		 
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(@Valid Department newDept
			,@RequestParam("parent_id") Integer parentID
			,Model model)
	{
		
		//find the assigned parent department based on the ID passed in from the form
		if (parentID != null)
		{
			newDept.setParentDepartment(departmentService.findDepartmentByID(parentID));
		}
		
		//save the new department object and populate its new ID
		newDept.setDepartmentId(departmentService.storeDepartment(newDept));
		
		//grab the updated department list from the database 
		List<Department> departments = departmentService.findAllDepartments();
		
		//pass the list to the model for display
		model.addAttribute("depts", departments);
		
		//return the department view
		return View.DEPARTMENTS;
		
	}
	
	@RequestMapping(value="emps" , method=RequestMethod.GET)
	public String doEmployees_GET(Model model)
	{
		
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//return the employee view
		return View.EMPLOYEES;
		
	}
	
	@RequestMapping(value="emps" , method=RequestMethod.POST)
	public String doEmployees_POST(@Valid Employee newEmp
			,@RequestParam("department_id") Integer deptID
			,@RequestParam("jobTitle_id") Integer jtID
			,Model model)
	{
		
		//if the 'is manager' field was not checked, change it from null to false 
		if(newEmp.getIsManager() == null)
		{
			newEmp.setIsManager(false);
		}
		
		//find the assigned department data based on the ID passed in
		if(deptID != null)
		{
			newEmp.setDept(departmentService.findDepartmentByID(deptID));
		}
		
		//find the employee's job title
		if(jtID != null)
		{
			newEmp.setJobTitle(jobTitleService.findByJobTitleID(jtID));
		}
		
		//if the employee already exists, update the record
		if(newEmp.getEmpID() != null)
		{
			employeeService.updateEmployeeRecord(newEmp);
		}
		
		//otherwise, insert a new record and populate the employee id
		else
		{
			newEmp.setEmpID(employeeService.createEmployeeRecord(newEmp));
		}
		
		//retrieve the updated data from the database
		List<Employee> empsList = employeeService.findAllEmployees();
		
		//pass the information to the model for display
		model.addAttribute("emps", empsList);
		
		//and finally, return the employee view
		return View.EMPLOYEES;
		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String doEmployeeDelete(@Valid Employee emp
			,Model model)
	{
		
		//delete the selected employee record
	    employeeService.deleteEmployeeRecord(emp);
	    
	    //retrive the updated information from the database
	    List<Employee> emps = this.employeeService.findAllEmployees();
	    List<Department> depts = this.departmentService.findAllDepartments();
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    
	    //pass that information to the model for diaplay
	    model.addAttribute("emps", emps);
	    model.addAttribute("depts", depts);
	    model.addAttribute("jobs", jobs);
	    
	    //and return the employee view
	    return View.EMPLOYEES;
	    
	}
	
	@RequestMapping(value="jobs", method=RequestMethod.GET)
	public String doJobTitles_GET(Model model)
	{
		
		//retrive the requested data from the database
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass it to the model for display
		model.addAttribute("jobs", jtList);
		
		//and return the job title view
		return View.JOB_TITLES;
		
	}
	
	@RequestMapping(value="jobs", method=RequestMethod.POST)
	public String doJobTitles_POST(@Valid JobTitle newJT, Model model)
	{
		
		//add the new job title to the datase, populating the object ID 
		newJT.setJobTitleID(jobTitleService.createJobTitle(newJT));
		
		//retrieve the updated list of job titles from the database
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data to the model for display
		model.addAttribute("jobs", jtList);
		
		//and return the job title view
		return View.JOB_TITLES;
		
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit_POST(@RequestParam("hiddenEmpID") Integer incomingEmpID
			,Model model) 
	{
		
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    List<Department> depts = this.departmentService.findAllDepartments();
	    Employee emp = this.employeeService.findEmployeeByID(incomingEmpID);
	    model.addAttribute("jobs", jobs);
	    model.addAttribute("depts", depts);
	    model.addAttribute("emp", emp);
	    return View.EDIT;
	
	}
	
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



}
