package com.nexient.orgchart.web.controller;

import com.nexient.orgchart.web.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String doDefault() {
		return View.ADMIN_DEFAULT;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String doLogin() {
		return View.ADMIN_LOGIN;
	}
}
