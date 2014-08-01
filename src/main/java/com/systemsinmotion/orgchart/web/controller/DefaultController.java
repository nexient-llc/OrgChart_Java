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
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public void doDepartments_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("depts", departments);
	}
	
	
	@RequestMapping(value = "removeDepartment/{id}", method = RequestMethod.POST)
	public @ResponseBody void removeDepartment(@PathVariable("id") Integer id) {
		departmentService.removeDepartment(id);
	}

	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, 
								  @RequestParam(value = "nameParam", defaultValue = "") String name, 
								  @RequestParam(value = "jobTitleParam", defaultValue = "") String jobId, 
								  @RequestParam(value = "departmentParam", defaultValue = "") String deptId, 
								  String string4, Model model) {
		List<Employee> employees = null;
		
		if(pageNum == 0) {
			employees = employeeService.findAllActiveEmployees();
		} else {
			employees = (name.equals("") && deptId.equals("") && jobId.equals("")) ? employeeService.findAllActiveEmployees(pageNum - 1) : employeeService.findAllEmployeesByCriteria(name, deptId, jobId);
		}
		
		List<Department> departments = departmentService.findAllActiveDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "nextPage", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_PAGEABLE(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		String retval = "";
		
		List<Employee> employees = employeeService.findAllActiveEmployees(pageNum);
		
		for(Employee emp : employees) {
			retval += "{\"id\": \"" + emp.getId() + "\"," + 
					  " \"firstName\": \"" + emp.getFirstName() + "\", " +
					  " \"middleInitial\": \"" + emp.getMiddleInitial() + "\", " +
					  " \"lastName\": \"" + emp.getLastName() + "\", " +
					  " \"departmentId\": \"" + emp.getDepartment().getId() + "\", " +
					  " \"departmentName\": \"" + emp.getDepartment().getName() + "\", " +
					  " \"jobTitleId\": \"" + emp.getJobTitle().getId() + "\", " +
					  " \"jobTitleName\": \"" + emp.getJobTitle().getName() + "\", " +
					  " \"email\": \"" + emp.getEmail() + "\", " +
					  " \"skype\": \"" + emp.getSkypeName() + "\"}\n";
		}
		
		return retval;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public void doEmployee_POST(Employee employee, Model model) {
		System.out.println(employee);
		employee.setFirstName(Character.toUpperCase(employee.getFirstName().charAt(0)) + employee.getFirstName().substring(1));
		if(employee.getMiddleInitial() != null)
			employee.setMiddleInitial(Character.toUpperCase(employee.getMiddleInitial()));
		employee.setLastName(Character.toUpperCase(employee.getLastName().charAt(0)) + employee.getLastName().substring(1));
		
		employeeService.storeEmployee(employee);
		
		List<Employee> employees = employeeService.findAllActiveEmployees(0);
		List<Department> departments = departmentService.findAllActiveDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
	}
	
	@RequestMapping(value = "newEmployee", method = RequestMethod.POST)
	public @ResponseBody String insertNewEmployee(Employee newEmployee, Model model) {
		String retVal = "";
		
		newEmployee.setFirstName(Character.toUpperCase(newEmployee.getFirstName().charAt(0)) + newEmployee.getFirstName().substring(1));
		if(newEmployee.getMiddleInitial() != null)
			newEmployee.setMiddleInitial(Character.toUpperCase(newEmployee.getMiddleInitial()));
		newEmployee.setLastName(Character.toUpperCase(newEmployee.getLastName().charAt(0)) + newEmployee.getLastName().substring(1));
		
		employeeService.storeEmployee(newEmployee);
		
		
		// reset newEmployee to the one that's been inserted so we have their id
		newEmployee = employeeService.findByEmail(newEmployee.getEmail());
		
		retVal += "{\"id\": \"" + newEmployee.getId() + "\"," + 
				  " \"firstName\": \"" + newEmployee.getFirstName() + "\", " +
				  " \"middleInitial\": \"" + newEmployee.getMiddleInitial() + "\", " +
				  " \"lastName\": \"" + newEmployee.getLastName() + "\", " +
				  " \"departmentId\": \"" + newEmployee.getDepartment().getId() + "\", " +
				  " \"departmentName\": \"" + newEmployee.getDepartment().getName() + "\", " +
				  " \"jobTitleId\": \"" + newEmployee.getJobTitle().getId() + "\", " +
				  " \"jobTitleName\": \"" + newEmployee.getJobTitle().getName() + "\", " +
				  " \"email\": \"" + newEmployee.getEmail() + "\", " +
				  " \"skype\": \"" + newEmployee.getSkypeName() + "\"}";
		
		return retVal;
	}
	
	
	@RequestMapping(value = "removeEmployee/{id}", method = RequestMethod.POST)
	public @ResponseBody void removeEmployee_POST(@PathVariable("id") Integer id) {
		employeeService.removeEmployee(id);
	}
	
	@RequestMapping(value = "reenableEmployee/{id}", method = RequestMethod.POST)
	public @ResponseBody void reenableEmployee_POST(@PathVariable("id") Integer id) {
		System.out.println("\n\n\n" + id + "\n\n\n");
		employeeService.reenableEmployee(id);
	}
	
	@RequestMapping(value = "checkEmailAndSkype", method = RequestMethod.GET)
	public @ResponseBody String validateUniqueConstraints_GET(@RequestParam("id") String id, @RequestParam("email") String email, @RequestParam("skypeName") String skype, 
			@RequestParam("addOrEdit") String addOrEdit) {
		Employee emailEmp = employeeService.findByEmail(email);
		Employee skypeEmp = employeeService.findBySkypeName(skype);
		String retval = "";
		
		if(addOrEdit.equals("add")) {
			if(emailEmp != null)
				retval = "Email \"" + email + "\" is already in use.";
			if(skypeEmp != null)
				retval = "Skype name \"" + skype + "\" is already in use.";
		}
		else if(addOrEdit.equals("edit")) {
			if(emailEmp != null && !emailEmp.getId().toString().equals(id))
				retval = "Email \"" + email + "\" is already in use.";
			if(skypeEmp != null && !skypeEmp.getId().toString().equals(id))
				retval = "Skype name \"" + skype + "\" is already in use.";
		}
		
		return retval;
	}
	
	@RequestMapping(value = "getInactiveEmployees", method = RequestMethod.GET)
	public String doEmployees_GETINACTIVE(Model model) {
		List<Employee> employees = this.employeeService.findAllInactiveEmployees();
		List<Department> departments = departmentService.findAllActiveDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", jobTitles);
		return View.EMPLOYEES;
	}
	
	
	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<Employee> employees = employeeService.findAllActiveEmployees();
		List<Department> departments = departmentService.findAllActiveDepartments();
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
