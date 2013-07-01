package com.systemsinmotion.orgchart.dao;

import java.io.Serializable;
import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao 
{
		
	Integer create(Employee e);
	
	Employee read(Serializable id); 
	
	//public abstract void delete(Employee e);
		
	List<Employee> queryAll();
	
	List<Employee> queryByFirstNameLastName(String lastName, String firstName);
	
	List<Employee> queryByDepartment(Department dept);
	
	List<Employee> queryByJobTitle(String jobTitle);

	List<Employee> queryByMultipleCriteria(String lastName, String firstName, int dept_Id,  String jobTitle);
	

}
