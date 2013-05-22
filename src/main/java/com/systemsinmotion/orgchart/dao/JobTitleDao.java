package com.systemsinmotion.orgchart.dao;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository
public class JobTitleDao implements IJobTitleDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(JobTitleDao.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("Saving Job title: " + jobTitle.getId() + ", "
				+ jobTitle.getName());
		try {
			return (Integer) this.hibernateTemplate.save(jobTitle);
		} catch (ConstraintViolationException re) {
			return -1;
		} catch (RuntimeException re) {
			LOG.error("saving failed: ", re);
			throw re;
		}
	}

	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("Deleting job title: " + jobTitle.getId() + ", "
				+ jobTitle.getName());
		try {
			this.hibernateTemplate.delete(jobTitle);
		} catch (RuntimeException re) {
			LOG.error("deleting failed: ", re);
			throw re;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all job titles");
		try {
			return this.hibernateTemplate.find(" from "
					+ JobTitle.class.getName() + " order by name ");
		} catch (RuntimeException re) {
			LOG.error("finding all failed: ", re);
			throw re;
		}
	}

	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("finding by id: " + id);
		try {
			if (id != null) {
				return this.hibernateTemplate.get(JobTitle.class, id);
			} else
				return null;
		} catch (IllegalArgumentException re) {
			return null;
		} catch (RuntimeException re) {
			LOG.error("finding by id failed: ", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public JobTitle findByName(String name) {
		LOG.debug("finding by name: " + name);
		try {
			JobTitle job = null;
			List<JobTitle> jobs = hibernateTemplate.find(" from "
					+ JobTitle.class.getName() + " where NAME =?", name);
			if (jobs.size() > 0) {
				job = jobs.get(0);
			}
			return job;
		} catch (RuntimeException re) {
			LOG.error("finding by name failed: ", re);
			throw re;
		}
	}

	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("updating job title: " + jobTitle.getId() + ", "
				+ jobTitle.getName());
		try {
			this.hibernateTemplate.update(jobTitle);
		} catch (RuntimeException re) {
			LOG.error("updating failed: ", re);
			throw re;
		}
	}
}
