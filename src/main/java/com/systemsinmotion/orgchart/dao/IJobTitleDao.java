package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao {

	/**
	 * Deletes the <code>JobTitle</code> instance from the database.
	 * 
	 * @param job_title
	 *            JobTitle instance to delete
	 */
	public abstract void delete(JobTitle job_title);

	/**
	 * Returns all <code>JobTitle</code> instances as a <code>List</code>
	 * 
	 * @return List of JobTitles
	 */
	public abstract List<JobTitle> findAll();

	/**
	 * Returns a <code>JobTitle</code> instance having a given <code>id</code>
	 * value.
	 * 
	 * @param id
	 *            Identifier of <code>JobTitle</code> instance to find
	 * @return Single <code>JobTitle</code> instance. Null if not found.
	 */
	public abstract JobTitle findById(Integer id);

	/**
	 * Returns a <code>JobTitle</code> instance with a given <code>name</code>
	 * value.
	 * 
	 * @param name
	 *            Name of the <code>JobTitle</code> to find
	 * @return Instance of <code>JobTitle</code>. Null if not found.
	 */
	public abstract JobTitle findByName(String name);

	public abstract Integer save(JobTitle job_title);

	/**
	 * Updates an existing <code>JobTitle</code> instance with new values.
	 * 
	 * @param job_title
	 *            The <code>JobTitle</code> instance to be updated
	 */
	public abstract void update(JobTitle job_title);

	public abstract String toString();

}