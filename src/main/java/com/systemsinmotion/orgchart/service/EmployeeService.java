package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Transactional
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
			
	public void updateEmployee(Employee employee)
	{
		this.employeeDao.update(employee);		
	}	

	public List<Employee> findAll()
	{		
		return this.employeeDao.queryAll();
	}
		
	public List<Employee> findbyCriteria(Employee employee)
	{		
		return this.employeeDao.queryByMultipleCriteria(employee);
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


