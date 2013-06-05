package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleDao jobTitleDao;

	public JobTitle findJobTitleByID(Integer jobTitleId) {
		return this.jobTitleDao.findById(jobTitleId);
	}

	public void setJobTitleDao(JobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDao.findAll();
	}

	public Integer storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleDao.save(jobTitle);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.delete(jobTitle);
	}

	public void updateJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.update(jobTitle);
	}

}
