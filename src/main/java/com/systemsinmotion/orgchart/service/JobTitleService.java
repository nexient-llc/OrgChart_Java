package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;


@Service("jobTitleService")
public class JobTitleService 
{
	@Autowired JobTitleDao jobTitleDao;
 		
	public JobTitle findJobTitleByID(Integer JobTitleId) 
	{

		return this.jobTitleDao.queryById(JobTitleId);
	}

	public void setJobTitleDAO(JobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	public JobTitle findJobTitleByName(String jobName) 
	{

		return this.jobTitleDao.queryByName(jobName);
	
	}
	
	public void updateJobTitle(JobTitle jobTitle){

		this.jobTitleDao.update(jobTitle);
		
	}
	
	
	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDao.queryAll();
	}

	public Integer storeJobTitle(JobTitle JobTitle) {
		return this.jobTitleDao.create(JobTitle);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.delete(jobTitle);
	}


}
