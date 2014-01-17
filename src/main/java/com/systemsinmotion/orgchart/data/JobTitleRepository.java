package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.JobTitle;

public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {

	JobTitle findById(Integer id);

	JobTitle findByName(String name);

	List<JobTitle> findByIsActiveTrue();
}
