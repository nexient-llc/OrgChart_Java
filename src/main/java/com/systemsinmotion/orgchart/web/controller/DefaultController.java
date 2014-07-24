package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metamodel.SessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
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
		refreshDepartmentModel(model);
		return View.DEPARTMENTS;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "newDepart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String doDepartmentNew_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		refreshDepartmentModel(model);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "updateDepart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doDepartmentUpdate_POST(Department department, Model model) {
		departmentService.storeDepartment(department);
		refreshDepartmentModel(model);
		return View.DEPARTMENTS;
	}

//	public void doDepartments_POST(Department department, @RequestParam(value = "doDelete", defaultValue="false") boolean doDelete, Model model) {

	@RequestMapping(value = "depart/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void doDepartmentDelete_DELETE(@PathVariable("id") Integer deptId, Model model)
	{
		departmentService.removeDepartmentById(deptId);
		refreshDepartmentModel(model);
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String doEmployees_GET(@RequestParam(value = "filterName", defaultValue="") String fullName,
			@RequestParam(value = "deptid", defaultValue="") String deptid,
			@RequestParam(value = "jobid", defaultValue="") String jobid,
			String string4,
			Model model) {
		refreshAllModels(model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "searchemps", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String doSearchEmployees_GET(@RequestParam(value = "filterName", defaultValue="") String fullName,
			@RequestParam(value = "deptid", defaultValue="") String deptId,
			@RequestParam(value = "jobid", defaultValue="") String jobId,
			String string4,
			Model model) {
//		List<Employee> employees = getFilteredEmployees_bySwitch(fullName, deptId, jobId);
		List<Employee> employees = getFilteredEmployees_byCriteria(fullName, deptId, jobId);
		model.addAttribute("emps", employees);
		refreshJobTitleModel(model);
		refreshDepartmentModel(model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "searchEmployeeName/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String doSearchEmployees_GET(@PathVariable("name") String fullName) {
		List<SimpleEmployee> employees = getFilteredEmployeeNames(fullName);
		return putCommaDelimitersInAListOfEmployees(employees);
	}
	
	public String putCommaDelimitersInAListOfEmployees(List<SimpleEmployee> employees) {
		String output = new String();
		for (SimpleEmployee emp : employees)
		{
			output += emp.getFirstName() + " " + emp.getLastName() + ",";
		}
		if (output.length() > 0)
		{
			output = output.substring(0,output.length() - 1);
		}
		return output;
	}


//	@RequestMapping(value = "emp/delete/{id}", method = RequestMethod.DELETE)
//	public @ResponseBody ResponseEntity<String> doEmployeeDelete_DELETE(@PathVariable("id") Integer empId, Model model)
//	{
//		employeeService.removeEmployeeById(empId);
//		refreshAllModels(model);
//		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
//	}
	
	@RequestMapping(value = "emp/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void doEmployeeDelete_DELETE(@PathVariable("id") Integer empId, Model model)
	{
		employeeService.removeEmployeeById(empId);
		refreshAllModels(model);
		return;
	}


	// Solution 1: Readable and few lines of code, but requires 2+ queries
//	private List<Employee> getFilteredEmployees_byIntersect(String fullName, String deptId,
//			String jobId) {
//		String[] name = fullName.split("\\s");
//		List<Employee> employees = employeeService.findAllActiveEmployees();
//		if (name[0].length() > 0)
//			employees.retainAll(employeeService.findEmployeesByFirstName(name[0]));
//		if (name.length > 1 && name[1].length() > 0)
//			employees.retainAll(employeeService.findEmployeesByLastName(name[1]));
//		if (deptId.length() > 0)
//			employees.retainAll(employeeService.findEmployeesByDepartment(departmentService.findDepartmentByID(Integer.parseInt(deptId))));
//		if (jobId.length() > 0)
//			employees.retainAll(employeeService.findEmployeesByJobTitle(jobTitleService.findJobTitleByID(Integer.parseInt(jobId))));
//
//		return employees;
//	}

	// Solution 2: Switch Approach, long-winded but correct
//	private List<Employee> getFilteredEmployees_bySwitch(String fullName, String deptId, String jobId)
//	{
//		String[] name = fullName.trim().split("\\s");
//		String firstName = (name.length >= 1 && name[0].length() > 0) ? name[0] : null;
//		String lastName = (name.length > 1 && name[1].length() > 0) ? name[1] : null;
//		Integer department = (deptId.length() > 0) ? Integer.parseInt(deptId) : null;
//		Integer jobTitle = (jobId.length() > 0) ? Integer.parseInt(jobId) : null;
//		return employeeService.findEmployeesByFilter(firstName, lastName, department, jobTitle);
//	}
	
	private List<SimpleEmployee> getFilteredEmployeeNames(String fullName)
	{
		String[] name = fullName.trim().split("\\s");
		String firstName = (name.length >= 1 && name[0].length() > 0) ? name[0] : null;
		String lastName = (name.length > 1 && name[1].length() > 0) ? name[1] : null;
		return employeeService.findEmployeesByNameOnlyFilter(firstName, lastName);
	}

	// Solution 3: Query Criteria
	private List<Employee> getFilteredEmployees_byCriteria(String fullName, String deptId, String jobId)
	{
		String[] name = fullName.trim().split("\\s");
		String firstName = (name.length >= 1 && name[0].length() > 0) ? name[0] : null;
		String lastName = (name.length > 1 && name[1].length() > 0) ? name[1] : null;
		Integer department = (deptId.length() > 0) ? Integer.parseInt(deptId) : null;
		Integer jobTitle = (jobId.length() > 0) ? Integer.parseInt(jobId) : null;
		return employeeService.findEmployeesByCriteriaFilter(firstName, lastName, department, jobTitle);
	}

	@RequestMapping(value = "newEmp", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String doEmployeeNew_POST(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		refreshAllModels(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "updateEmp", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doEmployeeUpdate_POST(Employee employee, Model model) {
		employeeService.storeEmployee(employee);
		refreshAllModels(model);
		return View.EMPLOYEES;
	}

	public void setJobTitleService(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String doJobTitles_GET(Model model) {
		refreshJobTitleModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "newJob", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String doJobTitleNew_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		refreshJobTitleModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "updateJob", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doJobTitleUpdate_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		refreshJobTitleModel(model);
		return View.JOB_TITLES;
	}
	
	@RequestMapping(value = "job/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody ResponseEntity<String> doJobTitleDelete_DELETE(@PathVariable("id") Integer jobId, Model model)
	{
		jobTitleService.removeJobTitleById(jobId);
		refreshAllModels(model);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	private void refreshAllModels(Model model) {
		refreshDepartmentModel(model);
		refreshJobTitleModel(model);
		refreshEmployeeModel(model);
	}
	
	private void refreshJobTitleModel(Model model) {
		List<JobTitle> titles = jobTitleService.findAllActiveJobTitles();
		model.addAttribute("jobs", titles);
	}
	
	private void refreshEmployeeModel(Model model) {
		List<Employee> employees = employeeService.findAllActiveEmployees();
		model.addAttribute("emps", employees);
		model.addAttribute("allEmps", employees);
	}

	private void refreshDepartmentModel(Model model) {
		List<Department> departments = departmentService.findAllActiveDepartments();
		 model.addAttribute("depts", departments);
	}
}
