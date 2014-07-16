package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;


@Service("JobTitleService")
public class JobTitleService {
	
	@Autowired
	JobTitleRepository repository;
	
	
	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}
	
	
	public JobTitle storeJobTitle(JobTitle job) {
		return this.repository.save(job);
	}
	
	
}
