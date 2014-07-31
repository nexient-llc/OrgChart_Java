package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleRepository extends JpaRepository <JobTitle, Integer>{

	JobTitle findById(Integer id);
	
	List<JobTitle> findByIsActiveIsTrue();
	
	Page<JobTitle> findByIsActiveIsTrue(Pageable page);
	
	List<JobTitle> findByNameIgnoreCase(String name);
}
