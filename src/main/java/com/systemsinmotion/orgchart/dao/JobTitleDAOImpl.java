package com.systemsinmotion.orgchart.dao;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDAO")
public class JobTitleDAOImpl implements JobTitleDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public JobTitle findByJobTitleID(int id) {

		try {

			return this.hibernateTemplate.get(JobTitle.class, id);
		} catch (RuntimeException re) {
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> findAllJobTitles() {
		
		try{
	
		return this.hibernateTemplate.find("FROM " + JobTitle.class.getName());
		
		
		}catch(RuntimeException re)
		{throw re;}

	}

	@Override
	public void deleteJobTitle(JobTitle jobTitle_delete) {

		try {

			this.hibernateTemplate.delete(jobTitle_delete);
		}

		catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Integer createJobTitle(JobTitle jobTitle_create) {

		try {
			return (Integer) this.hibernateTemplate.save(jobTitle_create);
		}

		catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public void updatejobTitle(JobTitle jobTitle) {

		try {

			this.hibernateTemplate.update(jobTitle);
		}

		catch (RuntimeException re) {
			throw re;
		}
	}

}