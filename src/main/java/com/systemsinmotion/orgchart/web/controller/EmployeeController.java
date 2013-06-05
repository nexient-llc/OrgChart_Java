package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("emps")
@SessionAttributes("modelEmp")
public class EmployeeController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JobTitleService jobTitleService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		loadModelData(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doEmployees_POST(@ModelAttribute("modelEmp") @Valid Employee modelEmp, Model model) {
		employeeService.storeEmployee(modelEmp);
		loadModelData(model);
		return View.EMPLOYEES;
	}

	// delete department with given id
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(@PathVariable Integer id, Model model) {
		Employee delEmp = employeeService.findEmployeeByID(id);
		employeeService.removeEmployee(delEmp);
		loadModelData(model);
		return View.EMPLOYEES;
	}

	// load department with given id into the edit form
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String doEditEmp_GET(@PathVariable Integer id, Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		Employee updateEmp = employeeService.findEmployeeByID(id);
		model.addAttribute("depts", departments);
		model.addAttribute("jobs", jobTitles);
		model.addAttribute("modelEmp", updateEmp);
		return View.EDIT_EMPLOYEE;
	}

	// save changes made in edit form, see above
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public String doEmployees_UPDATE(@ModelAttribute("modelEmp") @Valid Employee upEmp, Model model) {
		employeeService.updateEmployee(upEmp);
		loadModelData(model);
		return View.EMPLOYEES;
	}

	private void loadModelData(Model model) {
		List<Employee> employees = employeeService.findAllEmployees();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		List<Department> departments = departmentService.findAllDepartments();
		Employee newEmp = new Employee();
		model.addAttribute("emps", employees);
		model.addAttribute("jobs", jobTitles);
		model.addAttribute("depts", departments);
		model.addAttribute("modelEmp", newEmp);
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}