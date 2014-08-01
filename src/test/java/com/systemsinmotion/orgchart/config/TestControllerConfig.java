package com.systemsinmotion.orgchart.config;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.data.SimpleEmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.service.EmployeeService;
import com.systemsinmotion.orgchart.service.JobTitleService;

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.web.controller"})
public class TestControllerConfig {

	private List<Department> listOfFoundDepts;
	private List<JobTitle> listOfFoundTitles;
	private List<Employee> listOfFoundEmployees;
	private List<SimpleEmployee> listOfFoundSimpleEmployees;
	private List<SimpleEmployee> listOfFoundSimpleEmployees2;
	private Page<Employee> pageOfFoundEmployees;

	private Department mockDepartment;
	private Department mockDepartment2;
	private JobTitle mockTitle;
	private Employee mockEmployee;
	private SimpleEmployee mockSimpleEmployee;
	private SimpleEmployee mockSimpleEmployee2;

	
	@PostConstruct
	private void init() {
		listOfFoundDepts = new ArrayList<Department>();
		mockDepartment = Entities.department(Entities.DEPT_ID);
		listOfFoundDepts.add(mockDepartment);

		mockDepartment2 = Entities.department(Entities.PARENT_DEPT_ID);

		listOfFoundTitles = new ArrayList<JobTitle>();
		mockTitle = Entities.jobTitle(Entities.JOB_TITLE_ID);
		listOfFoundTitles.add(mockTitle);
		
		listOfFoundEmployees = new ArrayList<Employee>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		listOfFoundEmployees.add(mockEmployee);
//		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		pageOfFoundEmployees = new PageImpl<Employee>(listOfFoundEmployees);
		
		listOfFoundSimpleEmployees = new ArrayList<SimpleEmployee>();
		mockSimpleEmployee = Entities.simpleEmployee(Entities.SIMPLE_EMPLOYEE_ID);
		listOfFoundSimpleEmployees.add(mockSimpleEmployee);

		listOfFoundSimpleEmployees2 = new ArrayList<SimpleEmployee>();
		mockSimpleEmployee2 = Entities.simpleEmployee2(Entities.SIMPLE_EMPLOYEE_ID_2); 
		listOfFoundSimpleEmployees2.add(mockSimpleEmployee);
		listOfFoundSimpleEmployees2.add(mockSimpleEmployee2);
	}
	
	@Bean
	DepartmentService getDepartmentService() {
		DepartmentService service = mock(DepartmentService.class);
		when(service.findAllActiveDepartments()).thenReturn(listOfFoundDepts);
		when(service.findAllActiveDepartments(any(PageRequest.class))).thenReturn(new PageImpl<Department>(listOfFoundDepts));
		when(service.storeDepartment(eq(mockDepartment2))).thenReturn(mockDepartment2).thenThrow(new DataIntegrityViolationException("Can't save the same thing twice"));
		when(service.storeDepartment(mockDepartment)).thenAnswer(new Answer<Department>() {
		     public Department answer(InvocationOnMock invocation) {
		         listOfFoundDepts.add(mockDepartment);
		         return mockDepartment;
		     }
		 });
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				listOfFoundDepts.clear();
				return null;
			}
		}).doNothing().when(service).removeDepartmentById(Entities.DEPT_ID);
		return service;
	}

	@Bean
	EmployeeService getEmployeeService() {
		EmployeeService service = mock(EmployeeService.class);
		when(service.findAllActiveEmployees()).thenReturn(listOfFoundEmployees);
		when(service.findEmployeesByNameOnlyFilter(eq(Entities.FIRST_NAME), eq(Entities.LAST_NAME))).thenReturn(listOfFoundSimpleEmployees);
		when(service.findEmployeesByNameOnlyFilter(eq(Entities.FIRST_NAME), isNull(String.class))).thenReturn(listOfFoundSimpleEmployees2);
		when(service.findEmployeesByNameOnlyFilter(eq(Entities.LAST_NAME), isNull(String.class))).thenReturn(listOfFoundSimpleEmployees2);
		when(service.findEmployeesByNameOnlyFilter(isNull(String.class), isNull(String.class))).thenReturn(listOfFoundSimpleEmployees2);
		when(service.findEmployeesByCriteriaFilter(anyString(), anyString(), anyInt(), anyInt(), any(Pageable.class))).thenReturn(pageOfFoundEmployees);
		when(service.findAllActiveEmployees(any(PageRequest.class))).thenReturn(new PageImpl<Employee>(listOfFoundEmployees));
		when(service.storeEmployee(mockEmployee)).thenAnswer(new Answer<Employee>() {
		     public Employee answer(InvocationOnMock invocation) {
		         listOfFoundEmployees.add(mockEmployee);
		         return mockEmployee;
		     }
		 });
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				listOfFoundEmployees.clear();
				return null;
			}
		}).doNothing().when(service).removeEmployeeById(Entities.EMPLOYEE_ID);
		return service;
	}
	
	@Bean
	JobTitleService getJobTitleService() {
		JobTitleService service = mock(JobTitleService.class);
		when(service.findAllActiveJobTitles()).thenReturn(listOfFoundTitles);
		when(service.findAllActiveJobTitles(any(PageRequest.class))).thenReturn(new PageImpl<JobTitle>(listOfFoundTitles));
		when(service.storeJobTitle(mockTitle)).thenAnswer(new Answer<JobTitle>() {
		     public JobTitle answer(InvocationOnMock invocation) {
		         listOfFoundTitles.add(mockTitle);
		         return mockTitle;
		     }
		 });
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				listOfFoundTitles.clear();
				return null;
			}
		}).doNothing().when(service).removeJobTitleById(Entities.JOB_TITLE_ID);
		return service;
	}
	
	@Bean(name="mockDepartment")
	Department getDepartment() {
		return this.mockDepartment;
	}
	
	@Bean(name="mockDepartmentList")
	List<Department> getDepartmentList() {
		return this.listOfFoundDepts;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.findById(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.findByIsActiveIsTrue(any(PageRequest.class))).thenReturn(new PageImpl<Department>(listOfFoundDepts));
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
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(mockTitle);
		when(repo.findById(Entities.JOB_TITLE_ID)).thenReturn(this.mockTitle);
		when(repo.findByIsActiveIsTrue(any(PageRequest.class))).thenReturn(new PageImpl<JobTitle>(listOfFoundTitles));
		when(repo.save(this.mockTitle)).thenReturn(this.mockTitle);
		return repo;
	}

	
	@Bean
	Employee getEmployee(){
		return this.mockEmployee;
	}
	
	@Bean(name="mockEmployeeList")
	List<Employee> getEmployeeList() {
		return this.listOfFoundEmployees;
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
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByDepartmentIdAndIsActiveIsTrue(Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByJobTitleIdAndIsActiveIsTrue(Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentIdAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentIdAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.FIRST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByUpperCaseNameAndDepartmentAndJobTitleAndActive(Entities.LAST_NAME.toUpperCase(), Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentIdAndJobTitleIdAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME, Entities.DEPT_ID, Entities.JOB_TITLE_ID)).thenReturn(this.listOfFoundEmployees);
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
		when(repo.findByIsActiveIsTrue(any(PageRequest.class))).thenReturn(new PageImpl<Employee>(listOfFoundEmployees));
		when(repo.save(this.mockEmployee)).thenReturn(mockEmployee);
		return repo;
	}


	@Bean
	SimpleEmployee getSimpleEmployee(){
		return this.mockSimpleEmployee;
	}

	@Bean
	SimpleEmployeeRepository getSimpleEmployeeRepository() {
		SimpleEmployeeRepository repo = mock(SimpleEmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundSimpleEmployees);
		when(repo.findOne(Entities.SIMPLE_EMPLOYEE_ID)).thenReturn(mockSimpleEmployee);
		when(repo.findById(Entities.SIMPLE_EMPLOYEE_ID)).thenReturn(this.mockSimpleEmployee);
		when(repo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME)).thenReturn(this.listOfFoundSimpleEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.FIRST_NAME)).thenReturn(this.listOfFoundSimpleEmployees);
		when(repo.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.LAST_NAME, Entities.LAST_NAME)).thenReturn(this.listOfFoundSimpleEmployees);
		when(repo.save(this.mockSimpleEmployee)).thenReturn(mockSimpleEmployee);
		return repo;
	}

}
