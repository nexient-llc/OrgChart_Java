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
	/*@RequestMapping(value = "admin/login", method= RequestMethod.GET)
	public String getLogin(){
		return View.ADMIN_LOGIN;
	}*/
	

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		// uncomment when database connection is set up. will throw error when
		// run
		List<Department> departments = departmentService.findDepartmentsByIsActiveTrue();
		
		List<Employee> employees = employeeService.findEmployeesByIsActiveTrue();
		model.addAttribute("employees", employees);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "employees",method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		List<Employee> employees = employeeService.findEmployeesByIsActiveTrue();
		model.addAttribute("employees",employees);
		List<JobTitle> jobTitles = jobTitleService.findJobTitlesByIsActiveTrue();
		model.addAttribute("jobTitles", jobTitles);
		List<Department> departments = departmentService.findDepartmentsByIsActiveTrue();
		model.addAttribute("depts", departments);
		
		return View.EMPLOYEES;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "jobTitles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles= jobTitleService.findJobTitlesByIsActiveTrue();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService=jobTitleService;
	}
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Model model, String name, Integer parentDepartmentId, Integer departmentManagerId) {
		Department newDepartment = new Department();
		
		if (!parentDepartmentId.equals(-1)){
			newDepartment.setParentDepartment(departmentService.findDepartmentByID(parentDepartmentId));
		}
		if (!departmentManagerId.equals(-1)){
			newDepartment.setDepartmentManager(employeeService.findEmployeeByID(departmentManagerId));
		}
		newDepartment.setName(name);
		newDepartment.setIsActive(true);
		departmentService.storeDepartment(newDepartment);		
		return doDepartments_GET(model);
	}
	@RequestMapping(value ="jobTitles", method=RequestMethod.POST)
	public String doJobTitles_POST(Model model, String name){
		JobTitle newJobTitle = new JobTitle();
		newJobTitle.setName(name);
		newJobTitle.setIsActive(true);
		jobTitleService.storeJobTitle(newJobTitle);
		return doJobTitles_GET(model);
	}
	@RequestMapping(value="employees", method=RequestMethod.POST)
	public String doEmployee_Post(Model model, String firstName, String lastName, Character middleInitial, 
			String email, String skype, Boolean isManager, Integer departmentId, Integer jobTitleId){
		Employee newEmployee= new Employee();
		newEmployee.setFirstName(firstName);
		newEmployee.setLastName(lastName);
		newEmployee.setMiddleInitial(middleInitial);
		newEmployee.setEmail(email);
		newEmployee.setSkypeName(skype);
		
		newEmployee.setIsManager(isManager);
		newEmployee.setDepartment(departmentService.findDepartmentByID(departmentId));
		newEmployee.setJobTitle(jobTitleService.findJobTitleByID(jobTitleId));
		newEmployee.setIsActive(true);
		employeeService.storeEmployee(newEmployee);
		
		return doEmployees_GET(model);
	}
	@RequestMapping(value="removeJobTitle", method=RequestMethod.POST)
	public String removeJobTitle_Post(Model model, Integer jobTitleId){
	
		JobTitle updateJobTitle = jobTitleService.findJobTitleByID(jobTitleId);
		updateJobTitle.setIsActive(false);
		jobTitleService.storeJobTitle(updateJobTitle);
		return doJobTitles_GET(model);
	}
	@RequestMapping(value="removeEmployee", method=RequestMethod.POST)
	public String removeEmployee_Post(Model model, Integer employeeId){
		Employee updateEmployee = employeeService.findEmployeeByID(employeeId);
		updateEmployee.setIsActive(false);
		employeeService.storeEmployee(updateEmployee);
		return doEmployees_GET(model);
	}
	@RequestMapping(value="removeDepartment", method=RequestMethod.POST)
	public String removeDepartment_Post(Model model, Integer departmentId){
		Department updateDepartment = departmentService.findDepartmentByID(departmentId);
		updateDepartment.setIsActive(false);
		departmentService.storeDepartment(updateDepartment); 		
		return doDepartments_GET(model);
	}
	@RequestMapping(value="editJobTitle", method=RequestMethod.POST)
	public String editJobTitle_Post(Model model, String name, Integer jobTitleId){
		JobTitle editJobTitle = jobTitleService.findJobTitleByID(jobTitleId);
		editJobTitle.setName(name);
		jobTitleService.storeJobTitle(editJobTitle);
		return doJobTitles_GET(model);
	}
	@RequestMapping(value="editEmployee", method=RequestMethod.POST)
	public String editEmployee_Post(Model model, Integer employeeId, String firstName, String lastName, Character middleInitial, 
			String email, String skype, Boolean isManager, Integer departmentId, Integer jobTitleId){
		System.out.println(employeeId+" "+firstName+" "+lastName+" "+middleInitial+" "+email+" "+skype+" "+isManager+" "+departmentId+" "+jobTitleId );
		Employee editEmployee=employeeService.findEmployeeByID(employeeId);
		editEmployee.setFirstName(firstName);
		editEmployee.setLastName(lastName);
		editEmployee.setMiddleInitial(middleInitial);
		editEmployee.setEmail(email);
		editEmployee.setSkypeName(skype);
		editEmployee.setDepartment(departmentService.findDepartmentByID(departmentId));
		editEmployee.setJobTitle(jobTitleService.findJobTitleByID(jobTitleId));
		employeeService.storeEmployee(editEmployee);
		return doEmployees_GET(model);
	}
	@RequestMapping(value = "editDepts", method = RequestMethod.POST)
	public String editDepartments_POST(Model model, Integer departmentId, String departmentName, 
			Integer parentDepartmentId, Integer departmentManagerId) {
		Department editDepartment = departmentService.findDepartmentByID(departmentId);
		
		if (!parentDepartmentId.equals(-1)){
			editDepartment.setParentDepartment(departmentService.findDepartmentByID(parentDepartmentId));
		}
		if (!departmentManagerId.equals(-1)){
			editDepartment.setDepartmentManager(employeeService.findEmployeeByID(departmentManagerId));
		}
		editDepartment.setName(departmentName);
		
		departmentService.storeDepartment(editDepartment);		
		return doDepartments_GET(model);
	}
	
	
	
}
	
