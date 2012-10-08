package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleDAO {

	void delete(JobTitle jobTitle);

	Integer save(JobTitle jobTitle);

	void update(JobTitle jobTitle);

	JobTitle findByDescription(String desc);

	JobTitle findByJobTitleID(Integer ID);

	List<JobTitle> findAll();

}
