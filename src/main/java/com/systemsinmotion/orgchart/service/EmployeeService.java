package com.systemsinmotion.orgchart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	//set the dao
	public void setEmployeeDAO(EmployeeDAO employeeDAO) 
	{
		this.employeeDAO = employeeDAO;
	}
	
	//Save the employee object
	public Integer storeEmployee(Employee employee)
	{
		return this.employeeDAO.save(employee);
	}
	
	//update an employee record
	
	
	//delete an employee record
	
	
	//find all employees
	
	
	//find a specific employee by id
	
	
	//find an employee by first or last name
	
	
	//find an employee by email
	
	
	//find all employees in a given department
	
	
	//find all employees with a given job title
	
	
	//find all employees for a given manager
	
}
