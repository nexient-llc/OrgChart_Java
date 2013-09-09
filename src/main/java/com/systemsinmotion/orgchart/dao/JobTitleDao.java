package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDao")
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao {

	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void delete(JobTitle jobTitle) {
        LOG.debug("deleting Job Title instance with name: " + jobTitle.getName());
        this.hibernateTemplate.delete(jobTitle);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all Job Title instances");
		return this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by DESCRIPTION");
	}

	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting Job Title instance with id: " + id);
		JobTitle job = null;
		
		if(id != null) {
			job = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return job;
	}

	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding job Title instance by name: " + name);
		JobTitle job = null;
		
		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitles = this.hibernateTemplate.findByCriteria(criteria);
			
			if (null != jobTitles && !jobTitles.isEmpty()) {
				job = jobTitles.get(0);
			}
		}
		return job;
	}

	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("Saving Job Title instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("Updating Job Title instance with name: " + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
	}

}
