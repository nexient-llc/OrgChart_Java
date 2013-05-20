package com.systemsinmotion.orgchart.dao;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao {

	/**
	 * Deletes the <code>JobTitle</code> instance from the database.
	 * 
	 * @param jobTitle
	 *            jobTitle instance to delete
	 */
	void delete(JobTitle jobTitle);

	/**
	 * Saves a given <code>JobTitle</code instance and returns its generated
	 * id.
	 * 
	 * @param jobTitle
	 *            The <code>JobTitle</code> instance to be saved
	 * @return The identifier (id) of the new <code>JobTitle</code> instance.
	 */
	Integer save(JobTitle jobTitle);

}
