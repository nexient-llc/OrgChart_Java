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
public class DefaultController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;
	
	//display the home page
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value  = "emps" , method = RequestMethod.GET)
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
	
	@RequestMapping(value = "emps" , method = RequestMethod.POST)
	public String doEmployees_POST(@Valid Employee newEmp
			,@RequestParam("departmentId") Integer deptID
			,@RequestParam("jobTitleID") Integer jtID
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
		
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		//and finally, return the employee view
		return View.EMPLOYEES;
		
	}
	
	@RequestMapping(value = "basicDelete", method = RequestMethod.POST)
	public String doEmployeeDelete(@RequestParam("hiddenEmpID2") Integer empID
			,Model model)
	{
		
		//retrieve employee record based on passed ID
		Employee emp = employeeService.findEmployeeByID(empID);
		
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
	
	
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit_POST(@RequestParam("hiddenEditEmpID") Integer incomingEmpID
			,Model model) 
	{
		
	    //basic model information to repopulate the table
		//retrieve the data to be displayed on the page
		List<Employee> empsList = employeeService.findAllEmployees();
		List<Department> deptsList = departmentService.findAllDepartments();
		List<JobTitle> jtList = jobTitleService.findAllJobTitles();
		
		//pass the data lists to the model for display
		model.addAttribute("emps", empsList);
		model.addAttribute("depts", deptsList);
		model.addAttribute("jobs", jtList);
		
		
		//now, lets also find the selected employee information and pass that into the model
	    Employee emp = this.employeeService.findEmployeeByID(incomingEmpID);
	    model.addAttribute("selectedEmp", emp);
	    
	    //not return the employee view with the information
	    return View.EMPLOYEES;
	
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
