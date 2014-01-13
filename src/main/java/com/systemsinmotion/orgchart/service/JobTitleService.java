package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service
public class JobTitleService {

	@Autowired
	JobTitleRepository jobRepo;

	public void setJobTitleService(JobTitleRepository mockJobTitleRepo) {
		this.jobRepo = mockJobTitleRepo;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobRepo.findAll();
	}

	public JobTitle storeJobTitle(JobTitle mockJobTitle) {
		return this.jobRepo.save(mockJobTitle);
	}

	public JobTitle findJobTitleByID(Integer Id) {
		return this.jobRepo.findById(Id);
	}

}
