package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

    JobTitleService mockJobTitleService = mock(JobTitleService.class);
    DepartmentService mockDepartmentService = mock(DepartmentService.class);
    EmployeeService mockEmployeeService = mock(EmployeeService.class);

    JobTitle mockJobTitle = mock(JobTitle.class);
    Employee mockEmployee = mock(Employee.class);
    Department mockDepartment = mock(Department.class);
    
    Department mockDepartment2;
    
//    Map model = new HashMap<String, Object>();
    Model model = new ExtendedModelMap();

    private ArrayList<JobTitle> findAllJobTitleList;
    private ArrayList<Employee> findAllEmployeesList;
    private ArrayList<Department> findAllDepartmentsList;
    
    @SuppressWarnings("unused")
    private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

    @Before
    public void before() {
	//instantiate lists
	findAllJobTitleList = new ArrayList<JobTitle>();
	findAllEmployeesList = new ArrayList<Employee>();
	findAllDepartmentsList = new ArrayList<Department>();
	
	mockDepartment2 = new Department(null, null, TestObject.DEPARTMENT_NAME, null, null);
	
	// set up mock JobTitle
	when(mockJobTitle.getJobTitleId()).thenReturn(TestObject.JOB_TITLE_ID);
	when(mockJobTitle.getDescription()).thenReturn(TestObject.JOB_TITLE);

	findAllJobTitleList.add(mockJobTitle);
	// set up mock Department
	when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
	when(mockDepartment.getName()).thenReturn(TestObject.DEPARTMENT_NAME);
	
	findAllDepartmentsList.add(mockDepartment);

	// set up mock Employee
	when(mockEmployee.getEmployeeId()).thenReturn(TestObject.EMPLOYEE_ID);

	findAllEmployeesList.add(mockEmployee);

	// set up mock JobTitleService
	when(mockJobTitleService.findAllJobTitles()).thenReturn(
		findAllJobTitleList);
	when(mockJobTitleService.findJobTitleByID(TestObject.JOB_TITLE_ID))
		.thenReturn(mockJobTitle);
	when(mockJobTitleService.storeJobTitle(mockJobTitle)).thenReturn(
		TestObject.JOB_TITLE_ID);

	controller.setJobTitleSErvice(mockJobTitleService);

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

	// set up mock EmployeeService
	when(mockEmployeeService.findAllEmployees()).thenReturn(
		findAllEmployeesList);
	when(mockEmployeeService.findEmployeeByID(TestObject.EMPLOYEE_ID))
		.thenReturn(mockEmployee);
	when(mockEmployeeService.storeEmployee(mockEmployee)).thenReturn(
		TestObject.EMPLOYEE_ID);

	controller.setEmployeeService(mockEmployeeService);

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
    
    @Test
    public void testModelShouldUpdateOnDepartmentPagePost() {
	
	model.addAttribute("depts", findAllDepartmentsList);
	//Given
	controller.doDepartments_POST(mockDepartment2, null, model);
	//When
	findAllDepartmentsList = (ArrayList<Department>)model.asMap().get("depts");
	
	//Then
	assertNotNull(findAllDepartmentsList);
	assertTrue(findAllDepartmentsList.size() > 0);
	assertEquals(TestObject.DEPT_ID, findAllDepartmentsList.get(1).getDepartmentId());
	assertEquals(findAllDepartmentsList.get(1).getName(), TestObject.DEPARTMENT_NAME);

    }
        


    
    @SuppressWarnings("unchecked")
    @Test
    public void testModelShouldContainNewEmployeeList(){
	//Given
	controller.doEmployees_GET(model);
	//When
	findAllEmployeesList = (ArrayList<Employee>)model.asMap().get("emps");
	//Then
	assertNotNull(findAllEmployeesList);
	assertEquals(TestObject.EMPLOYEE_ID, findAllEmployeesList.get(0).getEmployeeId());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testModelShouldContainNewEmployee(){
	
	model.addAttribute("emps", findAllEmployeesList);
	//Given
	controller.doEmployees_POST(mockEmployee, null, model);
	//When
	findAllEmployeesList = (ArrayList<Employee>)model.asMap().get("emps");
	//Then
	assertNotNull(findAllEmployeesList);
	assertEquals(TestObject.EMPLOYEE_ID, findAllEmployeesList.get(1).getEmployeeId());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testModelShouldContainNewJobTitleList(){
	//Given
	controller.doJobTitle_Get(model);
	//When
	findAllJobTitleList = (ArrayList<JobTitle>)model.asMap().get("jobs");	
	
	//Then
	assertNotNull(findAllJobTitleList);
	assertEquals(TestObject.JOB_TITLE_ID, findAllJobTitleList.get(0).getJobTitleId());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testModelShouldContainNewJobtitle(){
	model.addAttribute("jobs", findAllJobTitleList);
	//Given
	controller.doJobTitle_POST(mockJobTitle, null, model);
	//When
	findAllJobTitleList = (ArrayList<JobTitle>)model.asMap().get("jobs");
	//Then
	assertNotNull(findAllJobTitleList);
	assertEquals(TestObject.JOB_TITLE, findAllJobTitleList.get(1).getDescription());
	
    }   


}
