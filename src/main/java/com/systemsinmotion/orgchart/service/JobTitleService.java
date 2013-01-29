package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDAO;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleDAO jobTitleDAO;
	
	//@Autowired
	//EmployeeService employeeService;

	public JobTitle findByJobTitleID(int id) {
		return this.jobTitleDAO.findByJobTitleID(id);
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDAO.findAllJobTitles();
	}

	public void deleteJobTitle(JobTitle jobTitle_delete) {
		this.jobTitleDAO.deleteJobTitle(jobTitle_delete);
	}

	public Integer createJobTitle(JobTitle jobTitle_create) {
		return this.jobTitleDAO.createJobTitle(jobTitle_create);
	}
	public void updatejobTitle(JobTitle jobTitle)
	{
		this.jobTitleDAO.updatejobTitle(jobTitle);
		
	}
	
	public void setJobTitleDAO(JobTitleDAO jobTitleDAO)
	{
		this.jobTitleDAO=jobTitleDAO;
	}
}
