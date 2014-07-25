package com.systemsinmotion.orgchart.web.controller;

import java.util.List;
import java.util.StringTokenizer;

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
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		updateProperties(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "autoComplete/{firstName}", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_SEARCH(
			@PathVariable("firstName") String name) {
		String json = employeeService.autoComplete(name);
		System.err.println(json);
		return json;
	}

	@RequestMapping(value = "filterEmployees", method = RequestMethod.GET)
	public String doEmployees_FILTER(
			@RequestParam(value = "firstName", defaultValue = " ") String firstName,
			@RequestParam(value = "lastName", defaultValue = " ") String lastName,
			@RequestParam(value = "department.id", defaultValue = "") String depart,
			@RequestParam(value = "jobTitle.id", defaultValue = "") String title,
			Model model) {

		List<Employee> employees = null;
		StringTokenizer employeeTokenizer = new StringTokenizer(firstName);

		if (employeeTokenizer.countTokens() > 0) {
			firstName = employeeTokenizer.nextToken();
		}
		if (employeeTokenizer.hasMoreTokens()) {
			lastName = employeeTokenizer.nextToken();
		}
		employees = employeeService.filterEmployees(firstName, lastName,
				depart, title);

		model.addAttribute("emps", employees);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("titles", jobTitles);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String doEmployees_UPDATE(Employee employee, Model model) {

		Employee currentEmployee = employeeService.findEmployeeByID(employee
				.getId());

		currentEmployee.setAll(employee);
		employeeService.storeEmployee(currentEmployee);
		updateProperties(model);

		return "redirect:emps";
	}

	// adding employee
	@RequestMapping(value = "addEmployee", method = RequestMethod.POST)
	public void doEmployees_POST(Employee employee, Model model) {

		employeeService.storeEmployee(employee);
		updateProperties(model);
	}

	private void updateProperties(Model model) {
		List<Employee> employees = employeeService.activeEmployees();
		List<Department> departments = departmentService.activeDepartments();
		List<JobTitle> titles = jobTitleService.activeJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", titles);
	}

}
