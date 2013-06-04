package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

//import com.systemsinmotion.orgchart.entity.Department;

@Controller
@SessionAttributes("newEmp")
public class EmployeeController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JobTitleService jobTitleService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		loadValues(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(
			@ModelAttribute("newEmp") @Valid Employee newEmp, Model model) {
		employeeService.storeEmployee(newEmp);
		loadValues(model);
		return View.EMPLOYEES;
	}

	private void loadValues(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		List<Department> departments = departmentService.findAllDepartments();
		Employee newEmp = new Employee();
		model.addAttribute("emps", employees);
		model.addAttribute("jobs", jobTitles);
		model.addAttribute("depts", departments);
		model.addAttribute("newEmp", newEmp);
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// from Mattias Severson @
	// http://www.jayway.com/2012/09/16/improve-your-spring-rest-api-part-i/
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	ErrorMessage handleException(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult()
				.getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size()
				+ globalErrors.size());
		String error;
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getField() + ", "
					+ fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getObjectName() + ", "
					+ objectError.getDefaultMessage();
			errors.add(error);
		}
		return new ErrorMessage(errors);
	}

	@XmlRootElement
	public class ErrorMessage {

		private List<String> errors;

		public ErrorMessage() {
		}

		public ErrorMessage(List<String> errors) {
			this.errors = errors;
		}

		public ErrorMessage(String error) {
			this(Collections.singletonList(error));
		}

		public ErrorMessage(String... errors) {
			this(Arrays.asList(errors));
		}

		public List<String> getErrors() {
			return errors;
		}

		public void setErrors(List<String> errors) {
			this.errors = errors;
		}
	}

}