package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDAO")
public class JobTitleDAOImpl implements JobTitleDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public Integer save(JobTitle jobTitle) 
	{
		
		//insert the new job title object, return the newly generated ID
		try
		{ 
			return (Integer) this.hibernateTemplate.save(jobTitle);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	public void delete(JobTitle jobTitle) 
	{
		
		//delete the selected job title record
		try
		{
			this.hibernateTemplate.delete(jobTitle);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	public void update(JobTitle jobTitle) 
	{
		
		//update the selected job title record
		try
		{
			this.hibernateTemplate.update(jobTitle);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<JobTitle> findAll() 
	{
		
		//retrieve all job title records
		try
		{
			return this.hibernateTemplate.find(" FROM "
					+  JobTitle.class.getName()
					+ " ORDER BY description ");
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	public JobTitle findByJobTitleID(Integer ID) 
	{
		
		//retrieve a job title by its given ID
		try
		{
			return this.hibernateTemplate.get(JobTitle.class, ID);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<JobTitle> findByJobTitleDesc(String desc) 
	{
		
		//retrieve a list of job titles by description, or partial description
		try
		{
			return this.hibernateTemplate.find(" FROM  "
					+ JobTitle.class.getName()
					+ " WHERE description like ? ", "%" + desc + "%");
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

}
