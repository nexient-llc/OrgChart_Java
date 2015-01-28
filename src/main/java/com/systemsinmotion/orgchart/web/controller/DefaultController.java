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
		//uncomment when database connection is set up. will throw error when run
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	// Function for adding a new department
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model) {
		/*Department department = new Department();
		department.setName(name);
		if(parent_id != null) department.setParentDepartment(departmentService.findDepartmentByID(parent_id));*/
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	// Function for editing an existing department
	@RequestMapping(value = "editdepts", method = RequestMethod.POST)
	public String doDepartmentsEdit_POST(Integer id, String name, Integer parent_id, Model model) {
		Department department = departmentService.findDepartmentByID(id);
		department.setName(name);
		if(parent_id != null) department.setParentDepartment(departmentService.findDepartmentByID(parent_id));
		else department.setParentDepartment(null);
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	// Function for removing an existing department
	// This just sets the isActive flag to false
	@RequestMapping(value = "removedepts", method = RequestMethod.POST)
	public String doDepartmentRemove_POST(Integer id, Model model) {
		Department department = departmentService.findDepartmentByID(id);
		department.setIsActive(false);
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}
	
	// Function for adding a new employee
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	/*public String doEmployees_POST(String firstName, String lastName, Character middleInitial, Integer departmentId, 
			String email, String skypeName, Integer jobTitle, Model model) {*/
	public String doEmployees_POST(Employee employee, Model model) {
		/*Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentByID(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitle));*/
		try {
			employeeService.storeEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}
	
	
	// Function for editing an existing employee
	@RequestMapping(value = "empsedit", method = RequestMethod.POST)
	public String doEmployeesEdit_POST(Integer id, String firstName, String lastName, Character middleInitial, Integer departmentId, 
			String email, String skypeName, Integer jobTitle, Model model) {
		Employee employee = employeeService.findEmployeeById(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentByID(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitle));
		try {
			employeeService.storeEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}

	// Function for adding a new job title
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model) {
		/*JobTitle jobTitle = new JobTitle();
		jobTitle.setName(name);*/
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}
	
	// Function for editing an existing job title
	@RequestMapping(value = "editJob", method = RequestMethod.POST)
	public String doJobEdit_POST(Integer id, String name, Model model) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(id);
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.JOB_TITLES;
	}
	
	// Function for handling the filtering of employees
	@RequestMapping(value = "empsfilter", method = RequestMethod.POST)
	public String doEmployeeFilter_POST(String name, Integer departmentId, Integer jobTitle, Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		if(name.isEmpty() && departmentId == null && jobTitle == null) {
			employees = employeeService.findAllEmployees();
		} else {
			String [] names = name.split(" ");
			String firstName = names[0];
			String lastName = null;
			lastName = names.length == 1 ? firstName : names[1];
			if(name.isEmpty() && departmentId != null && jobTitle == null) {
				employees = employeeService.findEmployeeByDepartmentId(departmentId);
			} else if(name.isEmpty() && departmentId == null && jobTitle != null) {
				employees = employeeService.findEmployeeByJobTitleId(jobTitle);
			} else if(!name.isEmpty() && departmentId == null && jobTitle == null) {
				if(names.length == 1) employees = employeeService.findEmployeeByFirstNameOrLastName(firstName, lastName);
				else employees = employeeService.findEmployeeByFirstNameAndLastName(firstName, lastName);
			} else if(!name.isEmpty() && departmentId != null && jobTitle == null) {
				if(names.length == 1) employees = employeeService.findEmployeeByFirstNameAndDepartmentIdOrLastNameAndDepartmentId(firstName, lastName, departmentId);
				else employees = employeeService.findEmployeeByFirstNameAndDepartmentIdAndLastNameAndDepartmentId(firstName, lastName, departmentId);
			} else if(!name.isEmpty() && departmentId == null && jobTitle != null) {
				if(names.length == 1) employees = employeeService.findEmployeeByFirstNameAndJobTitleIdOrLastNameAndJobTitleId(firstName, lastName, jobTitle);
				else employees = employeeService.findEmployeeByFirstNameAndJobTitleIdAndLastNameAndJobTitleId(firstName, lastName, jobTitle);
			}
			else {
				employees = employeeService.findEmployeeByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(name, name, departmentId, jobTitle);
			}
		}
		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobTitles", jobTitles);
		return View.EMPLOYEES;
	}
	
	// Responsible for handling the ajax call to get name suggestions
	@RequestMapping(value = "namesuggestions/{name}", method = RequestMethod.GET)
	public @ResponseBody String getEmployeeNameSuggestions_GET(@PathVariable("name") String name) {
		StringBuilder response = new StringBuilder();
		List<Employee> employees = employeeService.findEmployeeSuggestions(name);
		for(Employee e : employees) {
			response.append(e.getFirstName());
			response.append(" ");
			response.append(e.getLastName());
			response.append(",");
		}
		return response.toString();
	}

}
