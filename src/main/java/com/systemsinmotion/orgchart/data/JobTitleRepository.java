package com.systemsinmotion.orgchart.data;

import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("jobTitleRepository")
public interface JobTitleRepository extends BaseRepository<JobTitle, Integer> {

	JobTitle findByName(String name);

}
