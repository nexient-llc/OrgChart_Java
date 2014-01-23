package com.systemsinmotion.orgchart.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}
	
	
	// DEPARTMENT
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		return setUpDepartmentView(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model){
        departmentService.storeDepartment(department);
        
        return setUpDepartmentView(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Integer id, String name, Integer parentId, Model model){
		Department dept = departmentService.findDepartmentByID(id);
		dept.setName(name);
		dept.setParentDepartment(departmentService.findDepartmentByID(parentId));
		departmentService.storeDepartment(dept);
		
		return setUpDepartmentView(model);
	}
	
	@RequestMapping(value = "depts", method = RequestMethod.DELETE)
	public String doDepartments_DELETE(Integer id, Model model){
		departmentService.setDepartmentInactive(departmentService.findDepartmentByID(id));
		
		return setUpDepartmentView(model);
	}
	
	private String setUpDepartmentView(Model model){
		model.addAttribute("newDept", new Department());
        model.addAttribute("depts", departmentService.findActiveDepartments());
        return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	//JOBTITLE
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		return setUpJobTitlesView(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle jobTitle, Model model){
		jobTitleService.storeJobTitle(jobTitle);
		
		return setUpJobTitlesView(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitle_PUT(Integer id, String name, String description, Model model){
		JobTitle job = jobTitleService.findJobTitleById(id);
		job.setName(name);
		job.setDescription(description);
		jobTitleService.storeJobTitle(job);
		
		return setUpJobTitlesView(model);
	}
	
	@RequestMapping(value = "jobs", method = RequestMethod.DELETE)
	public String doJobTile_DELETE(Integer id, Model model){
		jobTitleService.setJobTitleInactive(jobTitleService.findJobTitleById(id));
		
		return setUpJobTitlesView(model);
	}
	
	private String setUpJobTitlesView(Model model){
		model.addAttribute("newJob", new JobTitle());
		model.addAttribute("jobs",jobTitleService.findActiveJobTitles());
		return View.JOB_TITLES;
	}
	
	public void setJobTitleService(JobTitleService jobTitleService){
		this.jobTitleService = jobTitleService;
	}
	
	
	//EMPLOYEE
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployee_GET(Model model) {
		return setUpEmployeeView(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployee_POST(Employee employee, Model model){
		employeeService.storeEmployee(employee);
		
		return setUpEmployeeView(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployee_PUT(Employee employee, Model model){
		Employee emp = employeeService.findEmployeeById(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setMiddleInitial(employee.getMiddleInitial());
		emp.setEmail(employee.getEmail());
		emp.setSkypeName(employee.getSkypeName());
		emp.setDepartment(departmentService.findDepartmentByID(employee.getDepartment().getId()));
		emp.setJobTitle(jobTitleService.findJobTitleById(employee.getJobTitle().getId()));
		employeeService.storeEmployee(employee);
		return setUpEmployeeView(model);
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployee_DELETE(Integer id, Model model){
		employeeService.setEmployeeInactive(employeeService.findEmployeeById(id));
		
		return setUpEmployeeView(model);
	}
	
	private String setUpEmployeeView(Model model){
		model.addAttribute("newEmp", new Employee());
		model.addAttribute("emps", employeeService.findActiveEmployees());
		model.addAttribute("depts", departmentService.findActiveDepartments());
		model.addAttribute("jobs", jobTitleService.findActiveJobTitles());
		
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emps/names", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String doEmployeeNames_GET(){
		return gson.toJson(employeeService.findActiveEmployeesNamesOnly());
	}
	
	@RequestMapping(value = "emps", method = RequestMethod.GET, params = {"fullName", "deptId", "jobId"}, produces="application/json")
	public @ResponseBody String doEmployeeFilter_GET(String fullName, Integer deptId, Integer jobId){
		return gson.toJson(employeeService.findByNameAndOrDepartmentAndOrJob(fullName, deptId, jobId));
	}
}
