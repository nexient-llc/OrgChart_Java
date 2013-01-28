package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

interface JobTitleDAO {

	public JobTitle findByJobTitleID(int id);

	public List<JobTitle> findAllJobTitles();

	public void deleteJobTitle(JobTitle jobTitle_delete);

	public Integer createJobTitle(JobTitle jobTitle_create);

	public void updatejobTitle(JobTitle jobTitle);

	



}
