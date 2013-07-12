package com.systemsinmotion.orgchart.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable; // Needed for AJAX CALLS WITH AUTOCOMPLETE FOR FORM FIELDS
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

// NEED TO ADD CATCH ERRORS
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
	
	// HOME
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	// DEPARTMENT
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department dept, Model model) {
		try{
			departmentService.storeDepartment(dept);
			String departmentPost = doDepartments_GET(model);
			return departmentPost;
		}catch(Exception E){
			String departmentPost = doDepartments_GET(model);
			return departmentPost;
		}
	}
	
	@RequestMapping(value = "deptsEdit", method = RequestMethod.PUT)
	public String doDepartment_EDIT(Department dept, Model model){
		try{
			departmentService.updateDepartment(dept);
			String departmentPut = doDepartments_GET(model);
			return departmentPut;
		}catch(Exception E){
			String departmentPut = doDepartments_GET(model);
			return departmentPut;
		}
	}
	
	@RequestMapping(value ="deptsDelete", method = RequestMethod.DELETE)
	public String doDepartment_DELETE(Department dept, Model model){
		
		try{
			departmentService.removeDepartment(dept);
			String departmentDelete = doDepartments_GET(model);
			return departmentDelete;
		} catch(Exception E){
			String departmentDelete = doDepartments_GET(model);
			return departmentDelete;
		}
	}
	
	@RequestMapping(value = "findAllParentId", method = RequestMethod.GET)
	public @ResponseBody String doParentId_GET(){
		List<Integer> parentId = departmentService.findAllParentDepartmentIds();
		Gson json = new Gson();
		String parentIds = json.toJson(parentId);
		return parentIds;
	}

	// EMPLOYEE 
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value ="emps", method = RequestMethod.GET)
	public String doEmployee_GET(Model model){
		List<Employee> employee = employeeService.findAllEmployees();
		List<Department> department = departmentService.findAllDepartments();
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		model.addAttribute("depts", department);
		model.addAttribute("emps", employee);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value="emps", method = RequestMethod.POST)
	public String doEmployee_POST(Employee employee, Model model){
		
		try{
			employeeService.storeEmployee(employee);
			String employeePost = doEmployee_GET(model);
			return employeePost;
		}
		catch(Exception E){
			String employeePost = doEmployee_GET(model);
			return employeePost;
		}
	
	}
	
	@RequestMapping(value="emps", method = RequestMethod.PUT)
	public String doEmployee_PUT(Employee employee, Model model){
		try{
			employeeService.updateEmployee(employee);
			String employeePut = doEmployee_GET(model);
			return employeePut;
		}
		
		catch(Exception E){
			String employeePut = doEmployee_GET(model);
			return employeePut;
		}
	}
	
	@RequestMapping(value="deleteEmps", method = RequestMethod.DELETE)
	public String doEmployee_DELETE(Employee employee, Model model){
		try{
			employeeService.removeEmployee(employee);
			String employeeDelete = doEmployee_GET(model);
			return employeeDelete;
		}
		
		catch(Exception E){
			String employeeDelete = doEmployee_GET(model);
			return employeeDelete;
		}
	}
	
	// EMPLOYEE FILTER
	@RequestMapping(value="empsFilter", method = RequestMethod.GET)
	public String doEmployeeFilter(String firstName, String lastName, Integer department, Integer jobTitle, Model model){
		List<Employee> employees = employeeService.findEmployeeByFilter(firstName, lastName, department, jobTitle);
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("jobs", jobTitles);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "findAllEmployees", method = RequestMethod.GET)
	public @ResponseBody String doEmployeeAuto(){
		List<Employee>	employees = employeeService.findAllEmployees();
		Gson json = new Gson();
		String employeesAuto = json.toJson(employees);
		return employeesAuto;
	}
	
	// JOBS
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value ="jobAdd", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle job, Model model){
		try{
			jobTitleService.storeJobTitle(job);
			String jobTitlePost = doJobTitle_GET(model);
			return jobTitlePost;
		}catch(Exception E){
			String jobTitlePost = doJobTitle_GET(model);
			return jobTitlePost;
		}
	}
	
	@RequestMapping(value="jobUpdate", method = RequestMethod.PUT)
	public String doJobTitle_PUT(JobTitle job, Model model){
		try{
			jobTitleService.updateJobTitle(job);
			String jobTitlePut = doJobTitle_GET(model);
			return jobTitlePut;
		}catch(Exception E){
			String jobTitlePut = doJobTitle_GET(model);
			return jobTitlePut;
		}
	}
	
	@RequestMapping(value = "jobDelete", method = RequestMethod.DELETE)
	public String doJobTitle_DELETE(JobTitle job, Model model){
		try{
			jobTitleService.removeJobTitle(job);
			String jobTitleDelete = doJobTitle_GET(model);
			return jobTitleDelete;
		}catch(Exception E){
			String jobTitleDelete = doJobTitle_GET(model);
			return jobTitleDelete;
		}
	}

}