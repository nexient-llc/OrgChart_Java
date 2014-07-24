package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public void doDepartments_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
	}
	
	
	@RequestMapping(value = "removeDepartment/{id}", method = RequestMethod.POST)
	public @ResponseBody void removeDepartment(@PathVariable("id") Integer id) {
		departmentService.removeDepartment(id);
	}

	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(@RequestParam(value = "nameParam", defaultValue = "") String name, 
								  @RequestParam(value = "jobTitleParam", defaultValue = "") String jobId, 
								  @RequestParam(value = "departmentParam", defaultValue = "") String deptId, 
								  String string4, Model model) {
		List<Employee> employees = null;
		
		employees = (name.equals("") && deptId.equals("") && jobId.equals("")) ? employeeService.findAllEmployees() : employeeService.findAllEmployeesByCriteria(name, deptId, jobId);
		
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
		return View.EMPLOYEES;
	}

	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public void doEmployee_POST(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		
		List<Employee> employees = employeeService.findAllEmployees();
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
	}
	
	
	@RequestMapping(value = "removeEmployee/{id}", method = RequestMethod.POST)
	public @ResponseBody void removeEmployee_POST(@PathVariable("id") Integer id) {
		employeeService.removeEmployee(id);
	}
	
	
	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
		return View.JOB_TITLES;
	}

	
	@RequestMapping(value = "titles", method = RequestMethod.POST)
	public void doJobTitles_POST(JobTitle job, Model model) {
		jobTitleService.storeJobTitle(job);
		
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobTitles);
	}
	
	
	@RequestMapping(value = "suggestions/{name}", method = RequestMethod.GET)
	public @ResponseBody String getEmployeeSuggestions_GET(@PathVariable("name") String name) {
		
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
	
	/*private List<Employee> getSuggestions(String name) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1)
			employees = employeeService.findAllEmployeesByFirstNameIgnoreCase(nameArr[0]);
		else if(nameArr.length == 2)
			employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCase(nameArr[0], nameArr[1]);
		
		if(employees == null || employees.size() == 0)
			return null;
		else
			return employees;
		
	}*/
	
	/*private List<Employee> getEmployees(String name, String deptId, String jobId) {
		List<Employee> employees = null;
		
		String nameArr[] = null;
		
		// if all are empty, get all employees
		if(name.equals("") && deptId.equals("") && jobId.equals(""))
			employees = employeeService.findAllEmployees();
		// if just department is chosen, get all employees by dept id
		else if(name.equals("") && !deptId.equals("") && jobId.equals(""))
			employees = employeeService.findAllEmployeesByDepartmentId(Integer.parseInt(deptId));
		// if just job title is chosen, get all employees by job title id
		else if(name.equals("") && deptId.equals("") && !jobId.equals(""))
			employees = employeeService.findAllEmployeesByJobTitleId(Integer.parseInt(jobId));
		// if dept and job are chosen, get all employees by job title and dept id
		else if(name.equals("") && !deptId.equals("") && !jobId.equals(""))
			employees = employeeService.findAllEmployeesByDepartmentIdAndJobTitleId(Integer.parseInt(deptId), Integer.parseInt(jobId));
		// if name is chosen, find by name (by first name OR first & last name)
		else if(!name.equals("") && deptId.equals("") && jobId.equals("")) {
			nameArr = name.split(" ");
			if(nameArr.length == 1)
				employees = employeeService.findAllEmployeesByFirstNameOrLastName(nameArr[0], nameArr[0]);
			else if(nameArr.length == 2)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCase(nameArr[0], nameArr[1]);
		}
		// if name and department are chosen, find by name and department id
		else if(!name.equals("") && !deptId.equals("") && jobId.equals("")) {
			nameArr = name.split(" ");
			if(nameArr.length == 1)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndDepartmentId(nameArr[0], Integer.parseInt(deptId));
			else if(nameArr.length == 2)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentId(nameArr[0], nameArr[1], Integer.parseInt(deptId));
		}
		// if name and job are chosen, find by department and job title id
		else if(!name.equals("") && deptId.equals("") && !jobId.equals("")) {
			nameArr = name.split(" ");
			if(nameArr.length == 1)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndJobTitleId(nameArr[0], Integer.parseInt(jobId));
			else if(nameArr.length == 2)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleId(nameArr[0], nameArr[1], Integer.parseInt(jobId));
		}
		// if all three are set, query for all
		else if(!name.equals("") && !deptId.equals("") && !jobId.equals("")) {
			nameArr = name.split(" ");
			if(nameArr.length == 1)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleId(nameArr[0], Integer.parseInt(deptId), Integer.parseInt(jobId));
			else if(nameArr.length == 2)
				employees = employeeService.findAllEmployeesByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleId(nameArr[0], nameArr[1], Integer.parseInt(deptId), Integer.parseInt(jobId));
		}
		
		return employees;
	}*/
}
