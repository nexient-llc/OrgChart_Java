package com.systemsinmotion.orgchart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.JobTitleDao;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleDao jobTitleDao;
}
