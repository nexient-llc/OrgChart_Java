package com.systemsinmotion.orgchart.web.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private JobTitleService jobTitleService;

	// Initial bring up to display current active employees
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model, HttpServletRequest request) {
		if (request.isUserInRole("ADMIN")) {
			updatePropertiesForAdminRights(model);
		} else {
			updateProperties(model);
		}

		return View.EMPLOYEES;
	}

	// Auto complete for searching for an employee
	@RequestMapping(value = "autoComplete/{firstName}", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_SEARCH(
			@PathVariable("firstName") String name) {
		String json = employeeService.autoComplete(name);

		return json;
	}

	// auto fill for edit employee text boxes
	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public @ResponseBody String doEmployees_EMP(Integer id) {

		Gson json = new Gson();
		return json.toJson(employeeService.findEmployeeByID(id));

	}

	// filter for searching for an employee
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

	// adding employee
	@PreAuthorize("ADMIN")
	@RequestMapping(value = "addEmployee", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee,
			@RequestParam(value = "isActive", required = false) Object active,
			Model model, HttpServletRequest request) {

		if (employee.getId() == null) {
			employeeService.storeEmployee(employee, active);
		} else {
			this.doEmployees_EDIT(employee, active, model, request);
		}
		updatePropertiesForAdminRights(model);
		return "redirect:emps";
	}

	private String doEmployees_EDIT(Employee employee,
			@RequestParam(value = "isActive", required = false) Object active,
			Model model, HttpServletRequest request) {

		if (request.isUserInRole("ADMIN")) {
			employeeService.editEmployee(employee, active);
		}

		return "redirect:emps";
	}

	@PreAuthorize("ADMIN")
	@RequestMapping(value = "deleteEmployee", method = RequestMethod.GET)
	public @ResponseBody String deleteEmployee(Integer id) {

		this.employeeService.deleteEmployee(id);
		return "redirect:emps";

	}

	// private void mailer() {
	// MimeMessage mailer = mailSender.createMimeMessage();
	// MimeMessageHelper email;
	// try {
	// email = new MimeMessageHelper(mailer, false, "utf-8");
	// email.setTo("brundel25@gmail.com");
	// email.setSubject("User name");
	// email.setText("user");
	//
	// } catch (MessagingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// mailSender.send(mailer);
	// }

	private void updateProperties(Model model) {
		List<Employee> employees = employeeService.activeEmployees();
		List<Department> departments = departmentService.activeDepartments();
		List<JobTitle> titles = jobTitleService.activeJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", titles);
	}

	private void updatePropertiesForAdminRights(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> titles = jobTitleService.findAllJobTitles();
		model.addAttribute("emps", employees);
		model.addAttribute("depts", departments);
		model.addAttribute("titles", titles);
	}

}
