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

	public void setJobTitleDAO(JobTitleDAO mockJobTitleDAO) {
		this.jobTitleDAO = mockJobTitleDAO;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDAO.findAll();
	}

	public JobTitle findJobTitleByID(Integer jobTitleID) {
		return this.jobTitleDAO.findByJobTitleID(jobTitleID);

	}

	public Integer storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleDAO.save(jobTitle);

	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDAO.delete(jobTitle);

	}

}
