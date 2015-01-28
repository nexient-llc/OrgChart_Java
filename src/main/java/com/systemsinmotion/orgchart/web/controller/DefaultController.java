package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		 List<Department> departments = departmentService.findAllActiveDepartments();
		 model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Employee employee, Model model) {		
		List<Employee> employees = employeeService.findAllEmployees();
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		 model.addAttribute("emps", employees);
		 model.addAttribute("depts", departments);
		 model.addAttribute("jobTitles", jobTitle);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		 List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobTitle);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "dept/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDeptAjax(@PathVariable("id") Integer id) {
		Department dept = departmentService.findDepartmentByID(id);
		if (dept == null) {
			return "";
		}
		return dept.toString();
	}

	@RequestMapping(value = "delete/dept/{id}", method = RequestMethod.GET)
	public @ResponseBody String deleteDeptAjax(@PathVariable("id") Integer id) {
		Department dept = departmentService.findDepartmentByID(id);
		if (dept == null) {
			return "";
		}
		dept.setIsActive(false);
		departmentService.storeDepartment(dept);
		return dept.toString();
	}	
	
	@RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
	public @ResponseBody String getEmpAjax(@PathVariable("id") Integer id) {
		Employee emp = employeeService.findEmployeeByID(id);
		if (emp == null) {
			return "";
		}
		return emp.toString();
	}
	
	@RequestMapping(value = "suggestions/{firstName}", method = RequestMethod.GET)
	public @ResponseBody String getEmployeeName(@PathVariable("firstName") String name) {		
		String returnValue = "";
		List<Employee> suggestions = employeeService.getEmployeeSuggestions(name);
		
		if(suggestions != null) {
			for (int i = 0; i < suggestions.size(); i++)
				returnValue += suggestions.get(i).getFirstName() + " " + suggestions.get(i).getLastName() + ",";
			return returnValue;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "job/{id}", method = RequestMethod.GET)
	public @ResponseBody String getJobAjax(@PathVariable("id") Integer id) {
		JobTitle jobTitle = jobTitleService.findJobTitleByID(id);
		if (jobTitle == null) {
			return "";
		}
		return jobTitle.toString();
	}
	
	

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Integer parent_id, Model model) {
		
		if(parent_id != null){	
			department.setParentDepartment(departmentService.findDepartmentByID(parent_id));
		}		
		if(department.getParentDepartment().getId().equals(department.getId())){
			 List<Department> departments = departmentService.findAllActiveDepartments();
			 model.addAttribute("depts", departments);
			return View.DEPARTMENTS;
		}		
		departmentService.storeDepartment(department);
		 List<Department> departments = departmentService.findAllActiveDepartments();
		 model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Integer job_title_id, Integer department_id, Boolean search, Model model) {
		
			List<Department> departments = departmentService.findAllDepartments();
			List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
			model.addAttribute("depts", departments);
			model.addAttribute("jobTitles", jobTitle);
			if(department_id != null){
				employee.setDepartment(departmentService.findDepartmentByID(department_id));
			}
			if(job_title_id != null){
				employee.setJobTitle(jobTitleService.findJobTitleByID(job_title_id));
			}
			
			if(search != null){
				if(search == true){
					List<Employee> employees = employeeSearch(employee);
					
					model.addAttribute("emps", employees);
					return View.EMPLOYEES;					
				}
				else{
					List<Employee> employees = employeeService.findAllEmployees();
					 model.addAttribute("emps", employees);
					return View.EMPLOYEES;
				}
			}
			
			employeeService.storeEmployee(employee);
			List<Employee> employees = employeeService.findAllEmployees();
			 model.addAttribute("emps", employees);
			return View.EMPLOYEES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doDepartments_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		 List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
	
	public List<Employee> employeeSearch(Employee employee){
		
		List<Employee> employees = new ArrayList<Employee>();	
		
		if(employee.getFirstName().equals("")){
			employee.setFirstName(null);
		}
		
		if( employee.getFirstName() != null && employee.getDepartment() != null && employee.getJobTitle() != null){
			employees.addAll(employeeService.findDistinctEmployeeByFirstNameOrLastNameAndDepartmentAndJobTitle(employee.getFirstName(), employee.getFirstName(), employee.getDepartment(), employee.getJobTitle()));									
		}
		else if( employee.getFirstName() != null && employee.getDepartment() != null && employee.getJobTitle() == null){
			employees.addAll(employeeService.findDistinctEmployeeByFirstNameOrLastNameAndDepartment(employee.getFirstName(), employee.getFirstName(), employee.getDepartment()));														
		}
		else if( employee.getFirstName() != null && employee.getDepartment() == null && employee.getJobTitle() != null){
			employees.addAll(employeeService.findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(employee.getFirstName(), employee.getFirstName(), employee.getJobTitle()));						
		}
		else if( employee.getFirstName() == null && employee.getDepartment() != null && employee.getJobTitle() != null){
			employees.addAll(employeeService.findDistinctEmployeeByDepartmentAndJobTitle(employee.getDepartment(), employee.getJobTitle()));						
		}
		else if( employee.getFirstName() != null && employee.getDepartment() == null && employee.getJobTitle() == null){
			employees.addAll(employeeService.findDistinctEmployeeByFirstNameOrLastName(employee.getFirstName(), employee.getFirstName()));						
		}
		else if( employee.getFirstName() == null && employee.getDepartment() == null && employee.getJobTitle() != null){
			employees.addAll(employeeService.findDistinctEmployeeByJobTitle(employee.getJobTitle()));						
		}
		else if( employee.getFirstName() == null && employee.getDepartment() != null && employee.getJobTitle() == null){
			employees.addAll(employeeService.findDistinctEmployeeByDepartment(employee.getDepartment()));						
		}
		else{
			employees.addAll(employeeService.findAllEmployees());					
		}
		
		return employees;
	}
	
}
