package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleDao jobtitleDao;

	public JobTitle findJobTitleByID(Integer jobtitleId) {
		return this.jobtitleDao.findById(jobtitleId);
	}

	public void setJobTitleDAO(JobTitleDao deparmentDAO) {
		this.jobtitleDao = jobtitleDao;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobtitleDao.findAll();
	}

	public Integer storeJobTitle(JobTitle jobtitle) {
		return this.jobtitleDao.save(jobtitle);
	}

	public void updateJobTitle(JobTitle jobtitle) {
		this.jobtitleDao.update(jobtitle);
	}

	public void removeJobTitle(JobTitle jobtitle) {
		this.jobtitleDao.delete(jobtitle);
	}

}
