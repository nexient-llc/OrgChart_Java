package com.systemsinmotion.orgchart.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.web.controller"})
public class TestControllerConfig {

	private List<Department> listOfFoundDepts;
	private List<JobTitle> listOfFoundTitles;
	private List<Employee> listOfFoundEmployees;

	private Department mockDepartment;
	private JobTitle mockTitle;
	private Employee mockEmployee;
	
	@PostConstruct
	private void init() {
		listOfFoundDepts = new ArrayList<Department>();
		mockDepartment = Entities.department(Entities.DEPT_ID);
		listOfFoundDepts.add(mockDepartment);
//		mockDepartment = Entities.department(Entities.DEPT_ID);

		listOfFoundTitles = new ArrayList<JobTitle>();
		mockTitle = Entities.jobTitle(Entities.JOB_TITLE_ID);
		listOfFoundTitles.add(mockTitle);
//		mockTitle = Entities.jobTitle(Entities.JOB_TITLE_ID);
		
		listOfFoundEmployees = new ArrayList<Employee>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		listOfFoundEmployees.add(mockEmployee);
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
	}
	
	@Bean
	DepartmentService getDepartmentService() {
		DepartmentService service = mock(DepartmentService.class);
		when(service.findAllDepartments()).thenReturn(listOfFoundDepts);
		when(service.storeDepartment(mockDepartment)).thenAnswer(new Answer<Department>() {
		     public Department answer(InvocationOnMock invocation) {
		         listOfFoundDepts.add(mockDepartment);
		         return mockDepartment;
		     }
		 });
		return service;
	}

	@Bean
	EmployeeService getEmployeeService() {
		EmployeeService service = mock(EmployeeService.class);
		when(service.findAllEmployees()).thenReturn(listOfFoundEmployees);
		when(service.storeEmployee(mockEmployee)).thenAnswer(new Answer<Employee>() {
		     public Employee answer(InvocationOnMock invocation) {
		         listOfFoundEmployees.add(mockEmployee);
		         return mockEmployee;
		     }
		 });
		return service;
	}
	
	@Bean
	JobTitleService getJobTitleService() {
		JobTitleService service = mock(JobTitleService.class);
		when(service.findAllJobTitles()).thenReturn(listOfFoundTitles);
		when(service.storeJobTitle(mockTitle)).thenAnswer(new Answer<JobTitle>() {
		     public JobTitle answer(InvocationOnMock invocation) {
		         listOfFoundTitles.add(mockTitle);
		         return mockTitle;
		     }
		 });
		return service;
	}
	
	@Bean
	Department getDepartment() {
		return this.mockDepartment;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		return repo;
	}

	@Bean
	JobTitle getJobTitle(){
		return this.mockTitle;
	}

	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(mockTitle);
		when(repo.save(this.mockTitle)).thenReturn(this.mockTitle);
		return repo;
	}
	
	@Bean
	Employee getEmployee(){
		return this.mockEmployee;
	}
	
	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(repo.save(this.mockEmployee)).thenReturn(mockEmployee);
		return repo;
	}
	
}
