package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
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
	
	private static final Logger LOG = LoggerFactory
			.getLogger(JpaJobTitleDao.class); 
	
	@Autowired
	HibernateTemplate hibernate;

	@Override
	public void delete(JobTitle jobTitle) {
		LOG.debug("deleting JobTitle instance with name: " +
				jobTitle.getName());
		this.hibernate.delete(jobTitle);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() {
		LOG.debug("finding all JobTitle instances");
		return this.hibernate.find("from "+ JobTitle.class.getName()
				+ " order by name");
	}

	@Override
	public JobTitle findByName(String name) {
		LOG.debug("finding JobTitle instace by name: ");
		JobTitle jobTitle = null;
		
		if(StringUtils.hasText(name)){
			DetachedCriteria criteria = DetachedCriteria
					.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<JobTitle> jobTitle2 = this.hibernate
				.findByCriteria(criteria);
			
			if(null != jobTitle2 && !jobTitle2.isEmpty()){
				jobTitle = jobTitle2.get(0);
			}
		}
		return jobTitle;
	}

	@Override
	public JobTitle read(Integer id) {
		LOG.debug("getting JobTitle instance with id: "+ id);
		JobTitle jobTitle = null;
		
		if (id != null) {
			jobTitle = this.hibernate.get(JobTitle.class, id);
		}
		return jobTitle;
	}

	@Override
	public Integer save(JobTitle jobTitle) {
		LOG.debug("saving JobTitle instance with name: "
				+ jobTitle.getName());
		return (Integer) this.hibernate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		LOG.debug("updating Department instance with name: "
				+ jobTitle.getName());
		this.hibernate.update(jobTitle);
	}

}
