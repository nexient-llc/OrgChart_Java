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
	
	@RequestMapping(value = "createDept", method = RequestMethod.POST )
	public ModelAndView doDepartments_POST(@ModelAttribute("DEPARTMENT") Department department, BindingResult result){
		setParent(department);
		departmentService.storeDepartment(department);
		List<Department> departments = departmentService.findAllDepartments();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(View.DEPARTMENTS);
		mav.addObject("depts", departments);
		return mav;
	}
	
	@RequestMapping(value = "editDept", method = RequestMethod.POST)
	public String doDepartment_PUT(@ModelAttribute("DEPARTMENT") Department department, Model model){
		//TODO: have it go to a show page.
		try{
			setParent(department);
			departmentService.updateDepartment(department);
		} catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		return doDepartments_GET(model);
	}
	
	@RequestMapping(value = "removeDept", method = RequestMethod.POST)
	public String doDepartment_DELETE(@ModelAttribute("DEPARTMENT") Department department, Model model){
		departmentService.deleteDepartment(department);
		return doDepartments_GET(model);
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	private void setParent(Department department){
		//checks if no parent specified. If not set parent department to null
		//if one is specified then find that parent department and associate it
		//to the department being saved
		Integer parentId = department.getParentDepartment().getId();
		if(parentId == null){
			department.setParentDepartment(null);
		}
		else{
			department.setParentDepartment(this.departmentService.findDepartmentByID(parentId));
		}
	}
}
