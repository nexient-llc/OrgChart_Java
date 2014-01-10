package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.BaseEntity;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository jobTitleRepository;

	public void setRepository(JobTitleRepository repository) {
		this.jobTitleRepository = repository;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleRepository.findAll();
	}

	public JobTitle findJobTitleByID(Integer jobTitleID) {
		return this.jobTitleRepository.findById(jobTitleID);
	}

	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleRepository.save(jobTitle);
	}

	public JobTitle findJobTitleByName(String name) {
		return this.jobTitleRepository.findByName(name);
	}
}
