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

	public void setRepository(JobTitleRepository repository) {
		this.repository = repository;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}

	public JobTitle storeJobTitle(JobTitle mockTitle) {
		return this.repository.save(mockTitle);
	}
	
	public JobTitle findJobTitleById(Integer id) {
		return this.repository.findById(id);
	}

}
