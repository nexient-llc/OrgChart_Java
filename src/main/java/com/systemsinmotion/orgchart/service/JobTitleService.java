package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;


@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository jobTitleRepository;
        
	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleRepository.findAll();
	}
	
	public JobTitle findJobTitleByID(Integer jobTitleID) {
		return this.jobTitleRepository.findOne(jobTitleID);
	}
	
	public JobTitle findByJobTitleName(String jobTitleName) {
		return this.jobTitleRepository.findByName(jobTitleName);
	}
	
	public void setRepository(JobTitleRepository jobTitleRepository) {
		this.jobTitleRepository = jobTitleRepository;
	}
	
	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleRepository.delete(jobTitle);
	}
	
	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.jobTitleRepository.save(jobTitle);
	}
	
}