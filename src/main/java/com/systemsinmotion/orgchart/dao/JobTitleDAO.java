package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleDAO {
	
	//save a new job title record, return its generated id
	public Integer createJobTitle(JobTitle jobTitle_create);
	
	//update an existing job title
	public void updatejobTitle(JobTitle jobTitle);
	
	//delete a job title
	public void deleteJobTitle(JobTitle jobTitle_delete);
	
	//find all job titles
	public List<JobTitle> findAllJobTitles();
	
	//find a specific job title by id
	public JobTitle findByJobTitleID(int id);
	
	//find a list of titles by whole or partial description
	public List<JobTitle> findByDescription(String desc);

}
