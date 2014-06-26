package com.systemsinmotion.orgchart.config;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.data.MockDepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.service"})
public class TestServiceConfig {
	private List<Department> listFoundDepartment;
	private Department mockDepartment;

	// added
	private List<Employee> listFoundEmployee;
	private Employee mockEmployee; 
	
	private List<JobTitle> listFoundJobTitle;
	private JobTitle mockJobTitle; 
	
	@PostConstruct
	private void init(){
		listFoundDepartment=new ArrayList<Department>();
		mockDepartment=Entities.department(Entities.DEPT_ID);
		listFoundDepartment.add(mockDepartment);
		
	//added
		listFoundEmployee=new ArrayList<Employee>();
		mockEmployee=Entities.employee(Entities.EMPLOYEE_ID);
		listFoundEmployee.add(mockEmployee);
		
		listFoundJobTitle=new ArrayList<JobTitle>();
		mockJobTitle=Entities.jobTitle(Entities.JOB_TITLE_ID);
		listFoundJobTitle.add(mockJobTitle);
	}
	
	@Bean
	Department getDepartment(){
		return this.mockDepartment;
	}
		
	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listFoundDepartment);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		return repo;	
	}
	
	@Bean
	Employee getEmployee(){
		return this.mockEmployee;
	}
	
	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);
		when(repo.findAll()).thenReturn(this.listFoundEmployee);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(repo.save(this.mockEmployee)).thenReturn(this.mockEmployee);
		return repo;	
	}
	
	@Bean
	JobTitle getJobTitle(){
		return this.mockJobTitle;
	}
	
	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listFoundJobTitle);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockJobTitle);
		when(repo.save(this.mockJobTitle)).thenReturn(this.mockJobTitle);
		return repo;	
	}
	
}


