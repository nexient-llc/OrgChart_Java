package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "depts", method = RequestMethod.GET)
    public String doDepartments_GET(Model model) {
	// uncomment when database connection is set up. will throw error when
	// run
	List<Department> departments = departmentService.findAllDepartments();
	model.addAttribute("dept", new Department());
	model.addAttribute("depts", departments);
	return View.DEPARTMENTS;
    }

    public void setDepartmentService(DepartmentService departmentService) {
	this.departmentService = departmentService;
    }
    
    public void setJobTitleService(JobTitleService jobTitleService) {
	this.jobTitleService = jobTitleService;
    }
    
    /**
   * 
   * @param dept
   * @param parentDepartmentId
   * @param model
   * @return
   */
  @RequestMapping(value = "depts", method = RequestMethod.POST)
  public String doDepartments_POST(Department dept, String name, Integer parentDepartmentId, Model model) {
	
      dept.setName(name);
      dept.setIsActive(true);
      
      if(parentDepartmentId != null) {
          Department parentDepartment = this.departmentService.findDepartmentByID(parentDepartmentId);
          dept.setParentDepartment(parentDepartment);
      } else {
          dept.setParentDepartment(null);
      }
      
      departmentService.storeDepartment(dept);
      List<Department> departments = departmentService.findAllDepartments();
      
      /*
       * This is the same depts var that is in the departments.jsp file
       */
      model.addAttribute("depts", departments);
      return View.DEPARTMENTS;
  }
  
  /**
   * For edit, and remove.
   * 
   * @param dept
   * @param parentDepartmentId
   * @param model
   * @return
   */
  @RequestMapping(value = "depts", method = RequestMethod.PUT)
  public String doDepartments_PUT(Department dept, String name, Integer parentDepartmentId, String buttonClicked, Model model) {
      
      if(parentDepartmentId != null) {
          Department parentDepartment = this.departmentService.findDepartmentByID(parentDepartmentId);
          dept.setParentDepartment(parentDepartment);
      } else {
          dept.setParentDepartment(null);
      }
      
      if(buttonClicked.equalsIgnoreCase("save")) {
	  dept.setIsActive(true);
      } else {	//Removing department
	  dept.setIsActive(false);
	  //Remove parentDepartmentId from all children departments.
	  List<Department> children = departmentService.findByParentDepartment(dept);
	  
	  for(Department child : children) {
	      System.out.println(">>>>>>>>>>>>>>" + child.getName() + "Removed parent id");
	      child.setParentDepartment(null);
	      departmentService.storeDepartment(child);
	  }
      }
      
      departmentService.storeDepartment(dept);
      List<Department> departments = departmentService.findAllDepartments();
      
      /*
       * This is the same depts var that is in the departments.jsp file
       */
      model.addAttribute("depts", departments);
      return View.DEPARTMENTS;
  }
  
    @RequestMapping(value = "emps", method = RequestMethod.POST, params = {"ajaxName"})
    public @ResponseBody String doEmployees_Filter(String ajaxName) {
	List<Employee> employees = employeeService.findLikeFirstNameOrLikeLastName(ajaxName);
	
	return employees.toString();
    }
  
    @RequestMapping(value = "emps", method = RequestMethod.POST)
    public String doEmployees_POST(Employee emp, String firstName,  String lastName, Integer departmentId, String email, String skypeName, Integer jobTitleId, Model model) {
	Department newDept = departmentService.findDepartmentByID(departmentId);
	JobTitle newJob = jobTitleService.findJobTitleByID(jobTitleId);
	
	emp.setFirstName(firstName);
	emp.setLastName(lastName);
	emp.setDepartment(newDept);
	emp.setEmail(email);
	emp.setSkypeName(skypeName);
	emp.setJobTitle(newJob);
	
	employeeService.storeEmployee(emp);
	
	List<Employee> employees = employeeService.findAllEmployees();
	List<Department> departments = departmentService.findAllDepartments();
	List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
	
	model.addAttribute("emps", employees);
	model.addAttribute("depts", departments);
	model.addAttribute("jobs", jobTitles);
	return View.EMPLOYEES;
    }
    
    @SuppressWarnings({ "unchecked", "null" })
    @RequestMapping(value = "emps", method = RequestMethod.PUT)
    public String doEmployees_PUT(Employee emp, String fullName, String firstName,  String lastName, Integer departmentId, String email, String skypeName, Integer jobTitleId, String buttonClicked, Model model) {
	List<Employee> employees = new ArrayList<Employee>();
	List<Department> departments = new ArrayList<Department>();
	List<JobTitle> jobTitles = new ArrayList<JobTitle>();
	
	//New stuff here before the outer else, not the inner else.
	if(buttonClicked.equalsIgnoreCase("filter")) {
	    if(fullName != null) {
		String[] nameParts = fullName.split(" ");
		int lNameIndex = nameParts.length - 1;
		
		employees.add(employeeService.findByFirstNameAndLastName(nameParts[0], nameParts[lNameIndex]));
		
	    }
	    
	    if(departmentId != null) {
		employees = employeeService.findByDepartment(departmentService.findDepartmentByID(departmentId));
	    }
	    
	    if(jobTitleId != null) {
		employees = employeeService.findByJobTitle(jobTitleService.findJobTitleByID(jobTitleId));
	    }
	    
//	    employees = employeeService.findAllEmployees();
	    
	    departments = departmentService.findAllDepartments();
	    jobTitles = jobTitleService.findAllJobTitles();
	} else {
	    //This was working before filter code was added.
	    Department newDept = departmentService.findDepartmentByID(departmentId);
	    JobTitle newJob = jobTitleService.findJobTitleByID(jobTitleId);
        	
	    emp.setFirstName(firstName);
	    emp.setLastName(lastName);
	    emp.setDepartment(newDept);
	    emp.setEmail(email);
	    emp.setSkypeName(skypeName);
	    emp.setJobTitle(newJob);
        	
	    employeeService.storeEmployee(emp);
        	
	    employees = employeeService.findAllEmployees();
	    departments = departmentService.findAllDepartments();
	    jobTitles = jobTitleService.findAllJobTitles();
	}
	
	model.addAttribute("emps", employees);
	model.addAttribute("depts", departments);
	model.addAttribute("jobs", jobTitles);
	return View.EMPLOYEES;
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "emps", method = RequestMethod.GET)
    public String doEmployees_GET(Model model) {
	List<Employee> employees = employeeService.findAllEmployees();
	List<Department> departments = departmentService.findAllDepartments();
	List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
	
	model.addAttribute("emps", employees);
	model.addAttribute("depts", departments);
	model.addAttribute("jobs", jobTitles);
	return View.EMPLOYEES;
    }
    
    /**
     * Method to handle post requests for job title page.
     * 
     * @param id - job title id
     * @param name - job title name
     * @param model - spring model
     * @return
     */
    @RequestMapping(value = "jobs", method = RequestMethod.POST)
    public String doJobTitles_POST(JobTitle job, String name, Model model) {
	
        job.setName(name);
        
        jobTitleService.storeJobTitle(job);
        List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
        
        /*
         * This is the same jobs var that is in the jobTitles.jsp file
         */
        model.addAttribute("jobs", jobTitles);
	return View.JOB_TITLES;
	
    }
    
    /**
     * For edit, and remove.
     * 
     * @param job
     * @param parentDepartmentId
     * @param model
     * @return
     */
    @RequestMapping(value = "jobs", method = RequestMethod.PUT)
    public String dojobTitles_PUT(JobTitle job, String name, Model model) {
        jobTitleService.storeJobTitle(job);
        List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
        
        /*
         * This is the same jobs var that is in the departments.jsp file
         */
        model.addAttribute("jobs", jobTitles);
        return View.JOB_TITLES;
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    public String doJobTitles_GET(Model model) {
	List<JobTitle> jobTitles = jobTitleService.findAllJobTitles();
	model.addAttribute("jobs", jobTitles);
	return View.JOB_TITLES;
    }

}
