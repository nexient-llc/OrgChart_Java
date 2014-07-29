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
import com.systemsinmotion.orgchart.data.AuthorityRepository;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.data.UsersRepository;
import com.systemsinmotion.orgchart.entity.Authorities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.entity.Users;

@Configuration
@ComponentScan({ "com.systemsinmotion.orgchart.service" })
public class TestServiceConfig {

	private List<Department> listOfFoundDepts;
	private List<JobTitle> listOfFoundTitles;
	private List<Employee> listOfFoundEmployees;
	private List<Users> listOfFoundUsers;
	private List<Authorities> listOfFoundAuthorities;

	private Department mockDepartment;
	private Users mockUser;
	private JobTitle mockTitle;
	private Employee mockEmployee;
	private Authorities mockAuthority;

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

		listOfFoundUsers = new ArrayList<Users>();
		mockUser = Entities.user();
		listOfFoundUsers.add(mockUser);

		listOfFoundAuthorities = new ArrayList<Authorities>();
		mockAuthority = Entities.authority();
		listOfFoundAuthorities.add(mockAuthority);

	}

	@Bean
	Authorities getAuthorities() {
		return this.mockAuthority;
	}

	@Bean
	Department getDepartment() {
		return this.mockDepartment;
	}

	@Bean
	Users getUsers() {
		return this.mockUser;
	}

	@Bean
	AuthorityRepository getAuthorityRepository() {
		AuthorityRepository repo = mock(AuthorityRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundAuthorities);
		when(repo.findByUserName(Entities.USERNAME)).thenReturn(
				this.mockAuthority);
		when(repo.findByAuthority(Entities.AUTHORITY)).thenReturn(
				this.listOfFoundAuthorities);
		when(repo.save(this.mockAuthority)).thenReturn(this.mockAuthority);
		when(repo.findOne(Entities.USERNAME)).thenReturn(this.mockAuthority);
		return repo;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
		when(repo.saveAndFlush(getDepartment()))
				.thenReturn(this.mockDepartment);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		return repo;
	}

	@Bean
	JobTitle getJobTitle() {
		return this.mockTitle;
	}

	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundTitles);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(mockTitle);
		when(repo.save(this.mockTitle)).thenReturn(this.mockTitle);
		return repo;
	}

	@Bean
	UsersRepository getUsersRepository() {
		UsersRepository repo = mock(UsersRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundUsers);
		when(repo.findByEnabledIsTrue()).thenReturn(this.listOfFoundUsers);
		when(repo.findOne(Entities.USERNAME)).thenReturn(this.mockUser);
		when(repo.findByUserName(Entities.USERNAME)).thenReturn(this.mockUser);
		when(repo.findByUserPassword(Entities.USERPASSWORD)).thenReturn(
				this.mockUser);
		when(repo.save(this.mockUser)).thenReturn(this.mockUser);
		return repo;
	}

	@Bean
	Employee getEmployee() {
		return this.mockEmployee;
	}

	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);

		when(repo.findAll()).thenReturn(this.listOfFoundEmployees);
		when(repo.findAll(Entities.PREDICATE)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findAll(Entities.PREDICATEFIRSTNAMEORLAST)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findAll(Entities.PREDICATELAST)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findAll(Entities.PREDICATEDEPART)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findAll(Entities.PREDICATETITLEID)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundEmployees);
		when(repo.findByJobTitleName(Entities.JOB_TITLE_NAME)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(repo.save(this.mockEmployee)).thenReturn(this.mockEmployee);

		return repo;
	}

}
