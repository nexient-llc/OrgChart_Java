package com.systemsinmotion.orgchart.dao;

import java.util.Collections;
import java.util.List;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository
public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Integer save(JobTitle jobTitle) {
		return (Integer) this.hibernateTemplate.save(jobTitle);
	}

	@Override
	public void update(JobTitle jobTitle) {
		this.hibernateTemplate.update(jobTitle);
	}

	@Override
	public void delete(JobTitle jobTitle) {
		this.hibernateTemplate.delete(jobTitle);
	}

	@Override
	public JobTitle findById(Integer id) {
		
		JobTitle jobTitleId = null;
		
		if (id != null){
			jobTitleId = this.hibernateTemplate.get(JobTitle.class, id);
		}
		return jobTitleId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> findAll() {
		
		List<JobTitle> jobTitle = Collections.EMPTY_LIST;
		DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
		jobTitle = this.hibernateTemplate.findByCriteria(criteria);
		
		return jobTitle;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public JobTitle findByName(String name) {
		
		JobTitle jobTitle = null;
		
		if(StringUtils.hasText(name)){
			DetachedCriteria criteria = DetachedCriteria.forClass(JobTitle.class);
			criteria.add(Restrictions.eq("name", name));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			List<JobTitle> jobTitles = this.hibernateTemplate.findByCriteria(criteria);
			
			if(!jobTitles.isEmpty() && jobTitles != null){
				jobTitle = jobTitles.get(0);
			}	
		}
		return jobTitle;
	}
}
