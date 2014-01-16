package com.systemsinmotion.orgchart.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

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
	
	
	// DEPARTMENT
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		setUpDepartmentView(model);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model){
        departmentService.storeDepartment(department);
        
        setUpDepartmentView(model);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Integer id, String name, Integer parentId, Model model){
		Department dept = departmentService.findDepartmentByID(id);
		dept.setName(name);
		dept.setParentDepartment(departmentService.findDepartmentByID(parentId));
		departmentService.storeDepartment(dept);
		
		setUpDepartmentView(model);
		return View.DEPARTMENTS;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Integer id, Model model){
		Department dept = departmentService.findDepartmentByID(id);
		dept.setIsActive(false);
		departmentService.storeDepartment(dept);
		
		setUpDepartmentView(model);
		return View.DEPARTMENTS;
	}
	
	private void setUpDepartmentView(Model model){
		model.addAttribute("newDept", new Department());
        model.addAttribute("depts", departmentService.findAllDepartments());
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	//JOBTITLE
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		setUpJobTitlesView(model);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		
		setUpJobTitlesView(model);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitle_PUT(Integer id, String name, String description, Model model){
		JobTitle job = jobTitleService.findJobTitleById(id);
		job.setName(name);
		job.setDescription(description);
		jobTitleService.storeJobTitle(job);
		
		setUpJobTitlesView(model);
		return View.JOB_TITLES;
	}
	
	private void setUpJobTitlesView(Model model){
		model.addAttribute("newJob", new JobTitle());
		model.addAttribute("jobs",jobTitleService.findAllJobTitles());
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
	
	
	//EMPLOYEE
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployee_GET(Model model) {
		//uncomment when database connection is set up. will throw error when run
		
		setUpEmployeeView(model);
		return View.EMPLOYEES;
	}
	
	private void setUpEmployeeView(Model model){
		model.addAttribute("newEmp", new Employee());
		model.addAttribute("emps",employeeService.findAllEmployees());
	}
}
