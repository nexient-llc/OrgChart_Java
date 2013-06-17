package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobtitleService")
public class JobTitleService {

	@Autowired
	JobTitleDao jobtitleDAO;

	public JobTitle findJobTitleByID(Integer jobtitleId) {
		return this.jobtitleDAO.findById(jobtitleId);
	}

	public void setJobTitleDAO(JobTitleDao deparmentDAO) {
		this.jobtitleDAO = jobtitleDAO;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobtitleDAO.findAll();
	}

	public Integer storeJobTitle(JobTitle jobtitle) {
		return this.jobtitleDAO.save(jobtitle);
	}

	public void removeJobTitle(JobTitle jobtitle) {
		this.jobtitleDAO.delete(jobtitle);
	}

}
