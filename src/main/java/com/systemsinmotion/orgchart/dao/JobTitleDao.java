package com.systemsinmotion.orgchart.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao {

	public static final Logger Log = LoggerFactory.getLogger(JobTitleDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Integer save(JobTitle jobTitle) {
		Log.debug("Saving JobTitle: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		Log.debug("Updating JobTitle" + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
		
	}

	@Override
	public void delete(JobTitle jobTitle) {
		Log.debug("Deleting JobTitle" + jobTitle.getName());
		this.hibernateTemplate.delete(jobTitle);
		
	}

	@Override
	public JobTitle findById(Integer id) {
		JobTitle jobTitleId = null;
		if (id != null){
			jobTitleId = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return jobTitleId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobTitle findByName(String name) {
		JobTitle jobName = null;
		List<JobTitle> jobNames = this.hibernateTemplate.find("FROM" + JobTitle.class.getName() + "WHERE NAME = ?", name);
		if(jobNames != null){
			jobName = jobNames.get(0);
		}
		return jobName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> findAll() {
		List<JobTitle> jobTitleAll;
		jobTitleAll = this.hibernateTemplate.find("FROM" + JobTitle.class.getName());
		return jobTitleAll;
	}

	
}
