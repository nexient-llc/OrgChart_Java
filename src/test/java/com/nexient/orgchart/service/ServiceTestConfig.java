package com.nexient.orgchart.service;

import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kskronek on 5/24/2016.
 */
@Configuration
@ComponentScan("com.nexient.orgchart.service")
public class ServiceTestConfig {

	@Bean
	public DepartmentRepository getDepartmentRepository() {
		final DepartmentRepository mock = mock(DepartmentRepository.class);
		when(mock.findOne(any(Integer.class))).thenReturn(null);
		return mock;
	}

	@Bean
	public EmployeeRepository getEmployeeRepository() {
		return mock(EmployeeRepository.class);
	}

	@Bean
	public JobTitleRepository getJobTitleRepository() {
		return mock(JobTitleRepository.class);
	}
}
