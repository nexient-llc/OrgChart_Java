package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface IJobTitleDao 
{
	public void add(JobTitle job);
	
	public List<JobTitle> Display_Unflitered_List();
	
	public void update(JobTitle job, String description_In);
		
	
}
