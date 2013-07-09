package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;


@Service("employeeService")
public class EmployeeService 
{
	
	@Autowired EmployeeDao employeeDao;

	public Employee findEmployeeById(Integer Id)	
	{				
		return employeeDao.queryById(Id);
	}
		
	public void setEmployeeDao(EmployeeDao employeeDao)
	{
		this.employeeDao=employeeDao;  
		
	}
		
	public List<Employee> findAll()
	{
		
		return this.employeeDao.queryAll();
	}
		
	public Integer storeEmployee(Employee employee)

	{		
		return this.employeeDao.create(employee); 
	}
	

	public void removeEmployee (Employee employee) 
	{
		this.employeeDao.delete(employee);
	}
	
}


