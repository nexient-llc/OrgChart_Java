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

	public JobTitle findJobTitleByID(Integer titleId) {
		return this.jobTitleRepository.findOne(titleId);
	}

	public JobTitle removeJobTitle(JobTitle title) {
		this.jobTitleRepository.delete(title);
		return title;
	}

	public void setRepository(JobTitleRepository repository) {
		this.jobTitleRepository = repository;
	}

	public JobTitleRepository getRepository() {
		return this.jobTitleRepository;
	}

	public JobTitle storeJobTitle(JobTitle title) {
		return this.jobTitleRepository.save(title);
	}

	public JobTitle editJobTitle(JobTitle title, Object active) {

		JobTitle job = this.jobTitleRepository.findOne(title.getId());
		job.setName(title.getName());

		if (active == null) {
			job.setIsActive(false);
		} else {
			job.setIsActive(true);
		}
		return this.jobTitleRepository.save(job);
	}

	public boolean titleExists(JobTitle title) {
		return this.jobTitleRepository.exists(title.getId());
	}

	public List<JobTitle> activeJobTitles() {
		return this.jobTitleRepository.findByIsActiveIsTrue();
	}

}
