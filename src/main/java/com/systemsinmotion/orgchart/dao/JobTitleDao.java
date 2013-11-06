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

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDao")
public class JobTitleDao implements IJobTitleDao {

	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IJobTitleDao#delete(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("Deleting Job Title " + jobTitle.getName());;
		this.hibernateTemplate.delete(jobTitle);;
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IJobTitleDao#findAll()
	 */
	@Override
	public List<JobTitle> findAll() {
		LOG.debug("Listing all Job Titles");
		
		@SuppressWarnings("unchecked")
		List<JobTitle> jobTitles = this.hibernateTemplate.find("from " + JobTitle.class.getName() + " order by name");
		
		return jobTitles;
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IJobTitleDao#findById(java.lang.Integer)
	 */
	@Override
	public JobTitle findById(Integer id) {
		LOG.debug("Finding a Job Title with id " + id);
		JobTitle jobTitle = null;
		
		if( null != id ) {
			jobTitle = this.hibernateTemplate.get(JobTitle.class, id);
		}
		
		return jobTitle;
	}

	@Override
	public JobTitle findByName(String name) {
		LOG.debug("Finding a job with name " + name);

		JobTitle jobTitle = null;
		
		if( null != name ) {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<JobTitle> queryResults = this.hibernateTemplate
					.findByCriteria(criteria);

			if (null != queryResults && !queryResults.isEmpty())
				jobTitle = queryResults.get(0);
		}

		return jobTitle;
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IJobTitleDao#save(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("Saving a new Job Title by name " + jobTitle.getName());
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IJobTitleDao#update(com.systemsinmotion.orgchart.entity.JobTitle)
	 */
	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("Updating Job Title by name " + jobTitle.getName());
		this.hibernateTemplate.update(jobTitle);
	}
}
