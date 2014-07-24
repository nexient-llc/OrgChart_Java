package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class DefaultControllerTest {

	@Autowired
	private DefaultController controller;

	@Autowired
	private DepartmentService mockDepartmentService;

	@Autowired
	private EmployeeService mockEmployeeService;
	
	@Autowired
	private JobTitleService mockJobTitleService;
	
	@Autowired
	private Department mockDepartment;
	
	@Autowired
	private Employee mockEmployee;
	
	@Autowired
	private JobTitle mockJobTitle;


	//	Map model = new HashMap<String, Object>();
	Model model = new ExtendedModelMap();

	private List<Department> findAllDepartmentsList;
	private List<Employee> findAllEmployeesList;
	private List<JobTitle> findAllJobTitlesList;

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

	@Test
	public void testInit() {
		assertNotNull(controller);
		assertNotNull(mockDepartmentService);
		assertNotNull(mockDepartment);
		assertNotNull(mockEmployee);
		assertNotNull(mockJobTitle);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewDepartmentList() {
		// Given
		String viewName = this.controller.doDepartments_GET(this.model);

		// When
		this.findAllDepartmentsList = (List<Department>) (this.model.asMap().get("depts"));
		// Then
		assertNotNull(this.findAllDepartmentsList);
		assertEquals(Entities.DEPT_ID, this.findAllDepartmentsList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPagePost() {

		model.addAttribute("depts", findAllDepartmentsList);
		//Given
		controller.doDepartmentNew_POST(mockDepartment, model);
		//When
		findAllDepartmentsList = (List<Department>)model.asMap().get("depts");

		//Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 1);
		assertEquals(Entities.DEPT_ID, findAllDepartmentsList.get(1).getId());
		assertEquals(findAllDepartmentsList.get(1).getName(), mockDepartment.getName());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewEmployeeList() {
		// Given
		String viewName = this.controller.doEmployees_GET("", "", "", "", this.model);

		// When
		this.findAllEmployeesList = (List<Employee>) (this.model.asMap().get("emps"));
		// Then
		assertNotNull(this.findAllEmployeesList);
		assertEquals(Entities.EMPLOYEE_ID, this.findAllEmployeesList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnEmployeePagePost() {

		model.addAttribute("emps", findAllEmployeesList);
		//Given
		controller.doEmployeeNew_POST(mockEmployee, model);
		//When
		findAllEmployeesList = (List<Employee>)model.asMap().get("emps");

		//Then
		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.size() > 1);
		assertEquals(Entities.EMPLOYEE_ID, findAllEmployeesList.get(1).getId());
		assertEquals(findAllEmployeesList.get(1).getEmail(), mockEmployee.getEmail());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewJobTitleList() {
		// Given
		String viewName = this.controller.doJobTitles_GET(this.model);

		// When
		this.findAllJobTitlesList = (List<JobTitle>) (this.model.asMap().get("jobs"));
		// Then
		assertNotNull(this.findAllJobTitlesList);
		assertEquals(Entities.JOB_TITLE_ID, this.findAllJobTitlesList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePagePost() {

		model.addAttribute("titles", findAllJobTitlesList);
		//Given
		controller.doJobTitleNew_POST(mockJobTitle, model);
		//When
		findAllJobTitlesList = (List<JobTitle>)model.asMap().get("jobs");

		//Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 1);
		assertEquals(Entities.JOB_TITLE_ID, findAllJobTitlesList.get(1).getId());
		assertEquals(findAllJobTitlesList.get(1).getName(), mockJobTitle.getName());

	}

}
