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

@Repository("jobTitleDao")
public class JpaJobTitleDao implements JobTitleDao {
	
	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JpaJobTitleDao.class);
	
	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public void delete(JobTitle jobTitle) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<JobTitle> findAll() {
		LOG.debug("finding all Department instances");
		return this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by name");
	}
	
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting Department instance with id: " + id);
		JobTitle jobTitle = null;

		if (id != null) {
			jobTitle = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return jobTitle;
	}

	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding Department instance by name: " + name);
		JobTitle title = null;

		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitles = this.hibernateTemplate.findByCriteria(criteria);

			if (null != jobTitles && !jobTitles.isEmpty()) {
				title = jobTitles.get(0);
			}
		}
		return title;
	}

	@Override
	public JobTitle read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving Department instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		// TODO Auto-generated method stub

	}

}
