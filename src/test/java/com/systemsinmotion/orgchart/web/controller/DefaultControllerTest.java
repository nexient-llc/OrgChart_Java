package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class DefaultControllerTest {

	@Autowired
	private DefaultController controller;

	@Autowired
	private DepartmentService mockDepartmentService;

	@Autowired
	private Department mockDepartment;
	
	@Autowired
	private Employee mockEmployee;
	
	@Autowired
	private JobTitle mockJobTitle;


	//	Map model = new HashMap<String, Object>();
	Model model = new ExtendedModelMap();

	private ArrayList<Department> findAllDepartmentsList;
	private ArrayList<Employee> findAllEmployeesList;
	private ArrayList<JobTitle> findAllJobTitlesList;

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";


	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewDepartmentList() {
		// Given
		String viewName = this.controller.doDepartments_GET(this.model);

		// When
		this.findAllDepartmentsList = (ArrayList<Department>) (this.model.asMap().get("depts"));
		// Then
		assertNotNull(this.findAllDepartmentsList);
		assertEquals(Entities.DEPT_ID, this.findAllDepartmentsList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPagePost() {

		model.addAttribute("depts", findAllDepartmentsList);
		//Given
		controller.doDepartments_POST(mockDepartment, model);
		//When
		findAllDepartmentsList = (ArrayList<Department>)model.asMap().get("depts");

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
		String viewName = this.controller.doEmployees_SEARCH("", "", "", "", this.model);

		// When
		this.findAllEmployeesList = (ArrayList<Employee>) (this.model.asMap().get("emps"));
		// Then
		assertNotNull(this.findAllEmployeesList);
		assertEquals(Entities.EMPLOYEE_ID, this.findAllEmployeesList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnEmployeePagePost() {

		model.addAttribute("emps", findAllEmployeesList);
		//Given
		controller.doEmployees_POST(mockEmployee, model);
		//When
		findAllEmployeesList = (ArrayList<Employee>)model.asMap().get("emps");

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
		this.findAllJobTitlesList = (ArrayList<JobTitle>) (this.model.asMap().get("titles"));
		// Then
		assertNotNull(this.findAllJobTitlesList);
		assertEquals(Entities.JOB_TITLE_ID, this.findAllJobTitlesList.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePagePost() {

		model.addAttribute("titles", findAllJobTitlesList);
		//Given
		controller.doJobTitles_POST(mockJobTitle, model);
		//When
		findAllJobTitlesList = (ArrayList<JobTitle>)model.asMap().get("titles");

		//Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 1);
		assertEquals(Entities.JOB_TITLE_ID, findAllJobTitlesList.get(1).getId());
		assertEquals(findAllJobTitlesList.get(1).getName(), mockJobTitle.getName());

	}

}
