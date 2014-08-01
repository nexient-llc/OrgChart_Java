package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class EmployeeControllerTest {

	@Autowired
	EmployeeController controller;

	@Autowired
	private EmployeeService mockEmployeeService;

	@Autowired
	private Employee mockEmployee;

	@Autowired
	private List<Employee> mockEmployeeList;

	private List<Employee> findAllEmployeesList;

	Model model = new ExtendedModelMap();

	private RedirectAttributes redirectAttributes;

	@Before
	public void init() {
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		mockEmployeeList.clear();
		mockEmployeeList.add(mockEmployee);
		when(mockEmployeeService.findEmployeeBySkype(Entities.SKYPE_NAME)).thenReturn(mockEmployeeList);
		when(mockEmployeeService.findEmployeeByEmail(Entities.EMAIL)).thenReturn(mockEmployeeList);
		redirectAttributes = Mockito.mock(RedirectAttributes.class);
	}

	@Test
	public void testInit() {
		assertNotNull(controller);
		assertNotNull(mockEmployeeService);
		assertNotNull(mockEmployee);
	}

	@Test
	public void doEmployees_GET_containsView() {
		// Given
		String viewName = this.controller.doEmployees_GET("", "", "", "", this.model);

		assertEquals(View.EMPLOYEES, viewName);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void setEmployeeService() {
		this.controller.setEmployeeService(mockEmployeeService);
		// Given
		this.controller.doEmployees_ajax_GET(0, "", "", "", this.model);

		// When
		this.findAllEmployeesList = (List<Employee>) (this.model.asMap().get("emps"));
		// Then
		assertNotNull(this.findAllEmployeesList);
		assertEquals(Entities.EMPLOYEE_ID, this.findAllEmployeesList.get(0).getId());
	}

	@Test
	public void doEmployeeNew_POST() {
		// Given
		controller.doEmployeeNew_POST(mockEmployee, model, redirectAttributes);
		// When
		findAllEmployeesList = mockEmployeeService.findAllActiveEmployees();

		// Then
		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.size() > 1);
		Employee foundEmployee = findAllEmployeesList.get(findAllEmployeesList.size() - 1);
		assertEquals(Entities.EMPLOYEE_ID, foundEmployee.getId());
		assertEquals(foundEmployee.getEmail(), mockEmployee.getEmail());
		verify(mockEmployeeService, atLeastOnce()).storeEmployee(mockEmployee);
	}

	@Test
	public void doEmployeeUpdate_POST() {
		mockEmployee.setEmail(Entities.EMAIL_2);

		controller.doEmployeeUpdate_POST(mockEmployee, model);
		findAllEmployeesList = mockEmployeeService.findAllActiveEmployees();

		assertTrue("Expected size greater than 1 but was: " + findAllEmployeesList.size(), findAllEmployeesList.size() > 1);
		Employee foundEmployee = findAllEmployeesList.get(findAllEmployeesList.size() - 1);
		assertEquals(Entities.EMPLOYEE_ID, foundEmployee.getId());
		assertEquals(foundEmployee.getEmail(), mockEmployee.getEmail());
		verify(mockEmployeeService, atLeastOnce()).storeEmployee(mockEmployee);
	}

	@Test
	public void doEmployeeDelete_DELETE() {
		controller.doEmployeeDelete_DELETE(Entities.EMPLOYEE_ID, model);
		findAllEmployeesList = mockEmployeeService.findAllActiveEmployees();

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.isEmpty());

		// Reset
		controller.doEmployeeNew_POST(mockEmployee, model, redirectAttributes);
		findAllEmployeesList = mockEmployeeService.findAllActiveEmployees();

		assertNotNull(findAllEmployeesList);
		assertFalse(findAllEmployeesList.isEmpty());
	}

	@Test
	public void doSearchEmployees_GET_fullName() {
		String response = controller.doSearchEmployees_GET(Entities.FULL_NAME);
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@Test
	public void doSearchEmployees_GET_firstName() {
		String response = controller.doSearchEmployees_GET(Entities.FIRST_NAME);
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@Test
	public void doSearchEmployees_GET_lastName() {
		String response = controller.doSearchEmployees_GET(Entities.LAST_NAME);
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@Test
	public void doSearchEmployees_GET_null() {
		String response = controller.doSearchEmployees_GET(null);
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@Test
	public void doSearchEmployees_GET_NoSuchEntry() {
		String response = controller.doSearchEmployees_GET(Entities.FULL_NAME_3);
		assertNotNull(response);
		assertTrue(response.isEmpty());
	}

	@Test
	public void doSearchEmployees_GET_ExtraSpacesFirstName() {
		String response = controller.doSearchEmployees_GET("   " + Entities.FIRST_NAME + "   ");
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@Test
	public void doSearchEmployees_GET_ExtraSpacesLastName() {
		String response = controller.doSearchEmployees_GET("   " + Entities.LAST_NAME + "   ");
		assertNotNull(response);
		assertTrue(response.contains(Entities.FULL_NAME));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_null() {
		controller.doEmployees_ajax_GET(0, "", "", "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstName() {
		controller.doEmployees_ajax_GET(0, Entities.FIRST_NAME, "", "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_lastName() {
		controller.doEmployees_ajax_GET(0, Entities.LAST_NAME, "", "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndLastName() {
		controller.doEmployees_ajax_GET(0, Entities.FULL_NAME, "", "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_Department() {
		controller.doEmployees_ajax_GET(0, "", Entities.DEPT_ID.toString(), "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndDepartment() {
		controller.doEmployees_ajax_GET(0, Entities.FIRST_NAME, Entities.DEPT_ID.toString(), "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_lastNameAndDepartment() {
		controller.doEmployees_ajax_GET(0, Entities.LAST_NAME, Entities.DEPT_ID.toString(), "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndLastNameAndDepartment() {
		controller.doEmployees_ajax_GET(0, Entities.FULL_NAME, Entities.DEPT_ID.toString(), "", model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_JobTitle() {
		controller.doEmployees_ajax_GET(0, "", "", Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.FIRST_NAME, "", Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_lastNameAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.LAST_NAME, "", Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndLastNameAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.FULL_NAME, "", Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_DepartmentAndJobTitle() {
		controller.doEmployees_ajax_GET(0, "", Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndDepartmentAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.FIRST_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_lastNameAndDepartmentAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.LAST_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_firstNameAndLastNameAndDepartmentAndJobTitle() {
		controller.doEmployees_ajax_GET(0, Entities.FULL_NAME, Entities.DEPT_ID.toString(), Entities.JOB_TITLE_ID.toString(), model);

		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));
	}

	@Test
	public void doEmployeeFind_GET_nulls() {
		mockEmployee.setSkypeName(null);
		mockEmployee.setEmail(null);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Skype Name is required");
	}

	@Test
	public void doEmployeeFind_GET_onlySkype() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(null);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "That Skype Name is already used");
	}

	@Test
	public void doEmployeeFind_GET_onlySkypeWithId() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(null);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Email is required");
	}

	@Test
	public void doEmployeeFind_GET_onlyEmail() {
		mockEmployee.setSkypeName(null);
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Skype Name is required");
	}

	@Test
	public void doEmployeeFind_GET_onlyId() {
		mockEmployee.setSkypeName(null);
		mockEmployee.setEmail(null);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Skype Name is required");
	}

	@Test
	public void doEmployeeFind_GET_oldSkypeAndOldEmail() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "That Skype Name is already used");
	}

	@Test
	public void doEmployeeFind_GET_oldSkypeAndOldEmailWithId() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Ok");
	}

	@Test
	public void doEmployeeFind_GET_newSkypeAndOldEmail() {
		mockEmployee.setSkypeName(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "That Email is already used");
	}

	@Test
	public void doEmployeeFind_GET_newSkypeAndOldEmailWithId() {
		mockEmployee.setSkypeName(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setEmail(Entities.EMAIL);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Ok");
	}

	@Test
	public void doEmployeeFind_GET_oldSkypeAndNewEmail() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "That Skype Name is already used");
	}

	@Test
	public void doEmployeeFind_GET_oldSkypeAndNewEmailWithId() {
		mockEmployee.setSkypeName(Entities.SKYPE_NAME);
		mockEmployee.setEmail(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Ok");
	}

	@Test
	public void doEmployeeFind_GET_newSkypeAndNewEmail() {
		mockEmployee.setSkypeName(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setEmail(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setId(null);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Ok");
	}

	@Test
	public void doEmployeeFind_GET_newSkypeAndNewEmailAndId() {
		mockEmployee.setSkypeName(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setEmail(Entities.NOT_PRESENT_VALUE);
		mockEmployee.setId(Entities.EMPLOYEE_ID);
		String message = controller.doEmployeeFind_GET(mockEmployee, model);
		assertEquals(message, "Ok");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doEmployees_ajax_GET_returnsAllEmps() {
		Page<Employee> employees = controller.doEmployees_ajax_GET(0, "", "", "", model);
		findAllEmployeesList = (List<Employee>) model.asMap().get("emps");

		assertNotNull(employees);
		assertEquals(Entities.EMPLOYEE_ID, employees.getContent().get(0).getId());
		assertNotNull(findAllEmployeesList);
		assertTrue(findAllEmployeesList.contains(mockEmployee));

	}
}
