package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DefaultControllerTests {

    @Autowired
    DefaultController controller;

    //mock up the service layer
    DepartmentService mockDepartmentService = mock(DepartmentService.class);
    EmployeeService mockEmpService = mock(EmployeeService.class);
    JobTitleService mockJTService = mock(JobTitleService.class);

    //mock the entity objects
    Department mockDepartment = mock(Department.class);
    Employee mockEmployee = mock(Employee.class);
    JobTitle mockJobTitle = mock(JobTitle.class);
    
    Department mockDepartment2;
    
    //create a model to use in the tests
    Model model = new ExtendedModelMap();

    //lists to hold the mock objects returned in the testing
    private ArrayList<Department> findAllDepartmentsList;
    private ArrayList<Employee> findAllEmployeesList;
    private ArrayList<JobTitle> findAllJobTitleList;
    
    @SuppressWarnings("unused")
    private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

    @Before
    public void before() 
    {
	
	    //instantiate lists
		findAllDepartmentsList = new ArrayList<Department>();
		findAllEmployeesList = new ArrayList<Employee>();
		findAllJobTitleList = new ArrayList<JobTitle>();
	
		//set up secondary department for 'add new'
		mockDepartment2 = new Department(null, null, TestObject.DEPARTMENT_NAME, null, null);

		//set up mock Department
		when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
		when(mockDepartment.getName()).thenReturn(TestObject.DEPARTMENT_NAME);
		findAllDepartmentsList.add(mockDepartment);
		
		//set up mock Employee
		when(mockEmployee.getEmpID()).thenReturn(TestObject.EMPLOYEE_ID);
		findAllEmployeesList.add(mockEmployee);
		
		//set up mock JobTitle
		when(mockJobTitle.getJobTitleID()).thenReturn(TestObject.JOB_TITLE_ID);
		when(mockJobTitle.getDesc()).thenReturn(TestObject.JOB_TITLE);
		findAllJobTitleList.add(mockJobTitle);
	
		// set up mock DepartmentService
		when(mockDepartmentService.findAllDepartments()).thenReturn(
			findAllDepartmentsList);
		when(mockDepartmentService.findDepartmentByID(TestObject.DEPT_ID))
			.thenReturn(mockDepartment);
		when(mockDepartmentService.storeDepartment(mockDepartment)).thenReturn(
			TestObject.DEPT_ID);
		when(mockDepartmentService.storeDepartment(mockDepartment2)).thenReturn(
			TestObject.DEPT_ID);
		controller.setDepartmentService(mockDepartmentService);
		
		//set up mock EmployeeService
		when(mockEmpService.findAllEmployees()).thenReturn(
			findAllEmployeesList);
		when(mockEmpService.findEmployeeByID(TestObject.EMPLOYEE_ID))
			.thenReturn(mockEmployee);
		when(mockEmpService.createEmployeeRecord(mockEmployee)).thenReturn(
			TestObject.EMPLOYEE_ID);
		controller.setEmployeeService(mockEmpService);

		//set up mock JobTitleService
		when(mockJTService.findAllJobTitles()).thenReturn(
			findAllJobTitleList);
		when(mockJTService.findByJobTitleID(TestObject.JOB_TITLE_ID))
			.thenReturn(mockJobTitle);
		when(mockJTService.createJobTitle(mockJobTitle)).thenReturn(
			TestObject.JOB_TITLE_ID);
		
    }

//    @SuppressWarnings("unchecked")
//    @Test
//    public void testModelShouldContainNewDepartmentList() 
//    {
//		//Given
//		controller.doDepartments_GET(model);
//		
//		//When
//		findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("depts"));
//		
//		//Then
//		assertNotNull(findAllDepartmentsList);
//		assertEquals(TestObject.DEPT_ID, findAllDepartmentsList.get(0).getDepartmentId());
//		
//    }
    
//    @SuppressWarnings("unchecked")
//    @Test
//    public void testModelShouldUpdateOnDepartmentPagePost() {
//	
//	model.addAttribute("depts", findAllDepartmentsList);
//	//Given
//	controller.doDepartments_POST(mockDepartment2, null, model);
//	//When
//	findAllDepartmentsList = (ArrayList<Department>)model.asMap().get("depts");
//	
//	//Then
//	assertNotNull(findAllDepartmentsList);
//	assertTrue(findAllDepartmentsList.size() > 0);
//	assertEquals(TestObject.DEPT_ID, findAllDepartmentsList.get(1).getDepartmentId());
//	assertEquals(findAllDepartmentsList.get(1).getName(), TestObject.DEPARTMENT_NAME);
//
//    }


}
