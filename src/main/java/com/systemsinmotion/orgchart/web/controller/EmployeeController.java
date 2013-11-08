package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private JobTitleService jobTitleService;

	/* Handles employee list landing page. */
	@RequestMapping(method = RequestMethod.GET)
	public String doEmployees_GET(Model model, 
			@RequestParam(value="empFilterString", required = false) String filterString,
			@RequestParam(value="empFilter", required = false) String filterSelect) {
		
		/* Provide information needed by the jsp in the form of attributes. */
		List<Employee> allEmployees = employeeService.findAllEmployees();
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		List<Department> departments = departmentService.findAllDepartments();

		/* Filtering */
		if(filterSelect != null && filterString != null && StringUtils.hasText(filterString)) {
			Employee employee = null;
			List<Employee> employees = null;
			
			if(filterSelect.equals("manager")) {
				try{
					Employee manager = employeeService.findById(Integer.parseInt(filterString));
					employees = employeeService.findEmployeeByManager(manager);
				} catch(Exception e) {
					
				}
			} else if(filterSelect.equals("department")) {
				Department department = departmentService.findByName(filterString);
				employees = employeeService.findEmployeeByDepartment(department);
			} else if(filterSelect.equals("email")) {
				employee = employeeService.findEmployeeByEmail(filterString);
			}
			
			if(null != employees) {
				if(null != employee) {
					employees.add(employee);
				}
				
				if(!employees.isEmpty()) {
					model.addAttribute("emps", employees);
				}
			}
		} else {
			model.addAttribute("emps", allEmployees);
		}
		
		model.addAttribute("jobs", jobTitles);
		model.addAttribute("depts", departments);

		return View.EMPLOYEES;
	}

	/* Handles edit request */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public String doEmployeeEdit_PUT(Employee employee, Model model) {

		employeeService.updateEmployee(employee);

		return "redirect:../" + View.EMPLOYEES;
	}

	/* Handles employee delete requests */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String doEmployeeDelete_POST(@RequestParam String confirmString,
			@RequestParam Integer deleteId, Model model) {
		employeeService.removeEmployee(deleteId, confirmString);
		return "redirect:../" + View.EMPLOYEES;
	}

	/* Handles adding an employee */
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public String doEmployeeAdd_PUT(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		return "redirect:../" + View.EMPLOYEES;
	}

	/* Returns JSON object given an employee ID */
	@RequestMapping(value = "/{id}/json", method = RequestMethod.GET)
	public @ResponseBody
	String doEmployeesPrefillForm_GET(@PathVariable Integer id) {
		Employee employee = this.employeeService.findById(id);

		if (employee != null) {
			Gson gson = new Gson();
			return gson.toJson(employee);
		}

		return null;
	}
}
