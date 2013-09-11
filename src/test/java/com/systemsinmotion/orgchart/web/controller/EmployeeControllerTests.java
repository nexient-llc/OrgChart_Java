package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeControllerTests {
	
	@Autowired
	EmployeeController controller;
	
	EmployeeService mockEmployeeService = mock(EmployeeService.class);
	DepartmentService mockDepartmentService = mock(DepartmentService.class);
	JobTitleService mockJobTitleService = mock(JobTitleService.class);
	Employee mockEmployee = mock(Employee.class);
	JobTitle mockJobTitle = mock(JobTitle.class);
	Department mockDepartment = mock(Department.class);
	
	Model model;
	List<Employee> employees;
	List<Department> departments;
	List<JobTitle> jobTitles;
	
	@Before
	public void before() {
		model = new ExtendedModelMap();
		employees = new ArrayList<Employee>();
		departments = new ArrayList<Department>();
		jobTitles = new ArrayList<JobTitle>();
		
		when(mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		when(mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		when(mockJobTitle.getId()).thenReturn(Entities.JOB_TITLE_ID);
		
		employees.add(mockEmployee);
		departments.add(mockDepartment);
		jobTitles.add(mockJobTitle);
		
		when(mockEmployeeService.findAllEmployees()).thenReturn(employees);
		when(mockDepartmentService.findAllActiveDepartments()).thenReturn(departments);
		when(mockJobTitleService.findAllJobTitles()).thenReturn(jobTitles);
		
		controller.setDepartmentService(mockDepartmentService);
		controller.setEmployeeService(mockEmployeeService);
		controller.setJobTitleService(mockJobTitleService);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doEmployees_GET() {
		String returnvalue = controller.doEmployees_GET(model);
		ArrayList<Employee> emps = (ArrayList<Employee>) model.asMap().get("emps");
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		assertNotNull(returnvalue);
		assertNotNull(emps);
		assertNotNull(depts);
		assertNotNull(jobs);
		assertEquals(View.EMPLOYEES, returnvalue);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doEmployees_POST() {
		String returnvalue = controller.doEmployees_POST(mockEmployee, model);
		ArrayList<Employee> emps = (ArrayList<Employee>) model.asMap().get("emps");
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		Mockito.verify(mockEmployeeService).storeEmployee(mockEmployee);
		assertNotNull(returnvalue);
		assertNotNull(emps);
		assertNotNull(depts);
		assertNotNull(jobs);
		assertEquals(View.EMPLOYEES, returnvalue);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doEmployees_PUT() {
		String returnvalue = controller.doEmployees_PUT(mockEmployee, model);
		ArrayList<Employee> emps = (ArrayList<Employee>) model.asMap().get("emps");
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		ArrayList<JobTitle> jobs = (ArrayList<JobTitle>) model.asMap().get("jobs");
		Mockito.verify(mockEmployeeService).updateEmployee(mockEmployee);
		assertNotNull(returnvalue);
		assertNotNull(emps);
		assertNotNull(depts);
		assertNotNull(jobs);
		assertEquals(View.EMPLOYEES, returnvalue);
		assertEquals(Entities.EMPLOYEE_ID, emps.get(0).getId());
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
		assertEquals(Entities.JOB_TITLE_ID, jobs.get(0).getId());
	}
	
}
