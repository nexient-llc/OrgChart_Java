package com.systemsinmotion.orgchart.web.controller;

import java.security.Principal;

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

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		return View.ADMIN_LOGIN;
	}

	@RequestMapping(value = "loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("error", "true");
		return View.ADMIN_LOGIN;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return View.ADMIN_LOGIN;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doDefault() {
		return View.ADMIN_DEFAULT;
	}

	@RequestMapping(value = "default", method = RequestMethod.GET)
	public String printWelcome(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return View.ADMIN_DEFAULT;
	}

	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doAdminDepts(Model model) {
		return "forward:/app/depts";
	}

}
