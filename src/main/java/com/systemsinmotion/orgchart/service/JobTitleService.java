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
	
	public JobTitle storeJobTitle(JobTitle mockEJobTitle) {
		return this.repository.save(mockEJobTitle);
	}

	public JobTitle findJobTitleByID(Integer jobTitleId) {
		return this.repository.findOne(jobTitleId);
	}
}