package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
    EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
    JobTitleService jobTitleService;
	

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
        //List<Department> departments = departmentService.findAllDepartments();
        //model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

    @RequestMapping(value = "emps", method = RequestMethod.GET)
    public String doEmployee_GET(Model model) {
        //uncomment when database connection is set up. will throw error when run
//		 List<Employee> employee = EmployeeService.findAllEmployees();
//		 model.addAttribute("emps", employee);
        return View.EMPLOYEES;
    }

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    public String doJob_GET(Model model) {
        //uncomment when database connection is set up. will throw error when run
//		 List<JobTitle> jobTitle = JobTitleService.findAllJobTitles();
//		 model.addAttribute("jobs", jobTitle);
        return View.JOB_TITLES;
    }
	
	public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public void setJobTitleService(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }


}
