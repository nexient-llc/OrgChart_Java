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

	public void delete(JobTitle jobTitle) {
		try {
			this.hibernateTemplate.delete(jobTitle);
		} catch (RuntimeException re) {
			throw re;
		}

	}

	public Integer save(JobTitle jobTitle) {
		try {
			return (Integer) this.hibernateTemplate.save(jobTitle);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		try {
			return this.hibernateTemplate.find("from "
					+ JobTitle.class.getName() + " order by DESCRIPTION");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void update(JobTitle jobTitle) {
		try {
			this.hibernateTemplate.update(jobTitle);
		} catch (RuntimeException re) {
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public JobTitle findByDescription(String desc) {
		try {
			JobTitle title = null;
			List<JobTitle> titles = this.hibernateTemplate
					.find(" from " + JobTitle.class.getName()
							+ " where DESCRIPTION = ?", desc);
			if (titles.size() > 0) {
				title = titles.get(0);
			}
			return title;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public JobTitle findByJobTitleID(Integer ID) {
		try {
			return this.hibernateTemplate.get(JobTitle.class, ID);
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
