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
	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		// uncomment when database connection is set up. will throw error when
		// run
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("dept", new Department());
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department dept,
			Integer parentDepartmentId, Model model) {
		Department testDepartment = departmentService.findDepartmentByName(dept
				.getName());
		if (testDepartment != null) {
			dept = testDepartment;
		}
		
		Department parent = null;
		if (parentDepartmentId != null) {
			parent = departmentService.findDepartmentById(parentDepartmentId);
		}
		dept.setParentDepartment((parentDepartmentId == null) ? null : parent);
		
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Integer id, String name, Integer parentID,
			Model model) {
		Department dept = departmentService.findDepartmentById(id);
		dept.setName(name);
		dept.setParentDepartment(departmentService.findDepartmentById(parentID));
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Integer id, Model model) {
		Department dept = departmentService.findDepartmentById(id);
		departmentService.removeDepartment(dept);
		
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	
	
	
	
	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String doLogin_GET(){
	// return View.LOGIN;
	// }
	//
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle job, Model model) {
		jobTitleService.storeJobTitle(job);
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitles_PUT(Integer id, String name, Model model) {
		JobTitle job = jobTitleService.findJobTitleById(id);
		job.setName(name);
		jobTitleService.storeJobTitle(job);
		List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		model.addAttribute("jobtitles", jobtitles);
		return View.JOB_TITLES;
	}

	// @RequestMapping(value = "edit", method = RequestMethod.GET)
	// public String doEdit_GET(/*Model model*/){
	// return View.EDIT;
	// }
	//
	// @RequestMapping(value = "ajax", method = RequestMethod.GET)
	// public String doAjax_GET(){
	// return View.AJAX;
	// }

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		addAttributesForEmpsPage(new Employee(), model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee emp, Model model) {
		employeeService.storeEmployee(emp);
		addAttributesForEmpsPage(new Employee(), model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Integer id, String firstName,
			String lastName, String middleInitial, String email,
			String skypeName, Integer departmentId, Integer jobTitleId,
			Model model) {
		Employee emp = employeeService.findEmployeeById(id);
		Department empdept = departmentService.findDepartmentById(departmentId);
		JobTitle empjob = jobTitleService.findJobTitleById(jobTitleId);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setMiddleInitial(middleInitial);
		emp.setDepartment(empdept);
		emp.setEmail(email);
		emp.setSkypeName(skypeName);
		emp.setJobTitle(empjob);
		employeeService.storeEmployee(emp);
		addAttributesForEmpsPage(emp, model);

		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(Integer id, Model model) {
		Employee emp = employeeService.findEmployeeById(id);
		employeeService.removeEmployee(emp);
		addAttributesForEmpsPage(emp, model);

		return View.EMPLOYEES;
	}
	
	
	@RequestMapping(value = "emps", method = RequestMethod.GET, params = {
			"fullName", "deptId", "jobId" })
	public @ResponseBody
	String doEmployeeFilter_GET(String fullName, Integer deptId, Integer jobId) {

		Department dept = null;
		JobTitle job = null;
		if (deptId != null)
			dept = departmentService.findDepartmentById(deptId);
		if (jobId != null)
			job = jobTitleService.findJobTitleById(jobId);

		List<Employee> emps = this.employeeService.findEmployeesByFiltering(
				fullName, dept, job);

		Integer[] empIds = new Integer[emps.size()];
		for (int i = 0; i < emps.size(); i++)
			empIds[i] = emps.get(i).getId();
		return gson.toJson(empIds);
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET, params = {
			"name"})
	public @ResponseBody String doEmployeeAutocomplete_GET(String name) {
		List<Employee> emps = this.employeeService.findEmployeesByLikeName(name);
		List<String> names = new ArrayList<String>();
		for(Employee emp:emps){
			names.add(emp.getFirstName() + " " + emp.getLastName());
		}
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(names);
		return json;
	}
	
	private void addAttributesForEmpsPage(Employee emp, Model model) {
		List<Department> depts = departmentService.findAllActiveDepartments();
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		List<Employee> emps = employeeService.findAllActiveEmployees();
		model.addAttribute("emp", emp);
		model.addAttribute("emps", emps);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
