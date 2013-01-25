package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentDAO {

	/**
	 * Deletes the <code>Department</code> instance from the database.
	 * 
	 * @param department
	 *            Department instance to delete
	 */
	public abstract void delete(Department department);

	/**
	 * Returns all <code>Department</code> instances as a <code>List</code>
	 * 
	 * @return List of Departments
	 */
	public abstract List<Department> findAll();

	/**
	 * Returns a <code>Department</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>Department</code> instance to find
	 * @return Single <code>Department</code> instance. Null if not found.
	 */
	public abstract Department findById(Integer id);

	/**
	 * Returns a <code>Department</code> instance with a given <code>name</code>
	 * value.
	 * 
	 * @param name
	 *            Name of the <code>Department</code> to find
	 * @return Instance of <code>Department</code>. Null if not found.
	 */
	public abstract Department findByName(String name);

	public abstract List<Department> findByParentDepartment(
			Department department);

	/**
	 * Saves a give <code>Department</code instance and returns its generated
	 * id.
	 * 
	 * @param department
	 *            The <code>Department</code> instance to be saved
	 * @return The identifier (id) of the new <code>Department</code> instance.
	 */
	public abstract Integer save(Department department);

	/**
	 * Updates an existing <code>Department</code> instance with new values.
	 * 
	 * @param department
	 *            The <code>Department</code> instance to be updated
	 */
	public abstract void update(Department department);

	public abstract String toString();

}