package com.systemsinmotion.orgchart.config;

import static org.mockito.Mockito.doAnswer;
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
import com.systemsinmotion.orgchart.data.AuthoritiesRepository;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.data.SimpleEmployeeRepository;
import com.systemsinmotion.orgchart.data.UserRepository;
import com.systemsinmotion.orgchart.entity.Authorities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;
import com.systemsinmotion.orgchart.entity.User;

@Configuration
@ComponentScan({ "com.systemsinmotion.orgchart.service" })
public class TestServiceConfig {

	private List<Department> listOfFoundDepts;
	private List<JobTitle> listOfFoundTitles;
	private List<Employee> listOfFoundEmployees;
	private List<SimpleEmployee> listOfFoundSimpleEmployees;
	private List<Authorities> listOfFoundAuthorities;
	private List<User> listOfFoundUsers;
	// private List<Employee> listOfFoundEmployees2;
	// private List<Employee> listOfFoundEmployees3;
	// private List<SimpleEmployee> listOfFoundSimpleEmployees2;

	private Department mockDepartment;
	private Department mockParentDepartment;
	private JobTitle mockTitle;
	private Employee mockEmployee;
	private SimpleEmployee mockSimpleEmployee;
	private Authorities mockAuthority;
	private User mockUser;

	// private Employee mockEmployee2;
	// private Employee mockEmployee3;
	// private SimpleEmployee mockSimpleEmployee2;

	@PostConstruct
	private void init() {
		listOfFoundDepts = new ArrayList<Department>();
		mockDepartment = Entities.department(Entities.DEPT_ID);
		mockParentDepartment = Entities.department(Entities.PARENT_DEPT_ID);
		mockDepartment.setParentDepartment(mockParentDepartment);
		listOfFoundDepts.add(mockDepartment);

		listOfFoundTitles = new ArrayList<JobTitle>();
		mockTitle = Entities.jobTitle(Entities.JOB_TITLE_ID);
		listOfFoundTitles.add(mockTitle);

		listOfFoundEmployees = new ArrayList<Employee>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		listOfFoundEmployees.add(mockEmployee);

		listOfFoundSimpleEmployees = new ArrayList<SimpleEmployee>();
		mockSimpleEmployee = Entities.simpleEmployee(Entities.SIMPLE_EMPLOYEE_ID);
		listOfFoundSimpleEmployees.add(mockSimpleEmployee);

		listOfFoundUsers = new ArrayList<User>();
		mockUser = Entities.user();
		listOfFoundUsers.add(mockUser);

		listOfFoundAuthorities = new ArrayList<Authorities>();
		mockAuthority = Entities.authority(mockUser);
		listOfFoundAuthorities.add(mockAuthority);

		// listOfFoundEmployees2 = new ArrayList<Employee>();
		// mockEmployee2 = Entities.employee2(Entities.EMPLOYEE_ID_2);
		// listOfFoundEmployees2.add(mockEmployee2);
		//
		// listOfFoundEmployees3 = new ArrayList<Employee>();
		// mockEmployee3 = Entities.employee3(Entities.EMPLOYEE_ID_3);
		// listOfFoundEmployees3.add(mockEmployee3);
		//
		//
		// listOfFoundSimpleEmployees2 = new ArrayList<SimpleEmployee>();
		// mockSimpleEmployee2 = Entities.simpleEmployee2(Entities.SIMPLE_EMPLOYEE_ID_2);
		// listOfFoundSimpleEmployees2.add(mockSimpleEmployee2);
	}

	@Bean(name = "mockParentDepartment")
	Department getParentDepartment() {
		return this.mockParentDepartment;
	}

	@Bean(name = "mockDepartment")
	Department getDepartment() {
		return this.mockDepartment;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.findById(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.findByNameIgnoreCase(Entities.DEPARTMENT_NAME)).thenReturn(this.listOfFoundDepts);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		return repo;
	}

	@Bean(name = "mockTitle")
	JobTitle getJobTitle() {
		return this.mockTitle;
	}

	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundTitles);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(mockTitle);
		when(repo.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockTitle);
		when(repo.findByNameIgnoreCase(Entities.JOB_TITLE_NAME)).thenReturn(this.listOfFoundTitles);
		when(repo.save(this.mockTitle)).thenReturn(this.mockTitle);
		return repo;
	}

	@Bean(name = "mockEmployee")
	Employee getEmployee() {
		return this.mockEmployee;
	}

	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundEmployees);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(repo.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(repo.findByJobTitle(mockEmployee.getJobTitle())).thenReturn(this.listOfFoundEmployees);
		when(repo.findByDepartment(mockEmployee.getDepartment())).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCase(Entities.FIRST_NAME)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByLastNameContainingIgnoreCase(Entities.LAST_NAME)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME))
				.thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME))
				.thenReturn(this.listOfFoundEmployees);
		when(repo.findByDepartmentIdAndIsActiveIsTrue(Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByJobTitleIdAndIsActiveIsTrue(Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentIdAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentIdAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(
				repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(Entities.FIRST_NAME,
						Entities.LAST_NAME, Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(
				repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME,
						Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(
				this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(
				this.listOfFoundEmployees);
		when(
				repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME,
						Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, null, null, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, null, null, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, Entities.LAST_NAME, null, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, Entities.LAST_NAME, null, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, null, Entities.DEPT_ID, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, null, Entities.DEPT_ID, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, Entities.LAST_NAME, Entities.DEPT_ID, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, null)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, null, null, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, null, null, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, Entities.LAST_NAME, null, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, null, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(null, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findActiveByUnknownInputs(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(listOfFoundEmployees);
		when(repo.findByEmailIgnoreCase(Entities.EMAIL)).thenReturn(listOfFoundEmployees);
		when(repo.findBySkypeNameIgnoreCase(Entities.SKYPE_NAME)).thenReturn(listOfFoundEmployees);
		when(repo.save(this.mockEmployee)).thenReturn(mockEmployee);
		return repo;
	}

	@Bean(name = "mockSimpleEmployee")
	SimpleEmployee getSimpleEmployee() {
		return this.mockSimpleEmployee;
	}

	@Bean
	SimpleEmployeeRepository getSimpleEmployeeRepository() {
		SimpleEmployeeRepository repo = mock(SimpleEmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundSimpleEmployees);
		when(repo.findOne(Entities.SIMPLE_EMPLOYEE_ID)).thenReturn(mockSimpleEmployee);
		when(repo.findById(Entities.SIMPLE_EMPLOYEE_ID)).thenReturn(this.mockSimpleEmployee);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(
				this.listOfFoundSimpleEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME))
				.thenReturn(this.listOfFoundSimpleEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME))
				.thenReturn(this.listOfFoundSimpleEmployees);
		when(repo.save(this.mockSimpleEmployee)).thenReturn(mockSimpleEmployee);
		return repo;
	}

	@Bean(name = "mockUser")
	User getUser() {
		return this.mockUser;
	}
	
	@Bean
	UserRepository getUserRepository() {
		UserRepository repo = mock(UserRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundUsers);
		when(repo.findOne(Entities.USER_NAME)).thenReturn(mockUser);
		when(repo.findByEnabledIsTrue()).thenReturn(listOfFoundUsers);
		when(repo.findByUserNameIgnoreCase(Entities.USER_NAME)).thenReturn(mockUser);
		when(repo.save(mockUser)).thenReturn(mockUser);
		return repo;
	}

	@Bean(name = "mockAuthority")
	Authorities getAuthorities() {
		return this.mockAuthority;
	}
	
	@Bean(name = "mockAuthList")
	List<Authorities> getAuthoritiesList() {
		return this.listOfFoundAuthorities;
	}
	
	@Bean
	AuthoritiesRepository getAuthoritiesRepository() {
		AuthoritiesRepository repo = mock(AuthoritiesRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundAuthorities);
		when(repo.findOne(Entities.USER_NAME)).thenReturn(mockAuthority);
		when(repo.findByAuthority(Entities.AUTHORITY)).thenReturn(listOfFoundAuthorities);
		when(repo.findByUserNameIgnoreCase(Entities.USER_NAME)).thenReturn(mockAuthority);
		when(repo.save(mockAuthority)).thenReturn(mockAuthority);
		return repo;
	}
}
