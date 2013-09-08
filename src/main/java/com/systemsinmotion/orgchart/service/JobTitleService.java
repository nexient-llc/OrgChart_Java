package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {
	
	@Autowired
	private IJobTitleDao jobTitleDao;
	
	public void setJobTitleDao(IJobTitleDao mockJobTitleDao) {
		this.jobTitleDao = mockJobTitleDao;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDao.findAll();
	}

	public JobTitle findJobTitleById(Integer jobTitleId) {
		return this.jobTitleDao.findById(jobTitleId);
	}

	public JobTitle findJobTitleByName(String jobTitleName) {
		return this.jobTitleDao.findByName(jobTitleName);
	}

	public Integer storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleDao.save(jobTitle);
	}

	public void updateJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.update(jobTitle);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.delete(jobTitle);
	}
	
	
}
