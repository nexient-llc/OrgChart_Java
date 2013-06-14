package com.systemsinmotion.orgchart.dao;

import java.util.ArrayList;
import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao {

	/**
	 * Deletes the <code>Employee</code> instance from the database.
	 * 
	 * @param employee
	 *            Employee instance to delete
	 */
	public abstract void delete(Employee employee);
	
	/**
	 * Saves a give <code>Department</code instance and returns its generated
	 * id.
	 * 
	 * @param department
	 *            The <code>Department</code> instance to be saved
	 * @return The identifier (id) of the new <code>Department</code> instance.
	 */
	public abstract Integer save(Employee employee);

	
	/**
	 * Returns all <code>Employee</code> instances as a <code>List</code>
	 * 
	 * @return List of Employees
	 */
	public abstract List<Employee> findAll();

	/**
	 * Returns a <code>Employee</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>Employee</code> instance to find
	 * @return Single <code>Employee</code> instance. Null if not found.
	 */
	public abstract Employee findById(Integer id);

	/**
	 * Returns a <code>Employee</code> instance with a given <code>name</code>
	 * value.
	 * 
	 * @param name
	 *            Name of the <code>Employee</code> to find
	 * @return Instance of <code>Employee</code>. Null if not found.
	 */
	public abstract Employee findByName(String name);

	/**
	 * Returns an <code>Employee</code> List with a given <code>department</code>
	 * value.
	 * 
	 * @param department
	 *            Name of the <code>Department</code> to find
	 * @return <code>Employee</code> List. Null if not found.
	 */
	public abstract List<Employee> findByDepartment(Department department);

	/**
	 * Returns an <code>Employee</code> List with a given <code>manager</code>
	 * value.
	 * 
	 * @param manager
	 *            Name of the <code>Manager</code> to find
	 * @return <code>Employee</code> List. Null if not found.
	 */
	public abstract List<Employee> findByManager(Employee manager);

	/**
	 * Returns an <code>Employee</code> List with a given <code>department</code>
	 * value.
	 * 
	 * @param name
	 *            Name of the <code>department</code> to find
	 * @return <code>Employee</code> List. Null if not found.
	 */
	public abstract Employee findByEmail(String name);

}