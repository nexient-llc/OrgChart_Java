package com.systemsinmotion.orgchart.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.service"})
public class TestServiceConfig {

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

		listOfFoundTitles = new ArrayList<JobTitle>();
		mockTitle = Entities.jobTitle(Entities.JOB_TITLE_ID);
		listOfFoundTitles.add(mockTitle);
		
		listOfFoundEmployees = new ArrayList<Employee>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		listOfFoundEmployees.add(mockEmployee);
	}

	@Bean
	Department getDepartment() {
		return this.mockDepartment;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findAllByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
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
		when(repo.findAllByIsActiveIsTrue()).thenReturn(listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(repo.save(this.mockEmployee)).thenReturn(mockEmployee);
		when(repo.findByEmailIgnoreCase(Entities.EMAIL)).thenReturn(mockEmployee);
		when(repo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByDepartmentIdAndIsActiveIsTrue(Entities.DEPT_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByJobTitleIdAndIsActiveIsTrue(Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.DEPT_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		return repo;
	}

}
