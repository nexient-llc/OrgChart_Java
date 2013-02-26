package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDAO")
public class JobTitleDAOImpl implements JobTitleDAO {

	@Autowired
	HibernateTemplate ht;

	//create a new job title, return the new id
	public Integer createJobTitle(JobTitle jobTitle_create) 
	{
		try 
		{
			return (Integer) this.ht.save(jobTitle_create);
		}
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//update an existing job title
	public void updatejobTitle(JobTitle jobTitle) 
	{
		try 
		{
			this.ht.update(jobTitle);
		}
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//delete a selected job title
	public void deleteJobTitle(JobTitle jobTitle_delete) 
	{
		try 
		{
			this.ht.delete(jobTitle_delete);
		}
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//find all job titles
	@SuppressWarnings("unchecked")
	public List<JobTitle> findAllJobTitles() 
	{	
		try
		{
			return this.ht.find("FROM " + JobTitle.class.getName());
		}
		catch (RuntimeException re)
		{
			throw re;
		}
	}
	
	//find specific job title by id
	public JobTitle findByJobTitleID(int id) 
	{
		try 
		{
			return this.ht.get(JobTitle.class, id);
		} 
		catch (RuntimeException re) 
		{
			throw re;
		}
	}
	
	//find job titles by whole or partial description
	@SuppressWarnings("unchecked")
	public List<JobTitle> findByDescription(String desc)
	{
		try
		{
			return this.ht.find(
					" FROM "
					+ JobTitle.class.getName()
					+ " WHERE description like ? "
					, "%" + desc + "%"
					);
		}
		catch (RuntimeException re)
		{
			throw re;
		}
	}

}