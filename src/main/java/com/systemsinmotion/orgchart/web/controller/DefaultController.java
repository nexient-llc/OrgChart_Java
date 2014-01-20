package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

	//@Autowired
	//EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;
	
	//@Autowired
	//JobTitleService jobTitleService;
	
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> allDepartments = departmentService.findAllDepartments();
		List<Department> activeDepartments = new ArrayList<Department>();
		for(Department department : allDepartments) {
			if(department.getIsActive() == true) {
				activeDepartments.add(department);
			}
		}
		model.addAttribute("depts", activeDepartments);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Model model, String name, Integer parentDepartmentId) {
		Department department = new Department();
		department.setName(name);
		if(parentDepartmentId != null)
			department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		department.setIsActive(true);
		departmentService.storeDepartment(department);
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Model model, Integer departmentId) {
		Department parentDepartment = departmentService.findDepartmentById(departmentId).getParentDepartment();
		List<Department> departments = departmentService.findDepartmentByParentId(departmentId);
		
		if(parentDepartment != null) {
			for(Department department : departments) {
				System.out.println("Setting Parent Department of " + department.getId() + " to " + parentDepartment.getId());
				department.setParentDepartment(parentDepartment);
				departmentService.storeDepartment(department);
			}
		}
		else {
			for(Department department : departments) {
				department.setParentDepartment(null);
				departmentService.storeDepartment(department);
			}
		}
		
		Department saveDepartment = departmentService.findDepartmentById(departmentId);
		saveDepartment.setParentDepartment(null);
		saveDepartment.setIsActive(false);
		departmentService.storeDepartment(saveDepartment);
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Model model, Integer departmentId, String name, Integer parentDepartmentId) {
		Department department = departmentService.findDepartmentById(departmentId);
		department.setName(name);
		if(parentDepartmentId == null)
			department.setParentDepartment(null);
		else
			department.setParentDepartment(departmentService.findDepartmentById(parentDepartmentId));
		departmentService.storeDepartment(department);
		return doDepartments_GET(model);
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}