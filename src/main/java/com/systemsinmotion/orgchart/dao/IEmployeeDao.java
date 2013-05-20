package com.systemsinmotion.orgchart.dao;

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
	 * Returns all <code>Department</code> instances as a <code>List</code>
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
	 * Returns a <code>Employee</code> instance with a given <code>first_name, last_name</code>
	 * value.
	 * 
	 * @param first_name, last_name
	 *            Name of the <code>Employee</code> to find
	 * @return Instance of <code>Employee</code>. Null if not found.
	 */
	public abstract Employee findByName(String first_name, String last_name);
	
	/**
	 * Returns a <code>Employee</code> list with a given <code>department</code>
	 * value.
	 * 
	 * @param department
	 *            Department to find employees of
	 * @return List of Employees. Empty list if none found.
	 */
	public abstract List<Employee> findByDepartment(
			Department department);

	/**
	 * Saves a give <code>Employee</code instance and returns its generated
	 * id.
	 * 
	 * @param Employee
	 *            The <code>Employee</code> instance to be saved
	 * @return The identifier (id) of the new <code>Employee</code> instance.
	 */
	public abstract Integer save(Employee employee);

	/**
	 * Updates an existing <code>Employee</code> instance with new values.
	 * 
	 * @param employee
	 *            The <code>Employee</code> instance to be updated
	 */
	public abstract void update(Employee employee);

	public abstract String toString();

}
