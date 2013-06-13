package com.systemsinmotion.orgchart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("admin")
@SessionAttributes("page")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String doDefault() {
		return View.ADMIN_DEFAULT;
	}

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doAdminDepts(Model model) {
		// return doDepartments_GET(model);
		return "forward:/app/depts";
	}

	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String doLogin(@RequestParam String page, Model model) {
	// model.addAttribute("page", page);
	// return View.ADMIN_LOGIN;
	// }

	// @RequestMapping(value = "j_spring_security_check", method = RequestMethod.POST)
	// public String do_Security_Check(@RequestParam String page,
	// @RequestParam String j_username, Model model) {
	// // remove the first something/
	// model.addAttribute("page", page.substring(page.lastIndexOf('/') + 1));
	// model.addAttribute("name", j_username);
	// return View.ADMIN_DEFAULT;
	// }

	// private void loadModelData(Model model) {
	// List<Department> departments = departmentService.findAllDepartments();
	// Department newDept = new Department();
	// Department parentDept = new Department();
	// newDept.setParentDepartment(parentDept);
	// model.addAttribute("depts", departments);
	// model.addAttribute("modelDept", newDept);
	// model.addAttribute("parentDept", parentDept);
	// }

}
