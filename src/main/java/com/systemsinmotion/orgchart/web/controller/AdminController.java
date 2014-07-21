package com.systemsinmotion.orgchart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.service.UserEntityService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	UserEntityService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String doDefault() {
		return View.ADMIN_DEFAULT;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String doLogin() {
		return View.ADMIN_LOGIN;
	}

	@RequestMapping(value = "j_spring_security_check", method = RequestMethod.POST)
	public String doLogin_POST(@RequestParam("j_username") String username,
			@RequestParam("j_password") String password) {

		if (userService.findUserByUserName(username) != null
				&& userService.findByUserPassword(password) != null) {
			return View.ADMIN_DEFAULT;
		} else {
			return View.ADMIN_LOGIN;
		}
	}
}
