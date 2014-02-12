package com.systemsinmotion.orgchart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("admin")
public class AdminController {

	/*@RequestMapping(method = RequestMethod.GET)
	public String doDefault() {
		return View.ADMIN_DEFAULT;
	}*/

	@RequestMapping( method = RequestMethod.GET)
	public String doLogin() {
		return View.ADMIN_LOGIN;
	}
	/*@RequestMapping(value="/app/j_spring_security_check", method=RequestMethod.POST)
	public String getAuthenticated(Model model, String j_username, String j_password){
		return View.ADMIN_DEFAULT;
		
	}*/
}
