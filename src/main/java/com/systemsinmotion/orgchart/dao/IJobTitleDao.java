package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao {
	
	/**
	 * Deletes given instance of JobTitle from database
	 * 
	 * @param jobTitle
	 * 			JobTitle instance to delete
	 */
	public abstract void delete(JobTitle jobTitle);
	
	/**
	 * Returns all JobTitle instances as a List
	 * 
	 * @return List of JobTitles
	 */
	public abstract List<JobTitle> findAll();
	
	/**
	 * Returns instance of JobTitle with a given id
	 * 
	 * @param id
	 * 			Id of JobTitle to find
	 * @return Instance of JobTitle. Null if not found.
	 */
	public abstract JobTitle findById(Integer id);
	
	/**
	 * Returns instance of JobTitle with a given name
	 * 
	 * @param name
	 * 			Name of JobTitle instance to find
	 * @return Instance of JobTitle. Null if void.
	 */
	public abstract JobTitle findByName(String name);
	
	/**
	 * Saves a given JobTitle instance and returns its generated id
	 * 
	 * @param jobTitle
	 * 			The JobTitle instance to be saved
	 * @return Generated id for instance of JobTitle
	 */
	public abstract Integer save(JobTitle jobTitle);
	
	/**
	 * Updates a given JobTitle instance with new values
	 * 
	 * @param jobTitle
	 * 			JobTitle instance to be updated
	 */
	public abstract void update(JobTitle jobTitle);
	
}
