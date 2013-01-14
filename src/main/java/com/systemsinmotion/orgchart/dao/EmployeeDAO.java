package com.systemsinmotion.orgchart.dao;

import java.util.List;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface EmployeeDAO {
	
	//insert a new employee into the database and return the new employee ID
	public abstract Integer save(Employee employee);
	
	//update an existing employee record
	public abstract void update(Employee employee);
	
	//delete an employee record
	public abstract void delete(Employee employee);
	
	//find all employees
	public abstract List<Employee> findAll();
	
	//find an employee by employeeID
	public abstract Employee findByEmployeeID(Integer ID);
	
	//find by employee(s) by first or last name
	public abstract List<Employee> findByName(String firstName, String lastName);
	
	//find employee(s) by email address
	public abstract List<Employee> findByEmail(String email);
	
	//find employees by department
	public abstract List<Employee> findByDepartment(Department department);
	
	//find employees by job title
	public abstract List<Employee> findByJobTitle(JobTitle jobTitle);
	
	//find employees by manager
	public List<Employee> findByManager(Employee employee);
	
}
