package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	private JobTitleRepository repository;

	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}

	public JobTitle findJobTitleByID(Integer jobTitleId) {
		return this.repository.findOne(jobTitleId);
	}
		
	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.repository.save(jobTitle);
	}

	public void delete(JobTitle jobTitle) {
		this.repository.delete(jobTitle);
	}

	public JobTitle save(JobTitle jobTitle) {
		return this.repository.save(jobTitle);
	}
}
