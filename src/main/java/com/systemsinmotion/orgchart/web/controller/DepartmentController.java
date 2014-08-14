package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@SuppressWarnings("restriction")
@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// department methods
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {

		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "deptAdd", method = RequestMethod.POST)
	public String doDepartment_POST(Department department, Model model,
			@RequestParam(value = "isActive", required = false) Object active) {
		departmentService.storeDepartment(department, active);
		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
		return "redirect:depts";

	}

	@RequestMapping(value = "depart", method = RequestMethod.GET)
	public @ResponseBody String doDepartment_GET(Integer id) {

		Department department = departmentService.findDepartmentByID(id);
		Gson json = new Gson();
		return json.toJson(department);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String doDepartments_DELETE(Department department, Model model) {

		departmentService.removeDepartment(department);
		List<Department> departments = departmentService.activeDepartments();
		model.addAttribute("depts", departments);
		return "redirect:depts";
	}
}
