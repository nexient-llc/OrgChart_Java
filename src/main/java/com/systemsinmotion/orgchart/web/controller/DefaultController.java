package com.systemsinmotion.orgchart.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
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
	private static final Logger log = LoggerFactory
			.getLogger(DefaultController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JobTitleService jobTitleService;

	// HOME MAPPINGS ---------------------------------------------------------

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String doGet() {
		return View.HOME;
	}

	// -------------------------------------------------------------------------

	// DEPARTMENT MAPPINGS -----------------------------------------------------
	@RequestMapping(value = "depts", method = RequestMethod.GET)
	public String doDepartments_GET(Model model) {
		List<Department> departments = departmentService
				.findAllActiveDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "dept/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDeptAjax(@PathVariable("id") Integer id) {
		Department dept = departmentService.findDepartmentByID(id);
		if (dept == null) {
			return "";
		}
		return dept.toString();
	}

	@RequestMapping(value = "depts", method = RequestMethod.POST)
	public String doDepartments_POST(Department department, Model model) {
		this.departmentService.saveDepartment(department);
		List<Department> departments = departmentService
				.findAllActiveDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "depts", method = RequestMethod.PUT)
	public String doDepartments_PUT(Department department, Model model) {
		this.departmentService.saveDepartment(department);
		List<Department> departments = departmentService
				.findAllActiveDepartments();
		model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

	@RequestMapping(value = "delete/dept/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> doDepartments_Delete(
			@PathVariable("id") Integer id) {
		String responseMessage;
		HttpStatus responseStatus;

		Department department = departmentService.findDepartmentByID(id);
		if (department == null) {
			responseMessage = "Invalid Department ID. Department was not found.";
			responseStatus = HttpStatus.NOT_FOUND;
		} else {
			this.departmentService.removeDepartment(department);
			responseMessage = "Department " + department.getName()
					+ " successfully removed.";
			responseStatus = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<String>(responseMessage, responseStatus);
	}

	// -----------------------------------------------------------------------------------

	// EMPLOYEE MAPPINGS
	// -----------------------------------------------------------------------------------

	@RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
	public @ResponseBody String getEmpAjax(@PathVariable("id") Integer id) {
		Employee emp = employeeService.findEmployeeByID(id);
		if (emp == null) {
			return "";
		}
		return emp.toJson();
	}

	@RequestMapping(value = "delete/emp/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> doEmployee_Delete(
			@PathVariable("id") Integer id) {
		String responseMessage;
		HttpStatus responseStatus;

		Employee employee = employeeService.findEmployeeByID(id);
		if (employee == null) {
			responseMessage = "Invalid Employee ID. Employee was not found.";
			responseStatus = HttpStatus.NOT_FOUND;
		} else {
			this.employeeService.removeEmployee(employee);
			responseMessage = "Employee " + employee.getFirstName() + " "
					+ employee.getLastName() + " successfully removed.";
			responseStatus = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<String>(responseMessage, responseStatus);
	}

	@RequestMapping(value = "emps", method = RequestMethod.GET)
	public String doEmployees_GET(String string, String string2,
			String string3, String string4, Model model) {
		addAllActiveEmployeesToModel(model);
		updateDepartmentsTitlesEmployeeView(model);
		return View.EMPLOYEES;
	}

	private void updateDepartmentsTitlesEmployeeView(Model model) {
		addAllActiveJobTitlesToModel(model);
		addAllActiveDepartmentsToModel(model);
	}

	@RequestMapping(value = "emps", method = RequestMethod.POST)
	public String doEmployees_POST(Employee employee, Model model) {
		this.employeeService.saveEmployee(employee);
		addAllActiveEmployeesToModel(model);
		updateDepartmentsTitlesEmployeeView(model);
		return View.EMPLOYEES;
	}

	@RequestMapping(value = "emps", method = RequestMethod.PUT)
	public String doEmployees_PUT(Employee employee, Model model) {
		this.employeeService.saveEmployee(employee);
		addAllActiveEmployeesToModel(model);
		updateDepartmentsTitlesEmployeeView(model);
		return View.EMPLOYEES;
	}

	// FILTER EMPLOYEE
	// MAPPINGS AND METHODS
	// ------------------------------------------------------------
	@RequestMapping(value = "empsFilter", method = RequestMethod.GET)
	public String doEmployees_FilterTable(Employee employee, Model model) {
		List<Employee> employees = null;
		if (employee.getFirstName() != "") {
			String[] names = employee.getFirstName().split(" ");
			if (names.length == 1){ //user has entered one name; either a first or second
				employees = employeeService.findEmployeeByName(employee.getFirstName(), employee.getFirstName());
			}
			else{
				employees = employeeService.findEmployeeByFullName(names[0],names[1]);
			}
		} else if (employee.getDepartment().getId() != null
				&& employee.getJobTitle().getId() != null) {
			employees = employeeService.findEmployeeByDepartmentIDAndJobID(
					employee.getDepartment().getId(), employee.getJobTitle()
							.getId());
		} else if (employee.getDepartment().getId() != null) {
			employees = employeeService.findEmployeeByDepartmentID(employee
					.getDepartment().getId());
		} else if (employee.getJobTitle().getId() != null) {
			employees = employeeService.findEmployeeByJobID(employee
					.getJobTitle().getId());
		} else {
			employees = employeeService.findAllActiveEmployees();
		}
		model.addAttribute("emps", employees);
		updateDepartmentsTitlesEmployeeView(model);
		return View.EMPLOYEES;
	}
	
	@RequestMapping(value = "emp/autocomplete/{firstName}", method = RequestMethod.GET)
	public @ResponseBody String getEmpsAjax(@PathVariable("firstName") String firstName) {
		String[] names = firstName.split(" ");
		List <Employee> employees = null;
		if (names.length == 1){ 
			employees = employeeService.findEmployeeAutoByName(names[0], names[0]);
		}
		else{
			employees = employeeService.findEmployeeAutoByFullName(names[0],names[1]);
		}
		if (employees == null) {
			return "";
		}
		Gson gson = new Gson();
		String json = gson.toJson(employees);
		return json;
	}

	// ------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------

	// JOB TITLE MAPPINGS
	// -------------------------------------------------------------------------------------

	@RequestMapping(value = "titles", method = RequestMethod.GET)
	public String doJobTitles_GET(Model model) {
		addAllActiveJobTitlesToModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "title/{id}", method = RequestMethod.GET)
	public @ResponseBody String getJobAjax(@PathVariable("id") Integer id) {
		JobTitle jobtitle = jobTitleService.findbyID(id);
		if (jobtitle == null) {
			return "";
		}
		return jobtitle.toString();
	}

	@RequestMapping(value = "title", method = RequestMethod.POST)
	public String doJobTitles_POST(JobTitle jobTitle, Model model) {
		this.jobTitleService.saveJobTitle(jobTitle);
		addAllActiveJobTitlesToModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "title", method = RequestMethod.PUT)
	public String doJobTitles_PUT(JobTitle jobTitle, Model model) {
		this.jobTitleService.saveJobTitle(jobTitle);
		addAllActiveJobTitlesToModel(model);
		return View.JOB_TITLES;
	}

	@RequestMapping(value = "delete/title/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> doJobTitle_Delete(
			@PathVariable("id") Integer id) {
		String responseMessage;
		HttpStatus responseStatus;

		JobTitle jobTitle = jobTitleService.findbyID(id);
		if (jobTitle == null) {
			responseMessage = "Invalid Job-Title ID. Job-Title was not found.";
			responseStatus = HttpStatus.NOT_FOUND;
		} else {
			this.jobTitleService.removeJobTitle(jobTitle);
			responseMessage = "Job-Title " + jobTitle.getName()
					+ " successfully removed.";
			responseStatus = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<String>(responseMessage, responseStatus);
	}

	// --------------------------------------------------------------------------------------
	
	//Log-In & Log-Out Mappings--------------------------------------------------------------
	
	
	@RequestMapping(value = "/app/logout", method = RequestMethod.POST)
	public String doLogout_POST() {
		return View.HOME;
	}

	@RequestMapping(value = "/app/login", method = RequestMethod.POST)
	public String login_POST(){
		return View.HOME;
	}
	
	//----------------------------------------------------------------------------------------
	
	
	private void addAllActiveEmployeesToModel(Model model) {
		List<Employee> employees = employeeService.findAllActiveEmployees();
		model.addAttribute("emps", employees);
	}

	private void addAllActiveDepartmentsToModel(Model model) {
		List<Department> departments = departmentService
				.findAllActiveDepartments();
		model.addAttribute("depts", departments);
	}

	private void addAllActiveJobTitlesToModel(Model model) {
		List<JobTitle> jobTitles = jobTitleService.findAllActiveJobTitles();
		model.addAttribute("titles", jobTitles);
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
