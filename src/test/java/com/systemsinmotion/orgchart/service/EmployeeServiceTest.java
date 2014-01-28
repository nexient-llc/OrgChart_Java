package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/test-context.xml")
@ContextConfiguration(classes = JPAConfig.class)
@WebAppConfiguration("/src/main/webapp")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	EmployeeRepository mockEmployeeRepo = mock(EmployeeRepository.class);
	Employee mockEmployee = mock(Employee.class);

	private ArrayList<Employee> listOfFoundDepts = new ArrayList<Employee>();

	@Test
	public void bs() {
		assertTrue(true);
	}
	
	@Before
	public void before() throws Exception {
		when(this.mockEmployee.getId()).thenReturn(Entities.EMPLOYEE_ID);
		this.listOfFoundDepts.add(this.mockEmployee);
		when(this.mockEmployeeRepo.findAll()).thenReturn(this.listOfFoundDepts);
		when(this.mockEmployeeRepo.findById(Entities.EMPLOYEE_ID)).thenReturn(this.mockEmployee);
		when(this.mockEmployeeRepo.save(this.mockEmployee)).thenReturn(Entities.employee());
		this.employeeService.setRepository(this.mockEmployeeRepo);
	}

	@Test
    public void findAllDepartments() {
        List<Employee> employees = this.employeeService.findAllEmployees();
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

	@Test
	public void findEmployeeByID() {
		Employee employee = this.employeeService.findById(Entities.EMPLOYEE_ID);
		assertNotNull(employee);
		assertEquals(Entities.EMPLOYEE_ID, employee.getId());
	}

    @Test
    public void storeEmployee() {
        Employee employee = this.employeeService.storeEmployee(this.mockEmployee);
        assertNotNull(employee);
        assertEquals(Entities.employee().getId(), employee.getId());
    }



}
