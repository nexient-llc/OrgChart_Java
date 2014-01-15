package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@WebAppConfiguration
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService EmpoService;
	@Mock
	EmployeeRepository mockEmployeeRepo;
	@Mock
	Employee mockEmployee;

	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();

	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeRepo.findAll()).thenReturn(
				this.listOfFoundEmployees);
		when(this.mockEmployeeRepo.findById(Entities.EMPLOYEE_ID)).thenReturn(
				this.mockEmployee);
		when(this.mockEmployeeRepo.save(this.mockEmployee)).thenReturn(
				mockEmployee);
	}

	@Test
	public void findAllEmployees() {
		List<Employee> emps = this.EmpoService.findAllEmployees();
		assertNotNull(emps);
		assertEquals(1, emps.size());
	}

	@Test
	public void findEmployeeByID() {
		Employee emps = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emps);
		assertEquals(mockEmployee.getId(), emps.getId());
	}

	@Test
	public void findEmployeeByEmail() {
		Employee emps = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emps);
		assertEquals(mockEmployee.getEmail(), emps.getEmail());
	}

	@Test
	public void findEmployeeByFirstName() {
		Employee emps = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emps);
		assertEquals(mockEmployee.getFirstName(), emps.getFirstName());
	}

	@Test
	public void findEmployeeBySkypeName() {
		Employee emps = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emps);
		assertEquals(mockEmployee.getSkypeName(), emps.getSkypeName());
	}

	@Test
	public void findEmployeeByLastName() {
		Employee emps = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(emps);
		assertEquals(mockEmployee.getLastName(), emps.getLastName());
	}

	@Test
	public void storeEmployee() {
		Employee empoId = this.EmpoService.storeEmployee(this.mockEmployee);
		assertNotNull(empoId);
		assertEquals(mockEmployee.getId(), empoId.getId());
	}

}
