package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {

	JobTitle findByName(String name);
//
//    List<JobTitle> findAllJobTitles();

    JobTitle findById(Integer Id);



}
