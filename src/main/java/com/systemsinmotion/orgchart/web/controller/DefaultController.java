package com.systemsinmotion.orgchart.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.ModelKey;
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
    private DepartmentRepository deptRepo;

    @Autowired
    private EmployeeRepository empsRepo;
    @Autowired
    private JobTitleRepository jobsRepo;

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
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("depts", departments);
		return View.DEPARTMENTS;
	}

    @RequestMapping(value = "emps", method = RequestMethod.GET)
    public String doEmployee_GET(Model model) {
        //uncomment when database connection is set up. will throw error when run
		 List<Employee> employee = employeeService.findAllEmployees();
		 model.addAttribute("emps", employee);
        return View.EMPLOYEES;
    }

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    public String doJob_GET(Model model) {
        //uncomment when database connection is set up. will throw error when run
		 List<JobTitle> jobTitle = jobTitleService.findAllJobTitles();
		 model.addAttribute("jobs", jobTitle);
        return View.JOB_TITLES;
    }

    @RequestMapping(value = "depts", method = RequestMethod.POST)
    public String doDepartments_POST(Department department,Integer parentDepartmentId,Model model) {
        Department result = departmentService.findDepartmentByName(department.getName());

        if(result != null) {
            department = result;
        }

        Department parent = null;
        if(parentDepartmentId != null) {
            parent = departmentService.findDepartmentByID(parentDepartmentId);
            department.setParentDepartment(parent);
        }

        departmentService.storeDepartment(department);
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("dept", department);
        model.addAttribute("depts", departments);

        model.addAttribute("statusMessage", "department.form.msg.success");
        return View.DEPARTMENTS;

    }

    @RequestMapping(value="department", method=RequestMethod.DELETE)
    public String doDepartments_DELETE(Integer id, Model model){
        Department department = departmentService.findDepartmentByID(id);
        departmentService.removeDepartment(department);

        return View.DEPARTMENTS;
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
