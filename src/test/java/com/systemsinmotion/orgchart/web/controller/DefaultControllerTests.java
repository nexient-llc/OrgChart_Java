package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
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
import com.systemsinmotion.orgchart.service.DepartmentServiceTest;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

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
    	
    	private ArrayList<JobTitle> findAllJobTitleList = new ArrayList<JobTitle>();
    	private ArrayList<Employee> findAllEmployeesList = new ArrayList<Employee>();
    	private ArrayList<Department> findAllDepartmentsList = new ArrayList<Department>();
    	
	@Before
	public void before() {
	 // set up mock JobTitle
	when(mockJobTitle.getJobTitleId()).thenReturn(TestObject.JOB_TITLE_ID);
	when(mockJobTitle.getDescription()).thenReturn(TestObject.JOB_TITLE);
	
	findAllJobTitleList.add(mockJobTitle);
	//set up mock Department
	when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
	
	findAllDepartmentsList.add(mockDepartment);
	
	//set up mock Employee
	 when(mockEmployee.getEmployeeId()).thenReturn(TestObject.EMPLOYEE_ID);
	 
	 findAllEmployeesList.add(mockEmployee);
	 
	 //set up mock JobTitleService
	 when(mockJobTitleService.findAllJobTitles()).thenReturn(findAllJobTitleList);
	 when(mockJobTitleService.findJobTitleByID(TestObject.JOB_TITLE_ID)).thenReturn(mockJobTitle);
	 when(mockJobTitleService.storeJobTitle(mockJobTitle)).thenReturn(TestObject.JOB_TITLE_ID);
	 
	 //set up mock DepartmentService
	 when(mockDepartmentService.findAllDepartments()).thenReturn(findAllDepartmentsList);
	 when(mockDepartmentService.findDepartmentByID(TestObject.DEPT_ID)).thenReturn(mockDepartment);
	 when(mockDepartmentService.storeDepartment(mockDepartment)).thenReturn(TestObject.DEPT_ID);
	 
	
	}

	@Test
	public void doGet() {
		
	}

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

	@Test
	public void doDepartments_GET() {

	}
	
	@Test
	public void doDepartments_POST(){
	    
	}
	
	@Test
	public void doEmployees_GET(){
	    
	}
	
	@Test
	public void doEmployees_POST(){
	    
	}
	
	@Test
	public void doJobTitles_GET(){
	    
	}
	
	@Test
	public void doJobTitles_POST(){
	}

}
