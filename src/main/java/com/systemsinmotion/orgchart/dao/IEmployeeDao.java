package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IEmployeeDao {
	
	/**
	 * Deletes the employee instance from the database.
	 * 
	 * @param employee
	 * 			Employee instance to delete
	 */
	public abstract void delete(Employee employee);
	
	/**
	 * Returns all Employee instances as a list
	 * 
	 * @return List of Employees
	 */
	public abstract List<Employee> findAll();
	
	/**
	 * Returns an Employee instance having a given id value.
	 * 
	 * @param id
	 * 			Id of Employee instance to find
	 * @return Instance of Employee. Null if not found.
	 */
	public abstract Employee findById(Integer id);
	
	/**
	 * Returns an Employee instance having a given email value
	 * 
	 * @param email
	 * 			Email of Employee instance to find
	 * @return Instance of Employee. Null if not found.
	 */
	public abstract Employee findByEmail(String email);
	
	/**
	 * Returns a list of Employee instances belonging to a given Department
	 * 
	 * @param department
	 * 			Department to find Employee instances belonging to
	 * @return	List of Employees
	 */
	public abstract List<Employee> findByDepartment(Department department);
	
	/**
	 * Returns a list of Employee instances which have a given JobTitle
	 * 
	 * @param jobTitle
	 * 			JobTitle belonging to Employees to find
	 * @return List of Employees
	 */
	public abstract List<Employee> findByJobTitle(JobTitle jobTitle);
	
	/**
	 * Saves a given Employee instance and returns its generated Id
	 * 
	 * @param employee
	 * 			The Employee instance to be saved
	 * @return Generated Id of given Employee instance
	 */
	public abstract Integer save(Employee employee);
	
	/**
	 * Updates existing Employee instance with new values
	 * 
	 * @param employee
	 * 			The Employee instance to be updated
	 */
	public abstract void update(Employee employee);
}
