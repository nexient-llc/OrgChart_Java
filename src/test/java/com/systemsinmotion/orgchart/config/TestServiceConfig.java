package com.systemsinmotion.orgchart.config;

import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.MockDepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Configuration
@ComponentScan({"com.systemsinmotion.orgchart.service"})
public class TestServiceConfig {
	private List<Department> listFoundDepartment;
	private Department mockDepartment;
	
	@PostConstruct
	private void init(){
		listFoundDepartment=new ArrayList<Department>();
		mockDepartment=Entities.department(Entities.DEPT_ID);
		listFoundDepartment.add(mockDepartment);
	}
	
	@Bean
	Department getDepartment(){
		return this.mockDepartment;
	}
		
	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listFoundDepartment);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		return repo;	
	}
}


