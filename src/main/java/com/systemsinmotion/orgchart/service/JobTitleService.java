package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.JobTitleRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
    JobTitleRepository repository;

	public List<JobTitle> findAllJobTitles() {
		return this.repository.findAll();
	}

    public JobTitle findJobTitleByName(String name) {
        return this.repository.findByName(name);
    }

	public JobTitle findJobTitleById(Integer Id) {
		return this.repository.findOne(Id);
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.repository.delete(jobTitle);
	}

	public void setRepository(JobTitleRepository repository) {
		this.repository = repository;
	}

	public JobTitle storeJobTitle(JobTitle jobTitle) {
		return this.repository.save(jobTitle);
	}
//
//    public List<JobTitle> findActiveJobTitles() {
//        return this.repository.findByisActive();
//    }

}
