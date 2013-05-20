package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.JobTitle
 * @author Allen Polak
 */
@Repository("jobTitleDAO")
public class JobTitleDAO implements IJobTitleDao {
	
	/**
	 * @see org.slf4j.Logger
	 * A generic loger for splitting outputs from instances of this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDAO.class);
	
	// TODO refactor using better supported framework 
	//	(see this post: 
	//		http://stackoverflow.com/questions/4067775/spring-hibernate-template-when-to-use-and-why)
	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#delete(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("deleting JobTitle instance with id:name = " + jobTitle.getId() + ":" + jobTitle.getName());
		try {
			this.hibernateTemplate.delete(jobTitle);
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#save(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with id:name = " + jobTitle.getId() + ":" + jobTitle.getName());
		try {
			return (Integer) this.hibernateTemplate.save(jobTitle);
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#toString()
	 */
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return getClass().getName() + " {" + newLine + "\thibernateTemplate : " + this.hibernateTemplate.toString()
				+ newLine + "}";
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findByName(java.lang.String )
	 */
	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding JobTitle instance by name = " + name);
		JobTitle jobTitle = null;
		try {
			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitles = this.hibernateTemplate.find("from JobTitle where name=?", name);
			if (null != jobTitles && !jobTitles.isEmpty()) {
				jobTitle = jobTitles.get(0);
			}
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
		return jobTitle;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#update(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void update(JobTitle newJobTitle) {
		LOG.debug("updating JobTitle instance with id:name = " + newJobTitle.getId() + ":" + newJobTitle.getName());
		try {
			this.hibernateTemplate.update(newJobTitle);
		} catch (RuntimeException re) {
			LOG.error("update failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findById(java.lang.Integer )
	 */
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting JobTitle instance with id: " + id);
		try {
			return this.hibernateTemplate.get(JobTitle.class, id);
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("returning all JobTitle instances");
		try {
			return this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by name");
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
	}

}
