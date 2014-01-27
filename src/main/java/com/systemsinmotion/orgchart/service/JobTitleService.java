package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;


@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository jobTitleRepo;
        
	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleRepo.findAll();
	}
	
	public JobTitle findJobTitleById(Integer jobTitleId) {
		return this.jobTitleRepo.findOne(jobTitleId);
	}
	
	public List<JobTitle> findJobTitleByIsActiveTrue() {
		return this.jobTitleRepo.findJobTitleByIsActiveTrue();
	}
	
	public JobTitle findJobTitleByName(String name) {
		return this.jobTitleRepo.findJobTitleByName(name);
	}
	
	public void setRepository(JobTitleRepository jobTitleRepository) {
		this.jobTitleRepo = jobTitleRepository;
	}
	
	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleRepo.save(jobTitle);
	}
	
}