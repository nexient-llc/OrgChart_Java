package com.systemsinmotion.orgchart.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.JobTitle;

import java.util.*;

public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
	
	
	List<JobTitle> findAll();
	
	JobTitle findByName(String name);

	JobTitle findById(Integer id);
}
