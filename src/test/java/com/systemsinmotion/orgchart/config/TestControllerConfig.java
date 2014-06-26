package com.systemsinmotion.orgchart.config;

import static org.mockito.Mockito.mock; // framework to create mock object with expected behavior and verify its interactions.
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

@Configuration // class can be used as a source of bean def by spring container.
@ComponentScan({"com.systemsinmotion.orgchart.web.controller"}) // to enable spring auto component scanning,Spring detects,register & instantiate beans & components. 

public class TestControllerConfig {

	 private List<Department> listOFFoundDepartments;
	 private Department mockDepartment;

	 private List<JobTitle> listFoundJobTitles;
	 private JobTitle mockJobTitle;

	 private List<Employee> listFoundEmployees;
	 private Employee mockEmployee;

	 @PostConstruct // initialising and destruction of bean.Will call init() after all injections and initializations have been called.
	 private void init(){
	
		  listOFFoundDepartments = new ArrayList<Department>();
		  mockDepartment = Entities.department(Entities.DEPT_ID);
		  listOFFoundDepartments.add(mockDepartment);
		  mockDepartment = Entities.department(Entities.DEPT_ID);
		
		  listFoundJobTitles = new ArrayList<JobTitle>();
		  mockJobTitle= Entities.jobTitle(Entities.JOB_TITLE_ID);
		  listFoundJobTitles.add(mockJobTitle);
		
		  listFoundEmployees = new ArrayList<Employee>();
		  mockEmployee= Entities.employee(Entities.EMPLOYEE_ID);
		  listFoundEmployees.add(mockEmployee);
		  mockEmployee= Entities.employee(Entities.EMPLOYEE_ID);	  
	 }

	 @Bean // tells spring, getDepartment will return obj which should be registered as a bean in Spring app context.
	 Department  getDepartment(){
		 return this.mockDepartment;
	 }

	 //Added to make a mockdep repository
	 @Bean
	 DepartmentRepository getDepartmentRepository(){
		  DepartmentRepository repo = mock(DepartmentRepository.class);
		  when(repo.findAll()).thenReturn (this.listOFFoundDepartments);
		  when(repo.findOne(Entities.DEPT_ID)).thenReturn (this.mockDepartment);
		  when(repo.save(this.mockDepartment)).thenReturn (this.mockDepartment);
		  return repo;
	 }

	 // Mock Job Repository	
	 @Bean
	 JobTitle getJobtitle(){
		 return this.mockJobTitle;
	 }

	 @Bean
	 JobTitleRepository getJobTitleRepository(){
		  JobTitleRepository repo = mock(JobTitleRepository.class);
		  when(repo.findAll()).thenReturn (this.listFoundJobTitles);
		  when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn (this.mockJobTitle);
		  when(repo.save(this.mockJobTitle)).thenReturn (this.mockJobTitle);
		  return repo;
	 }

	 // Mock employee repository
	 @Bean
	 Employee getEmployee(){
		 return this.mockEmployee;
	 }

	 @Bean
	 EmployeeRepository getEmployeeRepository(){
		  EmployeeRepository repo = mock(EmployeeRepository.class);
		  when(repo.findAll()).thenReturn (this.listFoundEmployees);
		  when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn (this.mockEmployee);
		  when(repo.save(this.mockEmployee)).thenReturn (this.mockEmployee);
		  return repo;
	 }

	 //Creating mock Department service
	 @Bean
	 DepartmentService getDepartmentService() {

		  DepartmentService ser = mock(DepartmentService.class);
		  when(ser.findAllDepartments()).thenReturn (this.listOFFoundDepartments);
		  when(ser.findDepartmentByID(Entities.DEPT_ID)).thenReturn (this.mockDepartment);
		  when(ser.storeDepartment(this.mockDepartment)).thenAnswer (new Answer<Department>(){
	   
			  public Department answer(InvocationOnMock invocation) throws Throwable {
				  listOFFoundDepartments.add(mockDepartment); 
				  return mockDepartment;
			  }
		  });
  
		  return ser;
	 }

	 @Bean
	 JobTitleService getJobTitleService(){
		  JobTitleService ser = mock(JobTitleService.class);
		  when(ser.findAllJobTitles()).thenReturn (this.listFoundJobTitles);
		  when(ser.findJobTitleByID(Entities.JOB_TITLE_ID)).thenReturn (this.mockJobTitle);
		  when(ser.storeJobTitle(this.mockJobTitle)).thenReturn (this.mockJobTitle);
		  when(ser.storeJobTitle(this.mockJobTitle)).thenAnswer (new Answer<JobTitle>(){
			  public JobTitle answer(InvocationOnMock invocation) throws Throwable{
				  listFoundJobTitles.add(mockJobTitle); 
				  return mockJobTitle;	
			  }
		  });
  
		  return ser;
	 }

	 @Bean
	 EmployeeService getEmployeeService() {
		  EmployeeService ser = mock(EmployeeService.class);
		  when(ser.findAllEmployees()).thenReturn (this.listFoundEmployees);
		  when(ser.findEmployeeByID(Entities.EMPLOYEE_ID)).thenReturn (this.mockEmployee);
		  when(ser.storeEmployee(this.mockEmployee)).thenReturn (this.mockEmployee);
		  when(ser.storeEmployee(this.mockEmployee)).thenAnswer (new Answer<Employee>() {
			   public Employee answer(InvocationOnMock invocation) throws Throwable{
				    listFoundEmployees.add(mockEmployee); 
				    return mockEmployee;			
			   }
		  });
	  
		  return ser;
	
	 }
}