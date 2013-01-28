package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(EmployeeDAO employeeDAO) 
	{
		
		this.employeeDAO = employeeDAO;
		
	}
	
	public Integer createEmployeeRecord(Employee employee)
	{
		
		return this.employeeDAO.save(employee);
		
	}
	
	public void updateEmployeeRecord(Employee employee)
	{
		
		this.employeeDAO.update(employee);
		
	}
	
	public void deleteEmployeeRecord(Employee employee)
	{
		
		this.employeeDAO.delete(employee);
		
	}
	
	public List<Employee> findAllEmployees()
	{
		
		return this.employeeDAO.findAll();
		
	}
	
	public Employee findEmployeeByID(Integer ID)
	{
		
		return this.employeeDAO.findByEmployeeID(ID);
		
	}
	
	public List<Employee> findByEmployeeName(String fName, String lName)
	{
		
		List<Employee> emps = new ArrayList<Employee>();
		
		//call find by combination if both fields are populated
		if( (!fName.equals("") || !fName.equals(null)) && (!lName.equals("") || !lName.equals(null)) )
		{
			
			emps = this.employeeDAO.findByNameCombination(fName, lName);
			
		}
		
		//call find by first name if fName is populated, but lName is not
		else if( (!fName.equals("") || !fName.equals(null)) && (lName.equals("") || lName.equals(null)) )
		{
			
			emps = this.employeeDAO.findByFirstName(fName);
			
		}
		
		//call find by last name if fName is not populated, but lName is
		else if( (fName.equals("") || fName.equals(null)) && (!lName.equals("") || !lName.equals(null)) )
		{
			
			emps = this.employeeDAO.findByLastName(lName);
			
		}
		
		return emps;
		
	}
	
	public List<Employee> findEmployeeByEmail(String email)
	{
		
		return this.employeeDAO.findByEmail(email);
		
	}
	
	public List<Employee> findEmployeesByDepartment(Department department)
	{
		
		return this.employeeDAO.findByDepartment(department);
		
	}
	
	public List<Employee> findEmployeesByJobTitle(JobTitle jobTitle)
	{
		
		return this.employeeDAO.findByJobTitle(jobTitle);
		
	}
	
	public List<Employee> findEmployeesByManager(Employee employee)
	{
		
		return this.employeeDAO.findByManager(employee);
		
	}

}

