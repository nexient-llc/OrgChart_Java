package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleRepository extends BaseRepository<JobTitle,Integer> {
	
	JobTitle findByName(String name);
	
	JobTitle findById(Integer id);
}