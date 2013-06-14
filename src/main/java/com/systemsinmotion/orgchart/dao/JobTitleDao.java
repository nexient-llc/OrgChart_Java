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
 * @author Keith Skronek
 */
@Repository("jobtitleDao")
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao {

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
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#delete(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public void delete(JobTitle jobtitle) {
		LOG.debug("deleting JobTitle instance with name: " + jobtitle.getName());
		this.hibernateTemplate.delete(jobtitle);
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
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findById(java.lang.Integer )
	 */
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("getting JobTitle instance with id: " + id);
		JobTitle dept = null;

		if (id != null) {
			dept = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return dept;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#findByName(java.lang.String )
	 */
	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding JobTitle instance by name: " + name);
		JobTitle dept = null;

		if (StringUtils.hasText(name)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<JobTitle> jobtitles = this.hibernateTemplate.findByCriteria(criteria);

			if (null != jobtitles && !jobtitles.isEmpty()) {
				dept = jobtitles.get(0);
			}
		}
		return dept;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#save(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobtitle) {
		LOG.debug("saving JobTitle instance with name: " + jobtitle.getName());
		return (Integer) this.hibernateTemplate.save(jobtitle);
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
	 * @see com.systemsinmotion.orgchart.dao.JobTitleDAO#update(com.systemsinmotion .orgchart.entity.JobTitle)
	 */
	@Override
	public void update(JobTitle jobtitle) {
		LOG.debug("updating JobTitle instance with name: " + jobtitle.getName());
		this.hibernateTemplate.update(jobtitle);
	}
}