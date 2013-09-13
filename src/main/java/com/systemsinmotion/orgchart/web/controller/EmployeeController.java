package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class EmployeeController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	JobTitleService jobTitleService;
	
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model){
		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "CreateEmp", method = RequestMethod.POST)
	public ModelAndView doEmployees_POST(Employee employee, Integer number, Model model){
		setJob(employee);
		setDepartment(employee);
		employeeService.storeEmployee(employee);
		List<Employee> employees = employeeService.findAllEmployees();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(View.EMPLOYEES);
		mav.addObject("emps", employees);
		return mav;
	}

	@RequestMapping(value = "EditEmp", method = RequestMethod.POST)
	public String doEmployees_PUT(Employee employee, Model model){
		//TODO: have it go to a show page
		try{
			setJob(employee);
			setDepartment(employee);
			employeeService.updateEmployee(employee);
		} catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		return doEmployees_GET(model);
	}

	@RequestMapping(value = "RemoveEmp", method = RequestMethod.POST)
	public String doEmployee_DELETE(Employee employee, Model model){
		employeeService.removeEmployee(employee);
		return doEmployees_GET(model);
	}
	
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	private void setJob(Employee employee){
		Integer jobTitle = employee.getJobTitle().getId();
		if(jobTitle == 0){
			employee.setJobTitle(null);
		}
		else{
			employee.setJobTitle(this.jobTitleService.findJobTitleByID(jobTitle));
		}
	}
	
	private void setDepartment(Employee employee){
		Integer department = employee.getDepartment().getId();
		if(department == 0){
			employee.setDepartment(null);
		}
		else{
			employee.setDepartment(this.departmentService.findDepartmentByID(department));
		}
	}
}
