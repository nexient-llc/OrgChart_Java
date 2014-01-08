package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleDao {

	/**
	 * Deletes the <code>JobTitle</code> instance from the database.
	 * 
	 * @param jobTitle
	 *            JobTitle instance to delete
	 */
	void delete(JobTitle jobTitle);

	/**
	 * Returns all <code>JobTitle</code> instances as a <code>List</code>
	 * 
	 * @return List of JobTitles
	 */
	List<JobTitle> findAll();

	/**
	 * Returns a <code>JobTitle</code> instance with a given <code>name</code>
	 * value.
	 * 
	 * @param name
	 *            Name of the <code>JobTitle</code> to find
	 * @return Instance of <code>JobTitle</code>. Null if not found.
	 */
	JobTitle findByName(String name);

	/**
	 * Returns a <code>JobTitle</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>JobTitle</code> instance to find
	 * @return Single <code>JobTitle</code> instance. Null if not found.
	 */
	JobTitle read(Integer id);

	/**
	 * Saves a give <code>JobTitle</code instance and returns its generated id.
	 * 
	 * @param jobTitle
	 *            The <code>JobTitle</code> instance to be saved
	 * @return The identifier (id) of the new <code>JobTitle</code> instance.
	 */
	Integer save(JobTitle jobTitle);

	String toString();

	/**
	 * Updates an existing <code>JobTitle</code> instance with new values.
	 * 
	 * @param jobTitle
	 *            The <code>JobTitle</code> instance to be updated
	 */
	void update(JobTitle jobTitle);

	JobTitle findById(int id);

}