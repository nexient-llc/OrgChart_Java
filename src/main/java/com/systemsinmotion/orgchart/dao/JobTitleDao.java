package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.entity.JobTitle;

/**
 * A data access object (DAO) providing persistence and search support for JobTitle entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.JobTitle
 * @author Steven Byks
 */
@Repository("jobTitleDao")
public class JobTitleDao implements IJobTitleDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDao.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#delete(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("deleting JobTitle instance with name: " + jobTitle.getName());
		this.hibernateTemplate.delete(jobTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all JobTitle instances");
		return this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by name");
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDao#findById(java.lang.Integer)
	 */
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting JobTitle instance with id: " + id);
		JobTitle jobTitle = null;

		if (id != null) {
			jobTitle = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return jobTitle;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#findByName(java.lang.String)
	 */
	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding JobTitle instance by name: " + name);
		JobTitle jobTitle = null;

		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitles = this.hibernateTemplate.findByCriteria(criteria);

			if (jobTitles != null && !jobTitles.isEmpty()) {
				jobTitle = jobTitles.get(0);
			}
		}
		return jobTitle;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#save(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#update(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("updating JobTitle instance with name: " + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDao#toString()
	 */
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return getClass().getName() + " {" + newLine + "\thibernateTemplate : " + this.hibernateTemplate.toString()
				+ newLine + "}";
	}

}
