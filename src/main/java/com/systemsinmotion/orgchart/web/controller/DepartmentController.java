package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
@RequestMapping("/depts")
public class DepartmentController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("depts", departments);
		model.addAttribute("DEPARTMENT", new Department());
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ModelAndView doDepartments_POST(@ModelAttribute("DEPARTMENT") Department department, BindingResult result){
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(View.DEPARTMENTS);
		mav.addObject("depts", departments);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String doDepartment_PUT(@ModelAttribute("DEPARTMENT") Department department, Model model){
		//TODO: have it go to a show page.
		//if the input form has no value will give a department with all null values
		//cleans input
		try{
			if(department.getParentDepartment().getName() == null){
				department.setParentDepartment(null);
			}
			departmentService.updateDepartment(department);
		} catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		return doDepartments_GET(model);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String doDepartment_DELETE(@ModelAttribute("DEPARTMENT") Department department, Model model){
		departmentService.deleteDepartment(department);
		return doDepartments_GET(model);
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}
