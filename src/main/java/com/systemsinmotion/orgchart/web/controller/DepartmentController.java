package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "departments", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "departments", method = RequestMethod.POST)
	public String doDepartments_POST(Department newDepartment,
			@RequestParam("parent_id") Integer parentId, Model model) {
		/* Ensure a valid department was sent in. */
		if (newDepartment == null)
			return View.DEPARTMENTS;

		/* Validate and set the parent id. */
		if (parentId > -1) {
			Department parentDepartment = departmentService
					.findDepartmentByID(parentId);
			newDepartment.setParentDepartment(parentDepartment);
		}

		/* Store the new department. */
		departmentService.storeDepartment(newDepartment);

		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);

		return View.DEPARTMENTS;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
