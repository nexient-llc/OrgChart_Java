package com.systemsinmotion.orgchart.web.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {
	
	final static Integer DEFAULT_PAGE_SIZE = 20;
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);
	
	final static Integer MAXIMUM_PAGE_SIZE = 50;
			
	@Autowired
	DepartmentService departmentService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JobTitleService jobTitleService;
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService.findAllDepartments();
		String dep = departments.get(0).getManager().getFirstName();
        model.addAttribute("depts", departments);
        return View.DEPARTMENTS;
	}
	@RequestMapping(value = "depts", method = RequestMethod.POST, params = {"page", "size"})
	@ResponseBody 
	public String doDepartments_POST(Integer page, Integer size, Model model) 
			throws JsonGenerationException, JsonMappingException, IOException 
	{
		size = size > MAXIMUM_PAGE_SIZE ? MAXIMUM_PAGE_SIZE : size;
		Page<Department> depts = departmentService.getPage(page, size);
		return this.toJSONArray(depts.getContent());
	}
	
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	@ResponseBody 
	public String doDepartments_POST(Model model) 
			throws JsonGenerationException, JsonMappingException, IOException 
	{
		Page<Department> depts = departmentService.getPage(0, DEFAULT_PAGE_SIZE);
		return this.toJSONArray(depts.getContent());
	}   
	
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(String string, String string2,
			String string3, String string4, Model model) {
		return View.EMPLOYEES;
	}  	
	
	public void doEmployees_POST(Employee mockEmployee, Model model) {
		// TODO Auto-generated method stub
	}
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		System.out.println(employeeService.rowCount());
		return View.HOME;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		return View.JOB_TITLES;
	}

	public void doJobTitles_POST(JobTitle mockJobTitle, Model model) {
		// TODO Auto-generated method stub
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private <T> String toJSONArray(List<T> list) throws JsonGenerationException, JsonMappingException, IOException{
		int size = list.size();
		if(size < 1){
			return null;
		}
		ObjectMapper converter = new ObjectMapper(); 
		String s = "[";
		for(int i = 0; i < size - 1; i++){
			s += converter.writeValueAsString(list.get(i)) + ",";
		}
		s +=  converter.writeValueAsString(list.get(size - 1)) + "]";	
		return s;
	}
}
