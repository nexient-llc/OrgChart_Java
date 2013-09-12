package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {
	
	@Autowired
	JobTitleDao jobTitleDAO;
	
	public JobTitle findJobTitleByID(Integer jobTitleId){
		return this.jobTitleDAO.findById(jobTitleId);
	}
	
	public void setJobTitleDAO(JobTitleDao jobTitleDAO) {
		this.jobTitleDAO = jobTitleDAO;
	}
	
	public List<JobTitle> findAllJobTitles(){
		return this.jobTitleDAO.findAll();
	}
	
	public Integer storeJobTitle(JobTitle jobTitle){
		return this.jobTitleDAO.save(jobTitle);
	}
	
	public void updateJobTitle(JobTitle jobTitle){
		this.jobTitleDAO.update(jobTitle);
	}
	
	public void removeJobTitle(JobTitle jobTitle){
		this.jobTitleDAO.delete(jobTitle);
	}
}
