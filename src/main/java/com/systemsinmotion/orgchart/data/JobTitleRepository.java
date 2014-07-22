package com.systemsinmotion.orgchart.data;

import org.springframework.data.repository.NoRepositoryBean;

import com.systemsinmotion.orgchart.entity.JobTitle;

@NoRepositoryBean
public interface JobTitleRepository extends BaseRepository<JobTitle,Integer>{

}
