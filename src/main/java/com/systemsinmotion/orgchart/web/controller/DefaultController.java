package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultController.
 * 
 * @author vpeker
 * @author kskronek
 */
@Controller
public class DefaultController {

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	/** The employee service. */
	@Autowired
	EmployeeService employeeService;

	/** The department service. */
	@Autowired
	DepartmentService departmentService;

	/** The job title service. */
	@Autowired
	JobTitleService jobTitleService;

	/**
	 * Do get.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}

	/**
	 * Do departments_ get.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	/**
	 * Do departments_ post.
	 * 
	 * @param newDepartment
	 *            the new department
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department newDepartment, Model model) {
		departmentService.storeDepartment(newDepartment);
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	/**
	 * Do departments_ put.
	 * 
	 * @param department
	 *            the department
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Department department, Model model) {
		this.departmentService.updateDepartment(department);
		getDepartmentsForDepartmentView(model);
		return View.DEPARTMENTS;
	}

	/**
	 * Do departments_pre fill edit form.
	 * 
	 * @param departmentId
	 *            the department id
	 * @return the string
	 */
	@RequestMapping(value = "depts/{departmentId}", method = RequestMethod.GET)
	public @ResponseBody
	String doDepartments_preFillEditForm(
			@PathVariable("departmentId") Integer departmentId) {
		Department dept = this.departmentService
				.findDepartmentByID(departmentId);
		Gson gson = new Gson();
		String json = gson.toJson(dept);
		return json;
	}

	/**
	 * Gets the departments for department view.
	 * 
	 * @param model
	 *            the model
	 * @return the departments for department view
	 */
	private void getDepartmentsForDepartmentView(Model model) {
		List<Department> allDepts = departmentService.findAllDepartments();
		model.addAttribute("depts", allDepts);
	}

	/**
	 * Sets the department service.
	 * 
	 * @param departmentService
	 *            the new department service
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * Do job title_ get.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.GET)
	public String doJobTitle_GET(Model model) {
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	/**
	 * Do job title_ post.
	 * 
	 * @param jobTitle
	 *            the job title
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.POST)
	public String doJobTitle_POST(JobTitle jobTitle, Model model) {
		jobTitleService.storeJobTitle(jobTitle);
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	/**
	 * Do job title_ put.
	 * 
	 * @param jobTitle
	 *            the job title
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "jobs", method = RequestMethod.PUT)
	public String doJobTitle_PUT(JobTitle jobTitle, Model model) {
		this.jobTitleService.updateJobTitle(jobTitle);
		getJobTitlesForJobTitleView(model);
		return View.JOB_TITLES;
	}

	/**
	 * Do job title_pre fill edit form.
	 * 
	 * @param jobId
	 *            the job id
	 * @return the string
	 */
	@RequestMapping(value = "jobs/{jobId}", method = RequestMethod.GET)
	public @ResponseBody
	String doJobTitle_preFillEditForm(@PathVariable("jobId") Integer jobId) {
		JobTitle job = this.jobTitleService.findJobTitleById(jobId);
		Gson gson = new Gson();
		String json = gson.toJson(job);
		return json;
	}

	/**
	 * Gets the job titles for job title view.
	 * 
	 * @param model
	 *            the model
	 * @return the job titles for job title view
	 */
	private void getJobTitlesForJobTitleView(Model model) {
		List<JobTitle> jobTitles = this.jobTitleService.findAllJobTitles();
		model.addAttribute("jobs", jobTitles);
	}

	/**
	 * Do employees_ get.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(Model model) {
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	/**
	 * Do employees_ post.
	 * 
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model) {
		employeeService.addEmployee(employee);
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	/**
	 * Do employees_ put.
	 * 
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Employee employee, Model model) {
		this.employeeService.updateEmployee(employee);
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	/**
	 * Do employees_ delete.
	 * 
	 * @param empId
	 *            the emp id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "emps", method = RequestMethod.DELETE)
	public String doEmployees_DELETE(@RequestParam Integer empId, Model model) {
		this.employeeService.deleteEmployee(this.employeeService
				.findEmployeeById(empId));
		getDepartmentsJobTitlesEmployeesForEmployeeView(model);
		return View.EMPLOYEES;
	}

	/**
	 * Do employees_pre fill edit form.
	 * 
	 * @param employeeId
	 *            the employee id
	 * @return the string
	 */
	@RequestMapping(value = "emps/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String doEmployees_preFillEditForm(@PathVariable("id") Integer employeeId) {
		Employee emp = this.employeeService.findEmployeeById(employeeId);
		Gson gson = new Gson();
		String json = gson.toJson(emp);
		return json;
	}

	@RequestMapping(value = "empsFilterPage", method = RequestMethod.GET)
	private @ResponseBody
	String doGetAllEntitiesForAutocomplete(Model mode) {
		List<Employee> employees = this.employeeService.findAllEmployees();
		Gson gson = new Gson();
		String json = gson.toJson(employees);
		return json;
	}

	/**
	 * Do employees_ filter table.
	 * 
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "empsFilter", method = RequestMethod.GET)
	public String doEmployees_FilterTable(Employee employee, Model model) {
		List<Employee> emps = new ArrayList<Employee>();
		filterEmpByLastName(emps, employee, model);
		filterEmpByFirstName(emps, employee, model);
		filterEmpsByJob(emps, employee, model);
		filterEmpByDept(emps, employee, model);
		model.addAttribute("emps", emps);
		return View.EMPLOYEES;
	}

	/**
	 * Filter emps by job.
	 * 
	 * @param emps
	 *            the emps
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 */
	private void filterEmpsByJob(List<Employee> emps, Employee employee,
			Model model) {

		if (employee.getJobTitle().getId() != null) {
			List<Employee> empsList = this.employeeService
					.findEmployeeByJob(employee.getJobTitle());

			for (Employee emp : empsList) {
				if (!emps.contains(emp)) {
					emps.add(emp);
				}
			}
			getDepartmentsForDepartmentView(model);
			getJobTitlesForJobTitleView(model);
		}
	}

	/**
	 * Filter emp by dept.
	 * 
	 * @param emps
	 *            the emps
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 */
	private void filterEmpByDept(List<Employee> emps, Employee employee,
			Model model) {
		if (employee.getDepartment().getDepartmentId() != null) {
			List<Employee> empsList = this.employeeService
					.findEmployeeByDept(employee.getDepartment());
			for (Employee emp : empsList) {
				if (!emps.contains(emp)) {
					emps.add(emp);
				}
			}
			getDepartmentsForDepartmentView(model);
			getJobTitlesForJobTitleView(model);
		}
	}

	/**
	 * Filter emp by first name.
	 * 
	 * @param emps
	 *            the emps
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 */
	private void filterEmpByFirstName(List<Employee> emps, Employee employee,
			Model model) {
		if (employee.getFirstName() != null
				&& employee.getFirstName().length() > 0) {
			List<Employee> empsList = this.employeeService
					.findEmployeeByFirstName(employee.getFirstName());
			for (Employee emp : empsList) {
				if (!emps.contains(emp)) {
					emps.add(emp);
				}
			}
			getDepartmentsForDepartmentView(model);
			getJobTitlesForJobTitleView(model);
		}
	}

	/**
	 * Filter emp by last name.
	 * 
	 * @param emps
	 *            the emps
	 * @param employee
	 *            the employee
	 * @param model
	 *            the model
	 */
	private void filterEmpByLastName(List<Employee> emps, Employee employee,
			Model model) {
		if (employee.getLastName() != null
				&& employee.getLastName().length() > 0) {
			List<Employee> empsList = this.employeeService
					.findEmployeeByLastName(employee.getLastName());
			for (Employee emp : empsList) {
				if (!emps.contains(emp)) {
					emps.add(emp);
				}
			}
			getDepartmentsForDepartmentView(model);
			getJobTitlesForJobTitleView(model);
		}
	}

	/**
	 * Gets the departments job titles employees for employee view.
	 * 
	 * @param model
	 *            the model
	 * @return the departments job titles employees for employee view
	 */
	private void getDepartmentsJobTitlesEmployeesForEmployeeView(Model model) {
		List<Employee> employees = this.employeeService.findAllEmployees();
		model.addAttribute("emps", employees);
		getDepartmentsForDepartmentView(model);
		getJobTitlesForJobTitleView(model);
	}
}
