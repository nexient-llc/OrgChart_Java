package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleDao")
public class JpaJobTitleDao implements JobTitleDao {

	@Override
	public void delete(JobTitle jobTitle) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<JobTitle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobTitle findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobTitle read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(JobTitle jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(JobTitle jobTitle) {
		// TODO Auto-generated method stub

	}

}
