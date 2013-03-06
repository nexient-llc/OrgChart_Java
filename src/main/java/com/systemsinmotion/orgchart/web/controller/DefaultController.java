package com.systemsinmotion.orgchart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	//display the home page
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}

}
