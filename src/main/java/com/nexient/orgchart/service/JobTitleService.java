package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.JobTitle;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository repository;

	public List<JobTitle> findAll() {
		return this.repository.findAll();
	}

	public JobTitle findOne(Integer id) {
		return this.repository.findOne(id);
	}

	public Integer saveOrUpdate(JobTitle jobTitle) {
		return this.repository.save(jobTitle).getId();
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.repository.delete(jobTitle);
	}
}
