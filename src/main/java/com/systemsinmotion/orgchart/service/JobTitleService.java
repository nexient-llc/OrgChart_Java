package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleDAO jobTitleDAO;

	public void setJobTitleDAO(JobTitleDAO jobTitleDAO)
	{
		this.jobTitleDAO=jobTitleDAO;
	}
	
	//create
	public Integer createJobTitle(JobTitle jobTitle_create) 
	{
		return this.jobTitleDAO.createJobTitle(jobTitle_create);
	}
	
	//update
	public void updatejobTitle(JobTitle jobTitle)
	{
		this.jobTitleDAO.updatejobTitle(jobTitle);
	}
	
	//delete
	public void deleteJobTitle(JobTitle jobTitle_delete) 
	{
		this.jobTitleDAO.deleteJobTitle(jobTitle_delete);
	}
	
	//find all
	public List<JobTitle> findAllJobTitles() 
	{
		return this.jobTitleDAO.findAllJobTitles();
	}
	
	//find by id
	public JobTitle findByJobTitleID(int id) 
	{
		return this.jobTitleDAO.findByJobTitleID(id);
	}

	//find by description
	public List<JobTitle> findByJobTitleDescription(String desc)
	{
		return this.jobTitleDAO.findByDescription(desc);
	}

}
