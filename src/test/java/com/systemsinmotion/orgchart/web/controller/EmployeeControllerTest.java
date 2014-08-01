package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class EmployeeControllerTest {

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private Employee mockEmployee;

	Model model = new ExtendedModelMap();
	private List<Employee> findAllEmployeesList;

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewEmployeeList() {
		// Given
		@SuppressWarnings("unused")
		String viewName = this.employeeController.doEmployees_FILTER(" ", " ",
				"", "", this.model);

		// When
		this.findAllEmployeesList = (ArrayList<Employee>) (this.model.asMap()
				.get("emps"));
		// Then
		assertNotNull(this.findAllEmployeesList);
		assertEquals(Entities.EMPLOYEE_ID, this.findAllEmployeesList.get(0)
				.getId());
	}

	@Test
	public void testShouldContainView() {
		// Given

		String viewName = this.employeeController.doEmployees_FILTER(
				Entities.FIRST_NAME, " ", "", "", this.model);

		assertNotNull(viewName);
		assertTrue(View.EMPLOYEES.equals(viewName));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnEmployeePagePost() {

		model.addAttribute("emps", findAllEmployeesList);
		// Given
		// employeeController.doEmployees_POST(mockEmployee, model);
		// When
		findAllEmployeesList = (ArrayList<Employee>) model.asMap().get("emps");

		// Then
		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.size() > 1);
		assertEquals(Entities.EMPLOYEE_ID, findAllEmployeesList.get(1).getId());
		assertEquals(findAllEmployeesList.get(1).getEmail(),
				mockEmployee.getEmail());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdatePost() {

		model.addAttribute("emps", findAllEmployeesList);
		// Given
		// employeeController.doEmployees_UPDATE(mockEmployee, model);
		// When
		findAllEmployeesList = (ArrayList<Employee>) model.asMap().get("emps");

		// Then
		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.size() > 1);
		assertEquals(Entities.EMPLOYEE_ID, findAllEmployeesList.get(1).getId());
		assertEquals(findAllEmployeesList.get(1).getEmail(),
				mockEmployee.getEmail());

	}

	@Test
	public void testControllerShouldReturnJson() {

		model.addAttribute("emps", findAllEmployeesList);

		String json = employeeController.doEmployees_SEARCH(String
				.valueOf(mockEmployee.getFirstName().charAt(0)));
		assertNotNull(json);
		assertTrue(json.length() > 0);

	}

	@Test
	public void testControllerReturnView() {

		model.addAttribute("emps", findAllEmployeesList);
		// Given
		// String view = employeeController.doEmployees_GET(model);
		// When

		// Then
		// assertNotNull(view);
		// assertTrue(view.length() > 0);

	}

}
