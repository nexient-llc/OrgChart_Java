package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("JobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository repository;

	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}

	public JobTitle findJobTitleByID(Integer titleId) {
		return this.repository.findOne(titleId);
	}

	public void removeJobTitle(JobTitle title) {
		this.repository.delete(title);
	}

	public void setRepository(JobTitleRepository repository) {
		this.repository = repository;
	}

	public JobTitle storeJobTitle(JobTitle title) {
		return this.repository.save(title);
	}
	public boolean titleExists(JobTitle title){
		return this.repository.exists(title.getId());
	}
}
