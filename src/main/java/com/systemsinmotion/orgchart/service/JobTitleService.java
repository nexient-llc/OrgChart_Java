package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository repository;

	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}
	
	public List<JobTitle> findAllActiveJobTitles() {
		return this.repository.findByIsActiveIsTrue();
	}
	
	public JobTitle findJobTitleByID(Integer jobTitleId) {
		return this.repository.findOne(jobTitleId);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		jobTitle.setIsActive(false);
		this.repository.save(jobTitle);
//		this.repository.delete(jobTitle);
	}
	
	public void setRepository(JobTitleRepository repository) {
		this.repository = repository;
	}

	public JobTitle storeJobTitle(JobTitle jobTitle) {
		if (jobTitle.getIsActive() == null)
			jobTitle.setIsActive(true);
		return this.repository.save(jobTitle);
	}

}
