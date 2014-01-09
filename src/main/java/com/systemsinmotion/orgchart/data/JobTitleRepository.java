package com.systemsinmotion.orgchart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {

	JobTitle findByName(String name); 
	
}
