package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao 
{
	public Integer create(JobTitle job);
	
	public void delete(JobTitle job);
	
	public List<JobTitle> queryAll();
	
	public void update(JobTitle job);

	public JobTitle queryById(Integer id);

	public JobTitle queryByName(String name);
		
	
}
