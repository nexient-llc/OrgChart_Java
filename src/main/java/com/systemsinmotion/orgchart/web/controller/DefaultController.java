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
import org.springframework.web.bind.annotation.ModelAttribute;
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
		 List<Department> departments = departmentService.findAllDepartments();
		 model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(@Valid Department incomingDept, @RequestParam("parent_id") Integer parent_id,  
		Model model) {
	    
	    incomingDept.setParentDepartment(departmentService.findDepartmentByID(parent_id));	    
	    incomingDept.setDepartmentId(departmentService.storeDepartment(incomingDept));
//	    List<Department> currentDeptList = (ArrayList<Department>)model.asMap().get("depts");
	    List<Department> currentDeptList = departmentService.findAllDepartments();
//	    currentDeptList.get(0);
	    currentDeptList.add(incomingDept);
	    model.addAttribute("depts", currentDeptList);
	    
	    return View.DEPARTMENTS;
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
	    List<Employee> emps = this.employeeService.findAllEmployees();
	    List<Department> depts = this.departmentService.findAllDepartments();
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    model.addAttribute("emps", emps);
	    model.addAttribute("depts", depts);
	    model.addAttribute("jobs", jobs);
	    return View.EMPLOYEES;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(@Valid Employee incomingEmployee, @RequestParam("department_id") Integer incomingEmpDeptID,
		@RequestParam("jobTitle_id") Integer incomingEmpJobID, Model model) {
	    	
	    	
	    	if(incomingEmployee.getIsManager() == null){ //store unchecked checkbox as false instead of null
	    	    incomingEmployee.setIsManager(false);
	    	}
	    	if(incomingEmpDeptID != null){ //Lookup Department if selected in form.
	    	   incomingEmployee.setDepartment(departmentService.findDepartmentByID(incomingEmpDeptID)); 
	    	}
	    	if(incomingEmpJobID != null){ //Lookup Employee if selected in form.
	    	    incomingEmployee.setJobTitle(jobTitleService.findJobTitleByID(incomingEmpJobID));
	    	}
	    	
//	    	if(employeeService.findEmployeeByID(incomingEmployee.getEmployeeId()) != null){
	    	if(incomingEmployee.getEmployeeId() != null){
	    	    employeeService.updateEmployee(incomingEmployee);
	    	}
	    	else {
	    	    incomingEmployee.setEmployeeId(employeeService.storeEmployee(incomingEmployee));
	    	}
	    	    
	    	List<Employee> currentEmpsList = employeeService.findAllEmployees();
	    	model.addAttribute("emps", currentEmpsList);	    	
	    return View.EMPLOYEES;	    
	}
	
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String doEmployeeDelete(@Valid Employee incomingEmployee, Model model){
	    employeeService.deleteEmployee(incomingEmployee);
	    List<Employee> currentEmpsList = employeeService.findAllEmployees();
	    model.addAttribute("emps", currentEmpsList);
	    return View.EMPLOYEES;
	}
	
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_Get(Model model) {
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    model.addAttribute("jobs", jobs);
	    return View.JOB_TITLES;
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitle_POST(@Valid JobTitle incomingJobTitle, BindingResult errors, Model model) {
	    incomingJobTitle.setJobTitleId(jobTitleService.storeJobTitle(incomingJobTitle));
//	    List<JobTitle> currentJobTitles = (ArrayList<JobTitle>)model.asMap().get("jobs");
	    List<JobTitle> currentJobTitles = jobTitleService.findAllJobTitles();
//	    currentJobTitles.add(incomingJobTitle);
	    model.addAttribute("jobs", currentJobTitles);
	    return View.JOB_TITLES;	    
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit_POST(@RequestParam("hiddenEmpID") Integer incomingEmpID, Model model) {
	    List<JobTitle> jobs = this.jobTitleService.findAllJobTitles();
	    List<Department> depts = this.departmentService.findAllDepartments();
	    Employee emp = this.employeeService.findEmployeeByID(incomingEmpID);
	    model.addAttribute("jobs", jobs);
	    model.addAttribute("depts", depts);
	    model.addAttribute("emp", emp);
	    return View.EDIT;
	}	
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setJobTitleSErvice(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

}
