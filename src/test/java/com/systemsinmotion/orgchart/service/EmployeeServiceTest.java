package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@Ignore
public class EmployeeServiceTest {

	@Autowired
	EmployeeService EmpoService;
	EmployeeRepository mockEmployeeRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundEmployees = new ArrayList<Employee>();

	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundEmployees.add(this.mockEmployee);
		when(this.mockEmployeeRepo.findAll()).thenReturn(
				this.listOfFoundEmployees);
		when(this.mockEmployeeRepo.findById(Entities.EMPLOYEE_ID)).thenReturn(
				this.mockEmployee);
		when(this.mockEmployeeRepo.save(this.mockEmployee)).thenReturn(
				mockEmployee);
		this.EmpoService.setEmployeeService(this.mockEmployeeRepo);
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
		assertEquals(Entities.EMPLOYEE_ID, empo.getId());
	}

	@Test
	public void findEmployeeByEmail() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(Entities.EMPLOYEE_ID, empo.getEmail());
	}

	@Test
	public void findEmployeeByFirstName() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(Entities.EMPLOYEE_ID, empo.getFirstName());
	}

	@Test
	public void findEmployeeByLastName() {
		Employee empo = this.EmpoService.findEmployeeByID(Entities.EMPLOYEE_ID);
		assertNotNull(empo);
		assertEquals(Entities.EMPLOYEE_ID, empo.getLastName());
	}

	@Test
	public void storeEmployee() {
		Employee empoId = this.EmpoService.storeEmployee(this.mockEmployee);
		assertNotNull(empoId);
		assertEquals(Entities.EMPLOYEE_ID, empoId);
	}

}
