package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.data.JobTitleRepository;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository repository;

	public List<JobTitle> findAllJobTitles(){
		return this.repository.findAll();
	}
	
	public List<JobTitle> findAllActiveJobTitles() {
		return this.repository.findByIsActiveIsTrue();
	}	
	
	public JobTitle findById(Integer id) {
		return this.repository.findOne(id);
	}
	
	@Transactional
	public void removeJobTitle(JobTitle jobTitle) {
		this.repository.delete(jobTitle);
	}	
	
	public void removeJobTitle(Integer id) {
		this.repository.delete(id);
	}		

	@Transactional
	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.repository.saveAndFlush(jobTitle);
	}
	
	public long rowCount(){
		return this.repository.count();
	}
}
