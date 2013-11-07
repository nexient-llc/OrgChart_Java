package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doDepartments_POST(Department newDepartment,
			@RequestParam("parent_id") Integer parentId, Model model) {

		departmentService.storeDepartment(newDepartment);

		return doDepartments_GET(model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public String doDepartmentsEdit_PUT(Department department, Model model) {
		/*
		 * See removeDepartment comment regarding the try/catch. Same applies
		 * here.
		 */
		try {
			departmentService.updateDepartment(department);
		} catch (Exception e) {
		}

		return "redirect:" + "../" + View.DEPARTMENTS;
	}

	@RequestMapping(value = "/{id}/json", method = RequestMethod.GET)
	public @ResponseBody
	String doDepartmentPrefillForm_GET(@PathVariable Integer id) {
		Department department = departmentService.findDepartmentByID(id);

		Gson gson = new Gson();

		if (department != null) {
			return gson.toJson(department);
		}

		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	String doDepartmentDelete_POST(
			@RequestParam("confirmString") String confirmString,
			@RequestParam("deleteId") Integer deleteId, Model model) {

		/*
		 * If an exception is thrown it's likely because employees have this
		 * department listed as their department and depend on this entry
		 * existing. For now we'll just silently catch it and redisplay the
		 * page.
		 */
		try {
			departmentService.removeDepartmentConfirm(deleteId, confirmString);
		} catch (Exception e) {
		}

		return "redirect:" + "../" + View.DEPARTMENTS;
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	String doDepartmentAdd_PUT(Department department, Model model) {
		departmentService.storeDepartment(department);
		return "redirect:" + "../" + View.DEPARTMENTS;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
