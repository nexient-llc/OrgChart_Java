package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.entity.Users;
import com.systemsinmotion.orgchart.service.UsersService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class UsersController {

	@Autowired
	UsersService userService;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String doUsers_GET(Model model) {

		List<Users> users = userService.findAllUsers();

		model.addAttribute("admin", users);

		return View.ADMIN_DEFAULT;
	}

}
