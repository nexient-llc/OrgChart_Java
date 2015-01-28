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

	public JobTitle findByName(String name) {
		return this.repository.findByName(name);
	}

	public JobTitle findbyID(Integer id) {
		return this.repository.findOne(id);
	}

	private JobTitle storeJobTitle(JobTitle jobTitle) {
		return repository.save(jobTitle);
	}

	public JobTitle saveJobTitle(JobTitle jobTitle) {
		jobTitle.setIsActive(true);
		return storeJobTitle(jobTitle);
	}

	public void removeJobTitle(JobTitle jobtitle) {
		jobtitle.setIsActive(false);
		storeJobTitle(jobtitle);
	}
	
	public List<JobTitle> findAllActiveJobTitles() {
		return this.repository.findByIsActiveIsTrue();
	}

}