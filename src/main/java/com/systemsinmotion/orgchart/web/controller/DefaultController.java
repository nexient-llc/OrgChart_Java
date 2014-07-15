package com.systemsinmotion.orgchart.web.controller;

import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
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
		// uncomment when database connection is set up. will throw error when
		// run

		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public void doDepartments_POST(Department mockDepartment, Model model) {
		departmentService.storeDepartment(mockDepartment);
		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
	}

	@RequestMapping(value = "depart", method = RequestMethod.GET)
	public @ResponseBody String doDepartment_GET(Integer id) {

		Department foundDepartment = departmentService.findDepartmentByID(id);
		Gson json = new Gson();
		return json.toJson(foundDepartment);
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String doDepartments_DELETE(Department department, Model model) {
		departmentService.removeDepartment(department);
		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public @ResponseBody String doEmployee_GET(Integer id) {

		Employee foundEmployee = employeeService.findEmployeeByID(id);
		Gson json = new Gson();
		return json.toJson(foundEmployee);
	}

	@RequestMapping(value = "filterEmployees", method = RequestMethod.GET)
	public String doEmployees_SEARCH(
			@RequestParam(value = "name") String employeeName,
			@RequestParam(value = "department") String department,
			@RequestParam(value = "jobTitle") String title, String unused,
			Model model) {
		List<Employee> employees = null;
		// start the filtering of the employees.
		if (employeeName == null) {
			// department is not null see @Doc link employeesFoundByDepartment
			if (department != null) {
				employees = employeesFoundByDepartment(department, title);
			} else {
				employees = employeesFoundByJobTitle(title);
			}
		} else{
              employees = employeesFoundWithName(employeeName, department , title); 
		}

		// updateProperties(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		updateProperties(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps/update", method = RequestMethod.POST)
	public String doEmployees_UPDATE(Model model) {
		updateProperties(model);
		return View.EMPLOYEES;
	}
	//left off here. need to clean up some code and fix some posts and gets emps
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public void doEmployees_POST(Employee employee, Model model) {
        employeeService.storeEmployee(employee); 
		updateProperties(model);
	}

	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		List<JobTitle> jobs = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobs);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "titles", method = RequestMethod.POST)
	public void doJobTitles_POST(JobTitle mockJobTitle, Model model) {

		jobTitleService.storeJobTitle(mockJobTitle);
		List<JobTitle> titles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", titles);
	}
    // private methods
	private List<Employee> employeesFoundByDepartment(String department,
			String title) {

		if (title == null) {
			return this.employeeService
					.employeesFoundByDepartmentName(department);
		} else
			return this.employeeService
					.employessFoundByDepartmentNameAndJobName(department, title);
	}

	private List<Employee> employeesFoundByJobTitle(String title) {

		return this.employeeService.employeesFoundByJobTitletName(title);
	}

	private List<Employee> employeesFoundWithName(String name , String ... titleAndDepartment) {

	return null;
	}

	private void updateProperties(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		List<Department> departments = departmentService.activeDepartments();
		List<JobTitle> title = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", title);
	}
}
