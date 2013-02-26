package com.systemsinmotion.orgchart.web.controller;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
public class DepartmentControllerTest {

	@Autowired
	DepartmentController controller;
	
	//mock the service layer
	DepartmentService mockDeptService = mock(DepartmentService.class);
	
	//mock entity object
	Department mockDept = mock(Department.class);
	Department mockDept2;
	
	
	//model for test use
	Model model = new ExtendedModelMap();
	
	//testing variables for holding returned values
    private ArrayList<Department> findAllDepartmentsList;
    private Department testValue;
    private String errorMsg;
    private static final String NOT_PRESENT_VALUE = "XXX";
	private static final Integer NOT_VALID_ID = -666;
	private static final Integer NOT_PRESENT_ID = 666;
    
    //set up
    @Before
    public void before()
    {
    	//instantiate the list
    	findAllDepartmentsList = new ArrayList<Department>();
    	
    	//set up the secondary dept object
    	mockDept2 = new Department(null, null, TestObject.DEPARTMENT_NAME, null, null);
    	
    	//set up the mocks
    	when(mockDept.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
		when(mockDept.getName()).thenReturn(TestObject.DEPARTMENT_NAME);
		findAllDepartmentsList.add(mockDept);
		
		when(mockDeptService.findAllDepartments()).thenReturn(findAllDepartmentsList);
		when(mockDeptService.findDepartmentByID(TestObject.DEPT_ID)).thenReturn(mockDept);
		when(mockDeptService.findDepartmentByName(TestObject.DEPARTMENT_NAME)).thenReturn(findAllDepartmentsList);
		when(mockDeptService.storeDepartment(mockDept)).thenReturn(TestObject.DEPT_ID);
		when(mockDeptService.storeDepartment(mockDept2)).thenReturn(TestObject.DEPT_ID);
		controller.setDepartmentService(mockDeptService);
    }
    
    //test find by ID - match found
    @SuppressWarnings("unchecked")
	@Test
    public void findDeptByIDTest_matchFound()
    {
    	//controller command
    	controller.findDeptByID(TestObject.DEPT_ID, model);
    	
    	//returned values, expected: single matched dept object and dept list
    	testValue = (Department) (model.asMap().get("editDept"));
    	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("deptsList"));
    	
    	//verify returns
    	assertThat(testValue, describedAs("Department object instance", is(instanceOf(Department.class))));
    	assertThat(testValue.getDepartmentId(), describedAs("22", is(TestObject.DEPT_ID)));
    	assertThat(findAllDepartmentsList, describedAs("Not null", is(not(nullValue()))));
    	assertThat(findAllDepartmentsList, describedAs("Size greater than 0", hasSize(greaterThan(0))));
    }
    
    //test find by ID - no valid ID provided
    @Test
    public void findDeptByIDTest_noValidIDProvided()
    {
    	//controller command
    	controller.findDeptByID(NOT_VALID_ID, model);
    	
    	//returned values, expected: failure message string
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("No valid Department ID provided. Please try again.", 
    					is("No valid Department ID provided. Please try again.")));
    }
    
    //test find by ID - no match found
    @Test
    public void findDeptByIDTest_noMatchFound()
    {
    	//controller command
    	controller.findDeptByID(NOT_PRESENT_ID, model);
    	
    	//returned values, expected: failure message string
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, describedAs("No department found. Please try again.", is("No department found. Please try again.")));
    }
    
    //test find by name - single match found
    @SuppressWarnings("unchecked")
	@Test
    public void findDeptByNameTest_matchFound()
    {
    	//controller command
    	controller.findByDeptName(TestObject.DEPARTMENT_NAME, model);
    	
    	//returned values, expected: single matching dept and dept list
    	testValue = (Department) (model.asMap().get("editDept"));
    	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("deptsList"));
    	
    	//verify returns
    	assertThat(testValue, describedAs("Department object instance", is(instanceOf(Department.class))));
    	assertThat(testValue.getName(), describedAs("Department", is(TestObject.DEPARTMENT_NAME)));
    	assertThat(findAllDepartmentsList, describedAs("Not null", is(not(nullValue()))));
    	assertThat(findAllDepartmentsList, describedAs("Size greater than 0", hasSize(greaterThan(0))));
    }
    
    //test find by name - multiple matches found
    @SuppressWarnings("unchecked")
    @Test
	public void findDeptByNameTest_multipleMatchesFound()
    {
    	//set the list to hold multiple returns
    	findAllDepartmentsList.add(mockDept2);
    	
    	//controller command
    	controller.findByDeptName(TestObject.DEPARTMENT_NAME, model);
    	
    	//returned values, expected: dept list
    	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("depts"));
    	
    	//verify returns
    	assertThat(findAllDepartmentsList, describedAs("Not null", is(not(nullValue()))));
    	assertThat(findAllDepartmentsList, describedAs("Size greater than 1", hasSize(greaterThan(1))));
    	assertThat(findAllDepartmentsList.get(0).getDepartmentId(), describedAs("22", is(TestObject.DEPT_ID)));
    }
    
    //test find by name - no match(es) found
    @Test
	public void findDeptByNameTest_noMatchesFound()
    {
    	//controller command
    	controller.findByDeptName(NOT_PRESENT_VALUE, model);
    	
    	//returned values, expected: msg string
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("No matching department names found. Please try again.", 
    					is("No matching department names found. Please try again.")));
    }
    
    //test find by name - null case
    @Test
	public void findDeptByNameTest_nullCase()
    {
    	//controller command
    	controller.findByDeptName(null, model);
    	
    	//returned values, expected: msg string
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("We're sorry, but no departments were found. Please try again.", 
    					is("We're sorry, but no departments were found. Please try again.")));
    }
    
    //test display all
    @SuppressWarnings("unchecked")
	@Test
    public void viewAllDepartmentsTest()
    {
    	//controller command
    	controller.viewAllDepts(model);
    	
    	//returned values, expected: dept list
    	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("depts"));
    	
    	//verify returns
    	assertThat(findAllDepartmentsList, describedAs("Not null", is(not(nullValue()))));
    	assertThat(findAllDepartmentsList, describedAs("Size greater than 0", hasSize(greaterThan(0))));
    	assertThat(findAllDepartmentsList.get(0).getDepartmentId(), describedAs("22", is(TestObject.DEPT_ID)));
    	
    }
    
    //test fetch for add new
    @SuppressWarnings("unchecked")
	@Test
    public void fetchDepartmentListTest()
    {
    	//controller command
    	controller.fetchDepartmentList(model);
    	
    	//returned values, expected: dept list
    	findAllDepartmentsList = (ArrayList<Department>) (model.asMap().get("addDept"));
    	
    	//verify returns
    	assertThat(findAllDepartmentsList, describedAs("Not null", is(not(nullValue()))));
    	assertThat(findAllDepartmentsList, describedAs("Size greater than 0", hasSize(greaterThan(0))));
    	assertThat(findAllDepartmentsList.get(0).getDepartmentId(), describedAs("22", is(TestObject.DEPT_ID)));
    	
    }
  
    //test update - valid inputs
    @Test
    public void updateDepartmentTest_validInputs()
    {
    	//controller command
    	controller.updateDepartment(mockDept, TestObject.DEPT_ID, "NewName", 0, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, describedAs("Update successful", is("Update successful")));
    }
    
    //test update - invalid numberic ID
    @Test
    public void updateDepartmentTest_invalidNumericID()
    {
    	//controller command
    	controller.updateDepartment(mockDept, NOT_VALID_ID, "NewName", 0, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("An error has occured while attempting to process the requested record, please try again."
    					, is("An error has occured while attempting to process the requested record, please try again.")));
    }
    
    //test update - non-existant numeric ID
    @Test
    public void updateDepartmentTest_nonexistantID()
    {
    	//controller command
    	controller.updateDepartment(mockDept, NOT_PRESENT_ID, "NewName", 0, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("An error has occured while saving your changes. Please try again."
    					, is("An error has occured while saving your changes. Please try again.")));
    }
    
    //test update - blank name
    @Test
    public void updateDepartmentTest_blankName()
    {
    	//controller command
    	controller.updateDepartment(mockDept, TestObject.DEPT_ID, "", 0, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("The department name being edited cannot be blank."
    					, is("The department name being edited cannot be blank.")));
    }
    
    //test add new department
    @Test
    public void addNewDepartmentTest()
    {
    	//controller command
    	controller.addNewDepartment(mockDept2, 0, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("New department, " + TestObject.DEPARTMENT_NAME + ", added successfully"
    					, is("New department, " + TestObject.DEPARTMENT_NAME + ", added successfully")));
    }
    
    //test delete department - valid id
	@Test
	public void deleteDepartmentTest_validID()
	{
		//controller command
    	controller.deleteDepartment(TestObject.DEPT_ID, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs(TestObject.DEPARTMENT_NAME + " has been successfully deleted."
    					, is(TestObject.DEPARTMENT_NAME + " has been successfully deleted.")));
	}
	
	//test delete department - invalid id
	@Test
	public void deleteDepartmentTest_invalidID()
	{
		//controller command
    	controller.deleteDepartment(NOT_VALID_ID, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("An error has occurred. Department was not deleted. Please try again."
    					, is("An error has occurred. Department was not deleted. Please try again.")));
	}
	
	//test delete department - nonexistent id
	@Test
	public void deleteDepartmentTest_nonexistentID()
	{
		//controller command
    	controller.deleteDepartment(NOT_PRESENT_ID, model);
    	
    	//returned values, expected: string msg
    	errorMsg = (String) (model.asMap().get("msg"));
    	
    	//verify returns
    	assertThat(errorMsg, describedAs("Not null", is(not(nullValue()))));
    	assertThat(errorMsg, describedAs("String object", is(instanceOf(String.class))));
    	assertThat(errorMsg, 
    			describedAs("An error has occurred. Department was not deleted. Please try again."
    					, is("An error has occurred. Department was not deleted. Please try again.")));
	}
}
