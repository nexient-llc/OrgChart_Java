package com.systemsinmotion.orgchart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service
public class JobTitleService {

	@Autowired
	IJobTitleDao JobTitleDao;
	
	public void setJobTitleDao(IJobTitleDao jobTitleDao){
		this.JobTitleDao = jobTitleDao;
	}
	
	public Integer storeJobTitle(JobTitle jobTitle){
		return this.JobTitleDao.save(jobTitle);
	}
	
	public void removeJobTitle(JobTitle jobTitle){
		this.JobTitleDao.delete(jobTitle);
	}
	
	public List<JobTitle> findAllJobTitles(){
		return this.JobTitleDao.findAll();
	}
	
	public JobTitle findJobTitleById(Integer jobTitleId){
		return this.JobTitleDao.findById(jobTitleId);
	}
	
	public JobTitle findJobByName(String name){
		return this.JobTitleDao.findByName(name);
	}
	
	
	public void updateJobTitle(JobTitle jobTitle){
		this.JobTitleDao.update(jobTitle);
	}
}
