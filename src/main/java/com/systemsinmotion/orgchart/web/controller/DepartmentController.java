package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "newDepart", method = RequestMethod.POST)
	// @ResponseStatus(HttpStatus.CREATED) // This code is ignored by spring for a redirect...
	public RedirectView doDepartmentNew_POST(Department department, Model model, RedirectAttributes ra) {
		Department newDepartment = departmentService.storeDepartment(department);
		ra.addFlashAttribute("createdDept", newDepartment);
		refreshDepartmentModel(model);
		RedirectView rv = new RedirectView(View.DEPARTMENTS);
		return rv;
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ModelAndView doDepartmentNew_POST_error(Exception e) {
		ModelAndView model = new ModelAndView();
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addObject("depts", departments);
		model.addObject("success", false);
		model.setViewName(View.DEPARTMENTS);
		return model;
	}

	@RequestMapping(value = "updateDepart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doDepartmentUpdate_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		refreshDepartmentModel(model);
		return "redirect:" + View.DEPARTMENTS;
	}

	// public void doDepartments_POST(Department department, @RequestParam(value = "doDelete", defaultValue="false") boolean doDelete, Model model) {

	@RequestMapping(value = "depart/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void doDepartmentDelete_DELETE(@PathVariable("id") Integer deptId, Model model) {
		departmentService.removeDepartmentById(deptId);
		refreshDepartmentModel(model);
	}

	@RequestMapping(value = "findDepart", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean doDepartmentFind_GET(@RequestParam("name") String name, @RequestParam(value = "id", defaultValue = "-1") Integer currentId) {
		List<Department> depts = departmentService.findDepartmentByName(name);
		if (currentId != -1) {
			depts.removeIf(p -> p.getId().equals(currentId));
		}
		if (depts.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		refreshDepartmentModel(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "getDepartments", method = RequestMethod.GET)
	public @ResponseBody Page<Department> doDepartments_ajax_GET(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
		return departmentService.findAllActiveDepartments(new PageRequest(page, 5, new Sort(new Sort.Order(Sort.Direction.ASC, "name").ignoreCase())));
	}

	private void refreshDepartmentModel(Model model) {
		List<Department> departments = departmentService.findAllActiveDepartments();
		model.addAttribute("depts", departments);
	}
}
