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
import com.systemsinmotion.orgchart.service.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DefaultControllerTests {

    @Autowired
    DefaultController controller;

    DepartmentService mockDepartmentService = mock(DepartmentService.class);

    Department mockDepartment = mock(Department.class);
    
    Department mockDepartment2;
    
//    Map model = new HashMap<String, Object>();
    Model model = new ExtendedModelMap();

    private ArrayList<Department> findAllDepartmentsList;
    
    @SuppressWarnings("unused")
    private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

    @Before
    public void before() {
	//instantiate lists
	findAllDepartmentsList = new ArrayList<Department>();
	
	mockDepartment2 = new Department(null, TestObject.DEPARTMENT_NAME, null);
	

	// set up mock Department
	when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
	when(mockDepartment.getName()).thenReturn(TestObject.DEPARTMENT_NAME);
	
	findAllDepartmentsList.add(mockDepartment);


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

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testModelShouldContainNewDepartmentList() {
	//Given
	controller.doDepartments_GET(model);
	//When
	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("depts"));
	//Then
	assertNotNull(findAllDepartmentsList);
	assertEquals(TestObject.DEPT_ID, findAllDepartmentsList.get(0).getDepartmentId());
    }
    
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
