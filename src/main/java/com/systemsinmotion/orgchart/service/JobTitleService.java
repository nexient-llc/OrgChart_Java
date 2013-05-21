package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IJobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {
	@Autowired
	IJobTitleDAO jobTitleDAO;

	public JobTitle findJobTitleByID(Integer jobTitleId) {
		return this.jobTitleDAO.findById(jobTitleId);
	}

	public void setJobTitleDAO(IJobTitleDAO jobTitleDAO) {
		this.jobTitleDAO = jobTitleDAO;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDAO.findAll();

	}

	public Integer storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleDAO.save(jobTitle);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDAO.delete(jobTitle);
	}
}
