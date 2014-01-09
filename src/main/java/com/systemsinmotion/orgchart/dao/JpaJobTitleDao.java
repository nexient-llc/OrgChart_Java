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

import com.systemsinmotion.orgchart.entity.Department;
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

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all JobTitle instances");
		return this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by name");
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findByName(java.lang.String )
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

			if (null != jobTitles && !jobTitles.isEmpty()) {
				jobTitle = jobTitles.get(0);
			}
		}
		return jobTitle;
	}

	@Override
	public JobTitle read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#save(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with name: " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findById(java.lang.Integer )
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
}
