package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestServiceConfig;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	EmployeeRepository mockEmpRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundEmps = new ArrayList<Employee>();

	@Test
	public void bs() {
		assertTrue(true);
	}
	
//	@Before
//	public void before() throws Exception {
//		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
//		this.listOfFoundEmps.add(this.mockEmployee);
//		when(this.mockEmpRepo.findAll()).thenReturn(this.listOfFoundEmps);
//		when(this.mockEmpRepo.findOne(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
//		when(this.mockEmpRepo.save(this.mockEmployee)).thenReturn(this.mockEmployee);
//	}
//
//	@Test
//	public void findAllEmployees() {
//		List<Employee> emps = this.employeeService.findAllEmployees();
//		assertNotNull(emps);
//		assertTrue(emps.size()>0);
//	}
//
//	@Test
//	public void findEmployeeByID() {
//		Employee emp = this.employeeService.findEmployeeByID(Entities.EMPLOYEE_ID);
//		assertNotNull(emp);
//		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
//	}
//
//	@Test
//	public void storeEmployee() {
//		Employee emp = this.employeeService.storeEmployee(this.mockEmployee);
//		assertNotNull(emp);
//		assertEquals(Entities.EMPLOYEE_ID, emp.getId());
//	}

}
