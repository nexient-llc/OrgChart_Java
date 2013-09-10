package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("depts")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		loadModel(model);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doDepartments_POST(@ModelAttribute("department") Department department, Model model) {
		this.departmentService.storeDepartment(department);
		loadModel(model);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String doDepartments_PUT(@ModelAttribute("department") Department department, Model model) {
		this.departmentService.updateDepartment(department);
		loadModel(model);
		return View.DEPARTMENTS;
	}
	
	@ModelAttribute("department")
	private Department getModelDepartment() {
		return new Department();
	}
	
	public void setDepartmentService(DepartmentService mockDepartmentService) {
		this.departmentService = mockDepartmentService;
	}
	
	private void loadModel(Model model) {
		List<Department> activeDepartments = this.departmentService.findAllActiveDepartments();
		model.addAttribute("depts", activeDepartments);
	}
}
