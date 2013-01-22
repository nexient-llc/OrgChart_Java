package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	private ArrayList<Employee> listOfFoundEmps = new ArrayList<Employee>();
	
	EmployeeDAO mockEmpDAO = mock(EmployeeDAO.class);
	Employee mockEmp = mock(Employee.class);
	
	Department mockDept = mock(Department.class);
	JobTitle mockJT = mock(JobTitle.class);
	
	@Before
	public void before() throws Exception 
	{
		
		when(mockEmp.getEmpID()).thenReturn(TestObject.EMPLOYEE_ID);
		listOfFoundEmps.add(mockEmp);
		when(mockEmpDAO.findAll()).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByFirstName(TestObject.FIRST_NAME)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByLastName(TestObject.LAST_NAME)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByEmail(TestObject.EMAIL)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByDepartment(mockDept)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByJobTitle(mockJT)).thenReturn(listOfFoundEmps);
		when(mockEmpDAO.findByManager(mockEmp)).thenReturn(listOfFoundEmps);
		
	}
	
	@Test
	public void should
	

}
