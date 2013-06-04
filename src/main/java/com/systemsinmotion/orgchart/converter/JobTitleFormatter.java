package com.systemsinmotion.orgchart.converter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.service.JobTitleService;

@Component
public class JobTitleFormatter implements Formatter<JobTitle> {

	@Autowired
	private JobTitleService jobTitleService;

	@Override
	public String print(JobTitle jobTitle, Locale arg1) {
		return jobTitle.getName();
	}

	@Override
	public JobTitle parse(String jobTitleId, Locale arg1) throws ParseException {
		return jobTitleService.findJobTitleByID(Integer.parseInt(jobTitleId));
	}

}