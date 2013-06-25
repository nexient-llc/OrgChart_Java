package com.systemsinmotion.orgchart.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao {

	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Integer save(JobTitle jobTitle) {
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		this.hibernateTemplate.update(jobTitle);
		
	}

	@Override
	public void delete(JobTitle jobTitle) {
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

	@Override
	public JobTitle findByName(String name) {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> findAll() {
		return null;
	}

	
}
