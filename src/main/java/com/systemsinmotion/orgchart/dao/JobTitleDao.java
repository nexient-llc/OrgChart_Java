package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.systemsinmotion.orgchart.entity.JobTitle;

public class JobTitleDao implements com.systemsinmotion.orgchart.dao.IJobTitleDao 
{

	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDao.class);
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(JobTitle jobTitle)
	{
		LOG.debug("Saving " + jobTitle.getName());
		hibernateTemplate.save(jobTitle);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobTitle> Display_Unflitered_List() {
		LOG.debug("returning all job titles(unflitered list)");
		return hibernateTemplate.find("from", JobTitle.class);
	}

	@Override
	public void update(JobTitle job, String description_In) 
	{
		// TODO Auto-generated method stub
		
	}

	
	
	
}
