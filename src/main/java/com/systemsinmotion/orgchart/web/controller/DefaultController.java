package com.systemsinmotion.orgchart.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

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
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	/* JSON Departments */
	@RequestMapping(value = "json/depts", method = RequestMethod.GET)
	public @ResponseBody String doDepartmentsJSON_GET(Model model) {
		model.addAttribute("depts", departmentService.findDepartmentsByIsActiveTrue());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(model);
			return json;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "";
	}

	@RequestMapping(value = "json/deptsa", method = RequestMethod.GET)
	public @ResponseBody List<Department> doDepartmentsJSONA_GET(Model model) {
		return departmentService.findDepartmentsByIsActiveTrue();
	}
	
	@RequestMapping(value = "json/depts", method = RequestMethod.POST)
	public @ResponseBody String doDepartmentsJSON_POST(String name, Integer parentDepartmentId, Model model) {
		Department department = new Department();
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		department.setIsActive(true);
		departmentService.storeNewDepartment(department);
		return doDepartmentsJSON_GET(model);
	}
	
	@RequestMapping(value = "json/dept/{departmentId}", method = RequestMethod.PUT)
	public @ResponseBody String doDepartmentsJSON_PUT(@PathVariable Integer departmentId, 
			String name, Integer parentDepartmentId, Model model) throws Exception {
		Department department = departmentService.findDepartmentById(departmentId);
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		try {
			departmentService.storeDepartment(department);
		} catch (Exception e) {
			throw e;
		}
		return doDepartmentsJSON_GET(model);
	}
	
	@RequestMapping(value = "json/dept/{departmentId}", method = RequestMethod.DELETE)
	public @ResponseBody String doDepartmentsJSON_DELETE(@PathVariable Integer departmentId, Model model) throws Exception {
		try {
			departmentService.setInactiveDepartment(departmentService.findDepartmentById(departmentId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doDepartmentsJSON_GET(model);
	}
	
	/* JSON Employees */
	@RequestMapping(value = "json/emps", method = RequestMethod.GET)
	public @ResponseBody String doEmployeesJSON_GET(Model model) {
		model.addAttribute("emps", employeeService.findEmployeesByIsActiveTrueOrderByFirstNameAsc());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(model);
			return json;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "";
	}
	
	@RequestMapping(value = "json/emps", method = RequestMethod.POST)
	public @ResponseBody String doEmployeesJSON_POST(String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId, Model model) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployeesJSON_GET(model);
	}
	
	@RequestMapping(value = "json/emp/{employeeId}", method = RequestMethod.PUT)
	public @ResponseBody String doEmployeesJSON_PUT(@PathVariable Integer employeeId, String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId, Model model) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployeesJSON_GET(model);
	}
	
	@RequestMapping(value = "json/emp/{employeeId}", method = RequestMethod.DELETE)
	public @ResponseBody String doEmployeesJSON_DELETE(@PathVariable Integer employeeId, Model model) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setDepartment(null);
		employee.setJobTitle(null);
		employee.setIsActive(false);
		employeeService.storeEmployee(employee);
		return doEmployeesJSON_GET(model);
	}
	
	/* JSON Job Titles */
	@RequestMapping(value = "json/jobs", method = RequestMethod.GET)
	public @ResponseBody String doJobTitlesJSON_GET(Model model) {
		model.addAttribute("jobs", jobTitleService.findJobTitleByIsActiveTrue());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(model);
			return json;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "";
	}
	
	@RequestMapping(value = "json/jobs", method = RequestMethod.POST)
	public @ResponseBody String doJobTitlesJSON_POST(String name, Model model) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(name);
		jobTitle.setIsActive(true);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitlesJSON_GET(model);
	}
	
	@RequestMapping(value = "json/job/{jobTitleId}", method = RequestMethod.PUT)
	public @ResponseBody String doJobTitlesJSON_PUT(@PathVariable Integer jobTitleId, String name, Model model) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitlesJSON_GET(model);
	}
	
	@RequestMapping(value = "json/job/{jobTitleId}", method = RequestMethod.DELETE)
	public @ResponseBody String doJobTitlesJSON_DELETE(@PathVariable Integer jobTitleId, Model model) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setIsActive(false);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitlesJSON_GET(model);
	}
	
	/* Departments */
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		model.addAttribute("depts", departmentService.findDepartmentsByIsActiveTrue());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Model model, String name, Integer parentDepartmentId) {
		Department department = new Department();
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		department.setIsActive(true);
		departmentService.storeNewDepartment(department);
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Model model, Integer departmentId, String name, Integer parentDepartmentId) throws Exception {
		Department department = departmentService.findDepartmentById(departmentId);
		department.setName(name);
		department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		try {
			departmentService.storeDepartment(department);
		} catch (Exception e) {
			throw e;
		}
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Model model, Integer departmentId) {
		try {
			departmentService.setInactiveDepartment(departmentService.findDepartmentById(departmentId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doDepartments_GET(model);
	}
	
	/* Employees */
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		model.addAttribute("emps", employeeService.findEmployeesByIsActiveTrueOrderByFirstNameAsc());
		model.addAttribute("depts", departmentService.findDepartmentsByIsActiveTrue());
		model.addAttribute("jobs", jobTitleService.findJobTitleByIsActiveTrue());
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Model model, String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Model model, Integer employeeId, String firstName, String lastName, String middleInitial,
			Integer departmentId, String email, String skypeName, Integer jobTitleId) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setMiddleInitial(middleInitial);
		employee.setDepartment(departmentService.findDepartmentById(departmentId));
		employee.setEmail(email);
		employee.setSkypeName(skypeName);
		employee.setJobTitle(jobTitleService.findJobTitleById(jobTitleId));
		employee.setIsActive(true);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(Model model, Integer employeeId) {
		Employee employee = employeeService.findEmployeeById(employeeId);
		employee.setDepartment(null);
		employee.setJobTitle(null);
		employee.setIsActive(false);
		employeeService.storeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	/* Job Titles */
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		model.addAttribute("jobs", jobTitleService.findJobTitleByIsActiveTrue());
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(Model model, String name) {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(name);
		jobTitle.setIsActive(true);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(Model model, Integer jobTitleId, String name) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setName(name);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.DELETE)
	public String doJobTitles_DELETE(Model model, Integer jobTitleId) {
		JobTitle jobTitle = jobTitleService.findJobTitleById(jobTitleId);
		jobTitle.setIsActive(false);
		jobTitleService.storeJobTitle(jobTitle);
		return doJobTitles_GET(model);
	}
	
	/* Checkers */
	@RequestMapping(value = "checkDepts", method = RequestMethod.GET)
	@ResponseBody
	public boolean deptNameExists(HttpServletResponse response, @RequestParam String name) {
		return departmentService.findDepartmentByName(name) != null;
	}
	
	@RequestMapping(value = "checkEmpsEmail", method = RequestMethod.GET)
	@ResponseBody
	public boolean empsEmailExists(HttpServletResponse response, @RequestParam String email) {
		return employeeService.findEmployeeByEmail(email) != null;
	}
	
	@RequestMapping(value = "checkEmpsSkypeName", method = RequestMethod.GET)
	@ResponseBody
	public boolean empsSkypeNameExists(HttpServletResponse response, @RequestParam String skypeName) {
		return employeeService.findEmployeeBySkypeName(skypeName) != null;
	}
	
	@RequestMapping(value = "checkJobs", method = RequestMethod.GET)
	@ResponseBody
	public boolean jobNameExists(HttpServletResponse response, @RequestParam String name) {
		return jobTitleService.findJobTitleByName(name) != null;
	}
}