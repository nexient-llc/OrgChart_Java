package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	IJobTitleDao jobTitleDao;

	public JobTitle findJobTitletByID(Integer jobTitleId) {
		return this.jobTitleDao.findById(jobTitleId);
	}

	public void setjobTitleDAO(IJobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDao.findAll();
	}

	public Integer storeJobTitle(JobTitle department) {
		return this.jobTitleDao.save(department);
	}

	public void removeJobTitle(JobTitle department) {
		this.jobTitleDao.delete(department);
	}

}
