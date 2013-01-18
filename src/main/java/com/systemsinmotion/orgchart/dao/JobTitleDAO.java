package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleDAO {

	//insert a new job title into the database and return its assigned id
	public abstract Integer save(JobTitle jobTitle);
	
	//delete an existing job title from the database
	public abstract void delete(JobTitle jobTitle);
	
	//update an existing job title record
	public abstract void update(JobTitle jobTitle);
	
	//retrieve all job title records
	public abstract List<JobTitle> findAll();
	
	//retrieve a specific job title by ID
	public abstract JobTitle findByJobTitleID(Integer ID);
	
	//retrieve job title(s) by description or partial description
	public abstract List<JobTitle> findByJobTitleDesc(String desc);
	
}
