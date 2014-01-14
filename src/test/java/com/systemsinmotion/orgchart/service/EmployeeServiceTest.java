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
		List<Employee> empo = this.EmpoService.findAllEmployees();
		assertNotNull(empo);
		assertEquals(1, empo.size());
	}

	@Test
	public void findEmployeeByID() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(mockEmployee.getId(), empo.getId());
	}

	@Test
	public void findEmployeeByEmail() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(mockEmployee.getEmail(), empo.getEmail());
	}

	@Test
	public void findEmployeeByFirstName() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(mockEmployee.getFirstName(), empo.getFirstName());
	}

	@Test
	public void findEmployeeBySkypeName() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(mockEmployee.getSkypeName(), empo.getSkypeName());
	}

	@Test
	public void findEmployeeByLastName() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(mockEmployee.getLastName(), empo.getLastName());
	}

	@Test
	public void storeEmployee() {
		Employee empoId = this.EmpoService.storeEmployee(this.mockEmployee);
		assertNotNull(empoId);
		assertEquals(mockEmployee.getId(), empoId.getId());
	}

}
