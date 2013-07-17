package com.systemsinmotion.orgchart.dao;

import java.util.ArrayList;
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
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao 
{
	
	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDao.class);	
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Integer create(JobTitle jobTitle)
	{
		LOG.debug("Saving " + jobTitle.getName());
		return (Integer)this.hibernateTemplate.save(jobTitle);					
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> queryAll() {
		LOG.debug("returning all job titles");
		return this.hibernateTemplate.find("from " + JobTitle.class.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public JobTitle queryById(Integer Id) {
		LOG.debug("returning job titles queryById");				

		DetachedCriteria criteria= DetachedCriteria.forClass(JobTitle.class);		
		criteria.add(Restrictions.eq("id", Id.intValue()));	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List<JobTitle> results= new ArrayList<JobTitle>();
		
		results=this.hibernateTemplate.findByCriteria(criteria); 
		
		JobTitle jobTitle = null;
		
		if (results.size()>0)
			jobTitle=results.get(0);
								
 		return jobTitle;
	}
			
	public JobTitle queryByName(String name)
	{	
		LOG.debug("returning job titles queryById");
	
		DetachedCriteria criteria= DetachedCriteria.forClass(JobTitle.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<JobTitle> results= new ArrayList<JobTitle>();
		
		results=hibernateTemplate.findByCriteria(criteria);
				
		JobTitle jobTitle=null;
	
		if (results.size()>0)
			jobTitle=results.get(0);								
 		return jobTitle;		
	}
	
	
	@Override
	public void update(JobTitle job) 
	{
		LOG.debug("updating "+ job.getName());
		this.hibernateTemplate.update(job);
		
	}

	@Override
	public void delete(JobTitle job) 
	{
		//LOG.debug("deleting "+ job.getName());
		this.hibernateTemplate.delete(job);
	}

}
