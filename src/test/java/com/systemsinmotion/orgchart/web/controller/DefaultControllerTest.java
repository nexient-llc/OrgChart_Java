package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	Model model;
	
	private ArrayList<Department> findAllDepartmentsList;
	private ArrayList<Employee> findAllEmployeesList;
	private ArrayList<JobTitle> findAllJobTitlesList;
	
	@Before
	public void intialize() throws Exception {
		model = new ExtendedModelMap();
		findAllDepartmentsList = new ArrayList<Department>();
		findAllEmployeesList = new ArrayList<Employee>();
		findAllJobTitlesList = new ArrayList<JobTitle>();

	}

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

//************************************Department Tests**************************************
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewDepartmentList() {
		// Given
		this.controller.doDepartments_GET(this.model);

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
		assertTrue(findAllDepartmentsList.size() == 0);
		findAllDepartmentsList = (ArrayList<Department>)model.asMap().get("depts");

		//Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 0);
		assertEquals(Entities.DEPT_ID, findAllDepartmentsList.get(0).getId());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPagePut() {
		
		//model.addAttribute("depts", findAllDepartmentsList);
		//Given
		controller.doDepartments_PUT(mockDepartment, model);
		//When
		findAllDepartmentsList = (ArrayList<Department>)model.asMap().get("depts");
		
		//Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 0);
		assertEquals(Entities.DEPT_ID, findAllDepartmentsList.get(0).getId());
	}

	@Test
	public void testGetDeptAjax_ReturnsEmptyString_WhenIdIsNull() throws Exception {
		String ajaxResponse = controller.getDeptAjax(null);

		assertEquals("",ajaxResponse);
	}
	
	@Test
	public void testGetDeptAjax_ReturnsEmptyString_WhenIdIsInvalid() throws Exception {
		String ajaxResponse = controller.getDeptAjax(8);
		
		assertEquals("",ajaxResponse);
	}

	@Test
	public void testGetDeptAjax_ReturnsDepartmentString_WhenIdIsValid() throws Exception {
		String ajaxResponse = controller.getDeptAjax(mockDepartment.getId());
		String expectedResponse ="{\"id\":22,\"isActive\":true,\"name\":\"Department "
				+ "name\",\"parentDepartment\":null}";
		
		assertEquals(expectedResponse,ajaxResponse);
	}
	
	@Test
	public void test_DoDepartmentsDelete_ReturnsDepartmentRemovedString_WhenIdIsValid() {
		//Given
		//When
		ResponseEntity<String> response = controller.doDepartments_Delete(mockDepartment.getId());
		
		//Then
		String expectedMessage = "Department " + mockDepartment.getName() + " successfully removed.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
	@Test
	public void test_DoDepartmentsDelete_ReturnsDepartmentNotFound_WhenIdIsNotFound() {
		//Given
		//When
		ResponseEntity<String> response = controller.doDepartments_Delete(7);
		
		//Then
		String expectedMessage = "Invalid Department ID. Department was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
	@Test
	public void test_DoDepartmentsDelete_ReturnsDepartmentNotFound_WhenIdIsNull() {
		//Given
		//When
		ResponseEntity<String> response = controller.doDepartments_Delete(null);
		//Then
		String expectedMessage = "Invalid Department ID. Department was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
//************************************Employee Tests**************************************
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewEmployeeList() {
		// Given
		this.controller.doEmployees_GET("", "", "", "", this.model);

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
		assertTrue(findAllEmployeesList.size() > 0);
		assertEquals(mockEmployee.getId(), findAllEmployeesList.get(0).getId());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnEmployeePagePut() {
		
		model.addAttribute("emps", findAllEmployeesList);
		//Given
		controller.doEmployees_PUT(mockEmployee, model);
		//When
		findAllEmployeesList = (ArrayList<Employee>)model.asMap().get("emps");
		
		//Then
		
		assertEquals(mockEmployee.getId(), findAllEmployeesList.get(0).getId());
	}

	@Test
	public void testGetEmpAjax_ReturnsEmptyString_WhenIdIsNull() throws Exception {
		String ajaxResponse = controller.getEmpAjax(null);
		
		assertEquals("",ajaxResponse);
	}
	
	@Test
	public void testGetEmpAjax_ReturnsEmptyString_WhenIdIsInvalid() throws Exception {
		String ajaxResponse = controller.getEmpAjax(8);

		assertEquals("",ajaxResponse);
	}
	
	@Test
	public void testGetEmpAjax_ReturnsEmployeeString_WhenIdIsValid() throws Exception {
		String ajaxResponse = controller.getEmpAjax(mockEmployee.getId());
		String expectedResponse ="{\"id\":5,\"isActive\":true,\"firstName\":\"first name\""
				+ ",\"lastName\":\"last name\",\"middleInitial\":null,\"email\":\"email\","
				+ "\"skypeName\":\"skype name\",\"isManager\":false,\"department\":{\"id\":null,"
				+ "\"isActive\":true,\"name\":\"Department name\",\"parentDepartment\":null},"
				+ "\"manager\":null,\"jobTitle\":null}";
		
		assertEquals(expectedResponse, ajaxResponse);
	}
	
	@Test
	public void test_DoEmployeeDelete_CallsRemoveEmployee_WhenIdIsValid() {
		//Given
		
		//When
		ResponseEntity<String> response = controller.doEmployee_Delete(mockEmployee.getId());
		
		//Then
		String expectedMessage = "Employee " + mockEmployee.getFirstName() +" "+mockEmployee.getLastName()+ " successfully removed.";
		
		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
	@Test
	public void test_DoEmployeeDelete_DoesNotCallRemoveEmployee_WhenIdIsNotFound() {
		//Given
		
		//When
		ResponseEntity<String> response = controller.doEmployee_Delete(7);
		
		//Then
		String expectedMessage = "Invalid Employee ID. Employee was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
	@Test
	public void test_DoEmployeeDelete_DoesNotCallRemoveEmployee_WhenIdIsNull() {
		//Given
		//When
		ResponseEntity<String> response = controller.doEmployee_Delete(null);
		
		//Then
		String expectedMessage = "Invalid Employee ID. Employee was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
//************************************Job Title Tests**************************************
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewJobTitleList() {
		// Given
		this.controller.doJobTitles_GET(this.model);

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
		assertTrue(findAllJobTitlesList.size() > 0);
		assertEquals(Entities.JOB_TITLE_ID, findAllJobTitlesList.get(0).getId());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnJobTitlePagePut() {
		
		model.addAttribute("titles", findAllJobTitlesList);
		//Given
		controller.doJobTitles_PUT(mockJobTitle, model);
		//When
		findAllJobTitlesList = (ArrayList<JobTitle>)model.asMap().get("titles");
		
		//Then
		assertNotNull(findAllJobTitlesList);
		assertTrue(findAllJobTitlesList.size() > 0);
		assertEquals(Entities.JOB_TITLE_ID, findAllJobTitlesList.get(0).getId());
	}

	@Test
	public void testJobEmpAjax_ReturnsEmptyString_WhenIdIsNull() throws Exception {
		String ajaxResponse = controller.getJobAjax(null);
		
		assertEquals("",ajaxResponse);
	}
	
	@Test
	public void testGetJobAjax_ReturnsEmptyString_WhenIdIsInvalid() throws Exception {
		String ajaxResponse = controller.getJobAjax(8);
		
		assertEquals("",ajaxResponse);
	}

	@Test
	public void testGetJobAjax_ReturnsJobTitleString_WhenIdIsValid() throws Exception {
		String ajaxResponse = controller.getJobAjax(mockJobTitle.getId());
		String expectedResponse = "{\"id\":5,\"isActive\":true,\"name\":\"JobTitle name\"}";
		
		assertEquals(expectedResponse,ajaxResponse);
	}
	
	@Test
	public void testDoJobTitleDelete_CallsRemoveJobTitle_WhenIdIsValid() {
		//Given
		//When
		ResponseEntity<String> response = controller.doJobTitle_Delete(mockJobTitle.getId());
		
		//Then
		String expectedMessage = "Job-Title " + mockJobTitle.getName() + " successfully removed.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
	@Test
	public void testDoJobTitleDelete_DoesNotCallRemoveJobTitle_WhenIdIsNotFound() {
		//Given
		//When
		ResponseEntity<String> response = controller.doJobTitle_Delete(7);
		
		//Then
		
		String expectedMessage = "Invalid Job-Title ID. Job-Title was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
	@Test
	public void testDoJobTitleDelete_DoesNotCallRemoveJobTitle_WhenIdIsNull() {
		//Given
		//When
		ResponseEntity<String> response = controller.doJobTitle_Delete(null);
		
		//Then
		String expectedMessage = "Invalid Job-Title ID. Job-Title was not found.";

		assertEquals(expectedMessage,response.getBody());
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}

}
