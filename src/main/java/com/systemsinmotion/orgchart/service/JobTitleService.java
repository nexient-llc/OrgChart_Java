package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IJobTitleDao;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	private IJobTitleDao jobTitleDao;

	public JobTitle findJobTitletByID(Integer jobTitleId) {
		return this.jobTitleDao.findById(jobTitleId);
	}

	public void setjobTitleDAO(IJobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	public List<JobTitle> findAllJobTitles() {
		return this.jobTitleDao.findAll();
	}

	public Integer storeJobTitle(JobTitle jobTitle) {
		/*
		 * See update, same idea.
		 */
		try {
			return this.jobTitleDao.save(jobTitle);
		} catch (Exception e) {
		}

		return null;
	}

	public void removeJobTitle(JobTitle jobTitle) {
		this.jobTitleDao.delete(jobTitle);
	}

	public void removeJobTitleConfirm(Integer deleteId, String confirmString) {
		JobTitle jobTitle = findJobTitletByID(deleteId);
		if (jobTitle != null && jobTitle.getName().equals(confirmString)) {
			/*
			 * Silently fails if we attempt to delete a job title that someone
			 * is currently holding.
			 */
			try {
				jobTitleDao.delete(jobTitle);
			} catch (Exception e) {
			}
		}
	}

	public void updateJobTitle(JobTitle jobTitle) {
		/*
		 * If a job title is updated to share a name with another job title an
		 * exception will be thrown as names are unique. This will silently fail
		 * to update when that happens.
		 */
		try {
			this.jobTitleDao.update(jobTitle);
		} catch (Exception e) {

		}
	}
}
