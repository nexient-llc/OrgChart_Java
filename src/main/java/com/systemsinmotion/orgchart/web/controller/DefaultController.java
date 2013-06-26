package com.systemsinmotion.orgchart.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Department;
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
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department newDept, Integer parent_id, Model model) {
			newDept.setParentDepartment(departmentService.findDepartmentByID(parent_id));
			newDept.setId(departmentService.storeDepartment(newDept));
			List<Department> departments = departmentService.findAllDepartments();
			model.addAttribute("depts", departments);
			return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Department changedDept, Integer parent_id, Model model) {
			changedDept.setParentDepartment(departmentService.findDepartmentByID(parent_id));
			departmentService.updateDepartment(changedDept);
			List<Department> departments = departmentService.findAllDepartments();
			model.addAttribute("depts", departments);
			return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(String id, Model model) {
			Integer id_int = Integer.parseInt(id);
			Department deptToDelete = departmentService.findDepartmentByID(id_int);
			departmentService.removeDepartment(deptToDelete);
			List<Department> departments = departmentService.findAllDepartments();
			model.addAttribute("depts", departments);
			return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		 String[] employees = {"a","b","c"};
		 model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		 List<JobTitle> jobtitles = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobtitles);
		return View.JOB_TITLES;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
