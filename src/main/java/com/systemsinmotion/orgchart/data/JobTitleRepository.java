package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.entity.JobTitle;


public interface JobTitleRepository extends BaseRepository<JobTitle, Integer>{

	JobTitle findByName(String name);

}
