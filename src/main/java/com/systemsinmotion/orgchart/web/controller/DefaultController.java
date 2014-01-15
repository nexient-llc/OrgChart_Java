package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@Controller
public class DefaultController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

//	@Autowired
//	EmployeeService employeeService;

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
		 List<Department> departments = departmentService.findAllDepartments();
		 model.addAttribute("dept", new Department());
		 model.addAttribute("depts", departments);
		 return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
    public String doDepartments_POST(Department dept, Model model) {
//		Department parent = dept.getParentDepartment();
//		dept.setParentDepartment(parent.getId() == null ? null : parent);
		departmentService.storeDepartment(dept);
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", dept);
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
    }
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
    public String doDepartments_PUT(Integer id, String name, Integer parentID, Model model) {
		Department updateDept = departmentService.findDepartmentByID(id);
		updateDept.setName(name);
		updateDept.setParentDepartment(departmentService.findDepartmentByID(parentID));
		departmentService.storeDepartment(updateDept);
			
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", new Department());
		model.addAttribute("depts", departments);
        return View.DEPARTMENTS;
    }
	
	@RequestMapping(value = "depts/{id}", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(@PathVariable(value = "id") Integer id, Model model){
		Department deleteDept = departmentService.findDepartmentByID(id);
		departmentService.removeDepartment(deleteDept);
		updateAttributes(model);
		return View.DEPARTMENTS;
	}
	
	private void updateAttributes(Model model){
		List<Department> departments = departmentService.findAllDepartments();
		model.addAttribute("dept", new Department());
		model.addAttribute("depts", departments);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model){
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("job", new JobTitle());
        model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
		model.addAttribute("job", jobTitle);
		model.addAttribute("jobs", jobTitles);
		return View.JOB_TITLES;
	}

}
