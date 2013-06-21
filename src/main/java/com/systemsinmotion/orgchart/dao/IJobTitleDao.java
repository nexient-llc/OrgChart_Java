/*
 	 * JobTitle Interface
	 * Job Entity - Needs Save/Update/Delete
	 * Job Entity Search - Id, Name, All
*/

package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao {
	
	public abstract Integer save(JobTitle jobTitle);
	
	public abstract void update(JobTitle jobTitle);
	
	public abstract void delete(JobTitle jobTitle);
	
	public abstract JobTitle findById(Integer id);
	
	public abstract JobTitle findByName(String name);
	
	public abstract List<JobTitle> findAll();
}
