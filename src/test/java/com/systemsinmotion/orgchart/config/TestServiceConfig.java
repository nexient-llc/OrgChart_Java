package com.systemsinmotion.orgchart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.MockDepartmentRepository;

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.service"})
public class TestServiceConfig {
	@Bean
	DepartmentRepository getDepartmentRepository() {
		return new MockDepartmentRepository();
	}
}
