package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeDAO {

	//insert new employee into the database, return the newly generated ID
	public abstract Integer save(Employee employee);
	
	//update existing record
	public abstract void update(Employee employee);
	
	//delete
	public abstract void delete(Employee employee);
	
	//find all
	public abstract List<Employee> findAll();
	
	//find by id
	public abstract Employee findByEmployeeID(Integer empID);
	
	//find by whole or partial first name
	public abstract List<Employee> findByFirstName(String fName);
	
	//find by whole or partial last name
	public abstract List<Employee> findByLastName(String lName);
	
	//find combination of first and last name
	public abstract List<Employee> findByNameCombination(String fName, String lName);
	
	//find by email
	public abstract List<Employee> findByEmail(String email);
	
	//find by department
	public abstract List<Employee> findByDepartment(Department department);
	
	//find by job title
	public abstract List<Employee> findByJobTitle(JobTitle jobTitle);
	
	//find by manager
	public abstract List<Employee> findByManager(Employee employee);
}
