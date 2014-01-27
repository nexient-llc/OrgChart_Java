package com.systemsinmotion.orgchart.web.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	Gson sendJSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		 updateDeptAttributes(new Department(), model);
		 return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
    public String doDepartments_POST(Department dept, Model model) {
		departmentService.storeDepartment(dept);
		
		updateDeptAttributes(dept, model);
		return View.DEPARTMENTS;
    }
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
    public String doDepartments_PUT(Integer id, String name, Integer parentID, Model model) {
		Department updateDept = departmentService.findDepartmentByID(id);
		updateDept.setName(name);
		updateDept.setParentDepartment(departmentService.findDepartmentByID(parentID));
		departmentService.storeDepartment(updateDept);
		
		updateDeptAttributes(new Department(), model);
        return View.DEPARTMENTS;
    }
	
	@RequestMapping(value = "depts/{id}", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@PathVariable(value = "id") Integer id, Model model){
		Department removeDept = departmentService.findDepartmentByID(id);
		departmentService.makeDepartmentInactive(removeDept);
		updateDeptAttributes(new Department(), model);
		return View.DEPARTMENTS;
	}
	
	private void updateDeptAttributes(Department department, Model model){
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("dept", department);
		model.addAttribute("depts", departments);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("job", new JobTitle());
        model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("job", jobTitle);
		model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(Integer id, String name, String description, Model model){
//		jobTitleService.storeJobTitle(jobTitle);
		JobTitle updateJobTitle = jobTitleService.findJobTitleByID(id);
		updateJobTitle.setName(name);
		updateJobTitle.setDescription(description);
		jobTitleService.storeJobTitle(updateJobTitle);
		
		updateJobTitleAttributes(model);
		return View.JOB_TITLES;
	}
	
	private void updateJobTitleAttributes(Model model){
		List<JobTitle> jobTitles = jobTitleService.findAllActiveJobTitles();
		model.addAttribute("job", new JobTitle());
		model.addAttribute("jobs", jobTitles);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		 updateEmployeeAttributes(new Employee(), model);
		 return View.EMPLOYEES;
	}
	
	private void updateEmployeeAttributes(Employee emp, Model model){
		List<Employee> employees = employeeService.findAllActiveEmployees();
		model.addAttribute("emp", emp);
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllActiveJobTitles();
		model.addAttribute("jobs", jobTitles);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee emp, Model model) {
		emp.setIsActive(true);
		employeeService.storeEmployee(emp);
		updateEmployeeAttributes(emp, model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Integer id, String firstName, String lastName, Character middleInitial, String email, 
							String skypeName, Integer departmentID, Integer jobTitleID, Model model){
		Employee updateEmployee = employeeService.findEmployeeByID(id);
		Department employeeDepartment = departmentService.findDepartmentByID(departmentID);
		JobTitle employeeJobTitle = jobTitleService.findJobTitleByID(jobTitleID);
		
		updateEmployee.setFirstName(firstName);
		updateEmployee.setLastName(lastName);
		updateEmployee.setMiddleInitial(middleInitial);
		updateEmployee.setEmail(email);
		updateEmployee.setSkypeName(skypeName);
		updateEmployee.setDepartment(employeeDepartment);
		updateEmployee.setJobTitle(employeeJobTitle);
		
		employeeService.storeEmployee(updateEmployee);
		
		updateEmployeeAttributes(updateEmployee, model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(Integer id, Model model){
		employeeService.setEmployeeInactive(id);
		updateEmployeeAttributes(new Employee(), model);
		
		return View.EMPLOYEES;
	}
	

	@RequestMapping(value = "emps", method = RequestMethod.GET, params = {"name", "departmentID", "jobTitleID"})
	public @ResponseBody String doEmployees_GET(String name, Integer departmentID, Integer jobTitleID) {
		List<Employee> filterEmployees = employeeService.filterEmployee(name, departmentID, jobTitleID);
		return employeeService.getAllEmployeesAsJson(filterEmployees);
//		return sendJSON.toJson(filterEmployees);
	}
	
	@RequestMapping(value = "emps/autocomplete", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_GET(){
		String[] empNames = employeeService.returnAllEmployeeNames();
		return sendJSON.toJson(empNames);
	}

}
