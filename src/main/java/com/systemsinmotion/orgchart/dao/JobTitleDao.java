package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDao")
public class JobTitleDao implements IJobTitleDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("deleting JobTitle instance with name: " + jobTitle.getName());
		this.hibernateTemplate.delete(jobTitle);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all JobTitle instances");
		DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
		criteria.addOrder(Order.asc("name"));
		
		return this.hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting JobTitle instance with id: " + id);
		JobTitle jobTitle = null;
		
		if (id != null) {
			jobTitle = this.hibernateTemplate.get(JobTitle.class, id);
		}
		
		return jobTitle;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public JobTitle findByName(String name) {
		LOG.debug("getting JobTitle instance with name: " + name);
		JobTitle jobTitle = null;
		List<JobTitle> jobTitles = null;
		
		if (name != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			
			jobTitles = this.hibernateTemplate.findByCriteria(criteria);
		}
		
		if (jobTitles != null && jobTitles.size() > 0) {
			jobTitle = jobTitles.get(0);
		}
		
		return jobTitle;
	}

	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("updating JobTitle instance with name: " + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
	}

}
