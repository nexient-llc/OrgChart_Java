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
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

// import com.systemsinmotion.orgchart.entity.Department;

@Controller
@RequestMapping("depts")
@SessionAttributes("modelDept")
public class DepartmentController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		loadModelData(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doDepartments_POST(@ModelAttribute("modelDept") @Valid Department newDept, Model model) {
		departmentService.storeDepartment(newDept);
		loadModelData(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@PathVariable Integer id, Model model) {
		Department delDept = departmentService.findDepartmentByID(id);
		departmentService.removeDepartment(delDept);
		loadModelData(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String doDepartments_UPDATE(@PathVariable Integer id, @ModelAttribute("modelDept") Department upDept,
			Model model) {
		// Department delDept = departmentService.findDepartmentByID(id);
		departmentService.updateDepartment(upDept);
		loadModelData(model);
		return View.DEPARTMENTS;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private void loadModelData(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		Department newDept = new Department();
		Department parentDept = new Department();
		newDept.setParentDepartment(parentDept);
		model.addAttribute("depts", departments);
		model.addAttribute("modelDept", newDept);
		model.addAttribute("parentDept", parentDept);
	}

}
