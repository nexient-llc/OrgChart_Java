package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

interface JobTitleDAO {
	
	public void findByJobTitleID(int id);
	
	public List<JobTitle> findByDescription(JobTitle jobTitle);
	
	List<Employee> findByEmployee(Employee employee);
	
	List<JobTitle> findAllJobTitles(JobTitle jobTitle);
	
	void deleteJobTItle(JobTitle jobTitle_delete);
	
	Integer addJobTitle(JobTitle jobTitle_add);
	
	
}
