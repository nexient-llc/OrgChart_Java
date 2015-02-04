package com.systemsinmotion.orgchart.web.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.systemsinmotion.orgchart.web.View;

@Controller
@SessionAttributes("page")
public class AdminController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String doLogin() {
		return View.ADMIN_LOGIN;
	}

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String doHome() {
		return View.ADMIN_LOGIN;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "login/success", method = RequestMethod.GET)
	public String loginSuccess(Principal principal) {
		if (principal != null && StringUtils.hasText(principal.getName())) {
			return "redirect:/app/home";
		}
		return View.ADMIN_LOGIN;
	}

}