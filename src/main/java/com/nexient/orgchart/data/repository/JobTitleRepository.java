package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kskronek on 5/24/2016.
 */
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
}
