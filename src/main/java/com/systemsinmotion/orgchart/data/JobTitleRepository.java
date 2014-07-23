package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.entity.JobTitle;
import com.systemsinmotion.orgchart.data.BaseRepository;

public interface JobTitleRepository extends BaseRepository<JobTitle,Integer>{

	JobTitle findByName(String name);

}
